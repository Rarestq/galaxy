package com.wuxiu.galaxy.service.core.biz.service.smsservice;

import com.google.common.eventbus.AsyncEventBus;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.service.core.bus.event.FinishPickupEvent;
import com.wuxiu.galaxy.service.core.bus.event.FinishStorageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 完成寄存时，发送短信服务（根据前台操作后的状态返回值，调用后台的发短信接口）
 *
 * @author: wuxiu
 * @date: 2019/4/25 22:09
 */
@Slf4j
@Component
public class FinishStorageEventSmsService {

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Autowired
    private LuggageStorageRecordManager storageRecordManager;

    /**
     * 完成寄存时，发送行李寄存完成事件
     *
     * @param luggageId 前台返回的行李寄存记录主键id
     */
    public void notifyDepositorBySMS(Long luggageId, String storageFee) {

        // 查询刚完成行李寄存的记录信息
        LuggageStorageRecord storageRecord = storageRecordManager.selectById(luggageId);

        // 发送完成寄存事件
        asyncEventBus.post(FinishStorageEvent.builder()
                .luggageRecordNo(storageRecord.getLuggageRecordNo())
                .adminPhone(storageRecord.getAdminPhone())
                .depositorName(storageRecord.getDepositorName())
                .depositorPhone(storageRecord.getDepositorPhone())
                .storageEndTime(storageRecord.getStorageEndTime())
                .storageFee(storageFee)
                .build());
    }

    /**
     * 发送取件完成事件
     *
     * @param luggageId
     */
    public void sendFinishPickupSms(Long luggageId) {

        // 查询行李寄存记录信息
        LuggageStorageRecord storageRecord = storageRecordManager.selectById(luggageId);

        // 发送取件完成事件
        asyncEventBus.post(FinishPickupEvent.builder()
                .luggageRecordNo(storageRecord.getLuggageRecordNo())
                .depositorName(storageRecord.getDepositorName())
                .depositorPhone(storageRecord.getDepositorPhone())
                .build());
    }
}
