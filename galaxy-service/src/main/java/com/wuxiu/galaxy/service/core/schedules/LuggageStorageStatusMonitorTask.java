package com.wuxiu.galaxy.service.core.schedules;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.eventbus.AsyncEventBus;
import com.wuxiu.galaxy.api.common.enums.LuggageStorageStatusEnum;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.service.core.base.enums.SmsTypeEnum;
import com.wuxiu.galaxy.service.core.biz.service.smsservice.SmsBody;
import com.wuxiu.galaxy.service.core.biz.service.smsservice.SmsSender;
import com.wuxiu.galaxy.service.core.bus.event.CreateOverdueRecordEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

import static org.apache.curator.shaded.com.google.common.collect.Lists.newArrayList;

/**
 * 行李寄存结束日期监控任务
 *
 * @author: wuxiu
 * @date: 2019/4/23 16:34
 */
@Slf4j
@Component
public class LuggageStorageStatusMonitorTask {

    @Autowired
    private LuggageStorageRecordManager storageRecordManager;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Autowired
    private SmsSender smsSender;

    private static final long MINUTES_BEFORE_OVERDUE = 30L;

    /**
     * 短信通知寄存者行李寄存即将逾期(寄存结束时间前 15 min 通知)，若已逾期，则更新状态
     * <p>
     * 每隔五秒更新一次行李寄存记录的状态
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void refreshLuggageStorageStatus() {

        // 获取所有处于「寄存中」状态的行李寄存记录信息
        List<LuggageStorageRecord> storageRecords =
                storageRecordManager.selectAllStorageRecords();
        List<LuggageStorageRecord> depositingStorageRecords =
                hasNoOverdueRecords(storageRecords);

        if (CollectionUtils.isEmpty(depositingStorageRecords)) {
            // 如果行李寄存记录中没有「寄存中」状态的记录时，直接返回
            log.info("当前没有「寄存中」状态的记录");
            return;
        } else {
            for (LuggageStorageRecord storageRecord : depositingStorageRecords) {
                LocalDateTime storageEndTime = storageRecord.getStorageEndTime();
                // 将行李寄存结束时间转化为当前时区下的毫秒
                long storageEndTimeMilli =
                        storageEndTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();

                // 根据寄存时长，在其寄存时间到达前半小时发送短信告知用户
                if (isHalfHourBeforeOverdue(storageEndTime)) {
                    SmsBody smsBody = buildSmsBody(storageRecord);

                    // 发送逾期前通知提醒短信
                    //smsSender.sendSms(smsBody);
                }

                // 已逾期,更新寄存记录状态以及发送创建逾期记录事件
                if (isTimeExpired(storageEndTimeMilli)) {
                    // 更新寄存记录状态
                    LuggageStorageRecord luggageStorageRecord =
                            buildLuggageStorageRecord(storageRecord);
                    storageRecordManager.updateById(luggageStorageRecord);

                    // 发送创建逾期记录事件
                    asyncEventBus.post(CreateOverdueRecordEvent.builder()
                            .luggageId(storageRecord.getLuggageId())
                            .status(LuggageStorageStatusEnum.OVERDUE.getDesc())
                            .remark(storageRecord.getRemark())
                            .build());
                    log.info("已发送自动创建逾期行李寄存记录事件：storageRecord:{}" +
                            storageRecord);
                }
            }

        }
    }

    /**
     * 构造 LuggageStorageRecord 对象
     *
     * @param storageRecord
     * @return
     */
    private LuggageStorageRecord buildLuggageStorageRecord(LuggageStorageRecord storageRecord) {
        LuggageStorageRecord luggageStorageRecord =
                new LuggageStorageRecord();
        luggageStorageRecord.setLuggageId(storageRecord.getLuggageId());
        luggageStorageRecord.setStatus(LuggageStorageStatusEnum.OVERDUE
                .getCode());
        // 将寄存结束时间改为一个小时后（这里有个逻辑问题，之前没注意到，改为一小时后，后面计算费用可能会出现负数）
        luggageStorageRecord.setStorageEndTime(LocalDateTime.now());
        luggageStorageRecord.setGmtModified(LocalDateTime.now());

        return luggageStorageRecord;
    }

    /**
     * 构造 SmsBody
     *
     * @param storageRecord
     * @return
     */
    private SmsBody buildSmsBody(LuggageStorageRecord storageRecord) {
        SmsBody smsBody = new SmsBody();
        smsBody.setAdminPhone(storageRecord.getAdminPhone());
        smsBody.setDepositorName(storageRecord.getDepositorName());
        smsBody.setDepositorPhone(storageRecord.getDepositorPhone());
        smsBody.setStorageRecordNo(storageRecord.getLuggageRecordNo());
        smsBody.setStorageEndTime(storageRecord.getStorageEndTime());
        smsBody.setSmsType(SmsTypeEnum.BEFORE_OVERDUE_SMS_TYPE.getCode());

        return smsBody;
    }

    /**
     * 判断行李寄存结束时间相对系统当前时间是否已逾期
     *
     * @param timeValue
     * @return
     */
    private boolean isTimeExpired(long timeValue) {
        return timeValue < System.currentTimeMillis();
    }

    /**
     * 判断当前时间是否为寄存结束时间前半小时
     *
     * @param localDateTime
     * @return
     */
    private boolean isHalfHourBeforeOverdue(LocalDateTime localDateTime) {

        Duration between = Duration.between(localDateTime, LocalDateTime.now());
        long minutes = between.toMinutes();

        return MINUTES_BEFORE_OVERDUE == minutes;
    }

    /**
     * 判断是否还有逾期的记录，返回处于寄存中状态的记录
     *
     * @param storageRecords
     * @return
     */
    private List<LuggageStorageRecord> hasNoOverdueRecords(List<LuggageStorageRecord>
                                                                   storageRecords) {
        List<LuggageStorageRecord> depositingStorageRecords = newArrayList();
        for (LuggageStorageRecord luggageStorageRecord : storageRecords) {
            if (Objects.equals(luggageStorageRecord.getStatus(),
                    LuggageStorageStatusEnum.DEPOSITING.getCode())) {
                depositingStorageRecords.add(luggageStorageRecord);
            }
        }
        return depositingStorageRecords;
    }
}
