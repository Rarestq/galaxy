package com.wuxiu.galaxy.service.core.biz.service.smsservice;

import com.google.common.eventbus.AsyncEventBus;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.service.core.bus.event.FinishPickupEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 完成取件时，发送事件
 *
 * @author: wuxiu
 * @date: 2019/5/28 15:46
 */
@Slf4j
@Component
public class FinishPickupEventSmsService {

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Autowired
    private LuggageStorageRecordManager storageRecordManager;

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
