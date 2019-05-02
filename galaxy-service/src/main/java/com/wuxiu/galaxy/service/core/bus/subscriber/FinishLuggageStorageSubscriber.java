package com.wuxiu.galaxy.service.core.bus.subscriber;

import com.google.common.eventbus.Subscribe;
import com.wuxiu.galaxy.service.core.biz.service.smsservice.FinishStorageEventSmsService;
import com.wuxiu.galaxy.service.core.biz.service.smsservice.SmsBody;
import com.wuxiu.galaxy.service.core.biz.service.smsservice.SmsSender;
import com.wuxiu.galaxy.service.core.bus.event.FinishStorageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 完成行李寄存后触发的事件的订阅者
 *
 * @author: wuxiu
 * @date: 2019/4/25 22:36
 */
@Slf4j
@Component
public class FinishLuggageStorageSubscriber {

    @Autowired
    private FinishStorageEventSmsService finishStorageEventSmsService;

    @Autowired
    private SmsSender smsSender;

    @Subscribe
    public void sendSms2UserwhenFinishStorage(FinishStorageEvent event) {
        log.info("行李寄存成功，开始给用户发送短信,event:{}", event);

        //todo:组装发送短信的消息内容
        SmsBody smsBody = new SmsBody();
        smsBody.setAdminPhone(event.getAdminPhone());
        smsBody.setDepositorName(event.getDepositorName());
        smsBody.setDepositorPhone(event.getDepositorPhone());
        smsBody.setStorageRecordNo(event.getLuggageRecordNo());
        smsBody.setStorageEndTime(event.getStorageEndTime());
        smsBody.setFee(event.getStorageFee());


        log.warn("发送短信失败，message:{}");

        log.info("发送短信成功");
    }

}
