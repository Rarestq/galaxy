package com.wuxiu.galaxy.service.core.biz.service.smsservice;

import com.wuxiu.galaxy.service.core.biz.service.apiservice.LuggageStorageRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 逾期前短信通知服务
 *
 * @author: wuxiu
 * @date: 2019/4/25 22:21
 */
@Slf4j
@Component
public class BeforeOverdueEventSmsService {

    @Autowired
    private SmsSender smsSender;

    @Autowired
    private LuggageStorageRecordService storageRecordService;

    /**
     * todo:行李寄存结束时间结束前 15 min(看具体寄存时长) 短信通知寄存人，
     */
    private void sendSms2NotifyUserPickupLuggage() {
        SmsBody smsBody = new SmsBody();
    }
}
