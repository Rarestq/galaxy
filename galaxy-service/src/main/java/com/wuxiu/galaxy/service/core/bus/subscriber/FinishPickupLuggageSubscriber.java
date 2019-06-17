package com.wuxiu.galaxy.service.core.bus.subscriber;

import com.google.common.eventbus.Subscribe;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.service.core.base.enums.SmsTypeEnum;
import com.wuxiu.galaxy.service.core.biz.service.smsservice.SmsBody;
import com.wuxiu.galaxy.service.core.biz.service.smsservice.SmsSender;
import com.wuxiu.galaxy.service.core.bus.event.FinishPickupEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 完成取件事件订阅者
 *
 * @author: wuxiu
 * @date: 2019/5/28 15:10
 */
@Slf4j
@Component
public class FinishPickupLuggageSubscriber {

    @Autowired
    private SmsSender smsSender;

    @Subscribe
    public void sendSms2UserwhenFinishPickup(FinishPickupEvent event) {
        log.info("行李取件成功，开始给用户发送短信,event:{}", event);

        // 组装发送短信的消息体
        SmsBody smsBody = new SmsBody();
        smsBody.setDepositorName(event.getDepositorName());
        smsBody.setDepositorPhone(CommonConstant.PHONE_AREA_CODE + event.getDepositorPhone());
        smsBody.setStorageRecordNo(event.getLuggageRecordNo());
        smsBody.setSmsType(SmsTypeEnum.FINISH_PICKUP_SMS_TYPE.getCode());

        // 发送短信
        smsSender.sendSms(smsBody);

        log.info("发送短信成功");
    }
}
