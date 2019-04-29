package com.wuxiu.galaxy.service.core.schedules;

import com.google.common.eventbus.AsyncEventBus;
import com.wuxiu.galaxy.api.common.enums.LuggageStorageStatusEnum;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.service.core.bus.event.CreateOverdueRecordEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

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

    /**
     * 短信通知寄存者行李寄存即将逾期(寄存结束时间前 15 min 通知)，若已逾期，则更新状态
     * <p>
     * 每隔五秒更新一次行李寄存记录的状态
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void refreshLuggageStorageStatus() {
        // 定时检查行李寄存记录的寄存结束时间

        // 查询所有有效的行李寄存记录信息
        List<LuggageStorageRecord> storageRecords =
                storageRecordManager.selectAllStorageRecords();
        for (LuggageStorageRecord storageRecord : storageRecords) {
            LocalDateTime storageEndTime = storageRecord.getStorageEndTime();
            // 将行李寄存结束时间转化为当前时区下的毫秒
            long storageEndTimeMilli =
                    storageEndTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();

            // 已逾期，发送事件
            if (isTimeExpired(storageEndTimeMilli)) {
                asyncEventBus.post(CreateOverdueRecordEvent.builder()
                        .luggageId(storageRecord.getLuggageId())
                        .status(LuggageStorageStatusEnum.getDescByCode(
                                storageRecord.getStatus()))
                        .remark(storageRecord.getRemark())
                        .build());
                log.info("已发送自动创建逾期行李寄存记录：storageRecord:{}" + storageRecord);
            }
        }

    }

    /**
     * 判断行李寄存结束时间相对系统当前时间是否已逾期
     *
     * @param timeValue
     * @return
     */
    private boolean isTimeExpired(long timeValue) {
        return timeValue > System.currentTimeMillis();
    }
}
