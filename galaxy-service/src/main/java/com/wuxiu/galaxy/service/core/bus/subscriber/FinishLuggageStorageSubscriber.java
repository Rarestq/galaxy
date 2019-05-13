package com.wuxiu.galaxy.service.core.bus.subscriber;

import com.google.common.eventbus.Subscribe;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.service.core.base.enums.SmsTypeEnum;
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
    private SmsSender smsSender;

    @Subscribe
    public void sendSms2UserwhenFinishStorage(FinishStorageEvent event) {
        log.info("行李寄存成功，开始给用户发送短信,event:{}", event);

        // 组装发送短信的消息体
        SmsBody smsBody = new SmsBody();
        smsBody.setAdminPhone(event.getAdminPhone());
        smsBody.setDepositorName(event.getDepositorName());
        smsBody.setDepositorPhone(CommonConstant.PHONE_AREA_CODE + event.getDepositorPhone());
        smsBody.setStorageRecordNo(event.getLuggageRecordNo());
        smsBody.setStorageEndTime(event.getStorageEndTime());
        smsBody.setFee(event.getStorageFee());
        smsBody.setSmsType(SmsTypeEnum.FINISH_STORAGE_SMS_TYPE.getCode());

        // 发送短信
        smsSender.sendSms(smsBody);

        log.info("发送短信成功");
    }

}
