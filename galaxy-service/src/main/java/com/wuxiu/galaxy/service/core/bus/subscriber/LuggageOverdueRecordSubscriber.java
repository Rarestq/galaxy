package com.wuxiu.galaxy.service.core.bus.subscriber;

import com.google.common.eventbus.Subscribe;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.api.common.enums.LuggageOverdueStatusEnum;
import com.wuxiu.galaxy.api.common.enums.UserTypeEnum;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.api.dto.SaveLuggageOverdueRecordDTO;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.service.core.base.enums.SmsTypeEnum;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LuggageOverdueRecordService;
import com.wuxiu.galaxy.service.core.biz.service.smsservice.SmsBody;
import com.wuxiu.galaxy.service.core.biz.service.smsservice.SmsSender;
import com.wuxiu.galaxy.service.core.bus.event.CreateOverdueRecordEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 行李逾期记录订阅者
 *
 * @author: wuxiu
 * @date: 2019/4/23 17:11
 */
@Slf4j
@Component
public class LuggageOverdueRecordSubscriber {

    @Autowired
    private LuggageOverdueRecordService luggageOverdueRecordService;

    @Autowired
    private LuggageStorageRecordManager storageRecordManager;

    @Autowired
    private SmsSender smsSender;

    /**
     * 自动创建行李逾期记录
     *
     * @param event
     */
    @Subscribe
    public void autoCreateLugaggeOverdueRecord(CreateOverdueRecordEvent event) {
        log.info("自动创建行李逾期记录开始，event:{}", event);

        SaveLuggageOverdueRecordDTO overdueRecordDTO = new SaveLuggageOverdueRecordDTO();

        overdueRecordDTO.setLuggageId(event.getLuggageId());
        overdueRecordDTO.setRemark(event.getRemark());
        Integer status = LuggageOverdueStatusEnum.getCodeByDesc(event.getStatus());
        overdueRecordDTO.setStatus(status);

        OperateUserDTO operateUserDTO = new OperateUserDTO();
        operateUserDTO.setOperateUserId(0L);
        operateUserDTO.setName(UserTypeEnum.SYSTEM.getDesc());
        operateUserDTO.setOperateUserNo("SYSTEM");
        operateUserDTO.setOperateUserPhone("-");
        operateUserDTO.setUserTypeEnum(UserTypeEnum.SYSTEM);

        luggageOverdueRecordService.createLuggageOverdueRecord(overdueRecordDTO,
                operateUserDTO);
        log.info("自动创建行李逾期记录结束");

        // 查询寄存记录信息
        LuggageStorageRecord storageRecord =
                storageRecordManager.selectById(event.getLuggageId());

        SmsBody smsBody = buildSmsBody(storageRecord);

        // 发送短信
        smsSender.sendSms(smsBody);

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
        smsBody.setDepositorPhone(CommonConstant.PHONE_AREA_CODE +
                storageRecord.getDepositorPhone());
        smsBody.setStorageRecordNo(storageRecord.getLuggageRecordNo());
        smsBody.setStorageEndTime(storageRecord.getStorageEndTime());
        smsBody.setSmsType(SmsTypeEnum.OVERDUE_SMS_TYPE.getCode());

        return smsBody;
    }
}
