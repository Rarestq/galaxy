package com.wuxiu.galaxy.service.core.biz.service.smsservice;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.wuxiu.galaxy.api.common.expection.SmsException;
import com.wuxiu.galaxy.service.core.base.utils.SplicingStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.wuxiu.galaxy.service.core.base.enums.SmsConstant.ACCOUNT_SID;
import static com.wuxiu.galaxy.service.core.base.enums.SmsConstant.AUTH_TOKEN;
import static com.wuxiu.galaxy.service.core.base.enums.SmsConstant.FROM_PHONE;

/**
 * twilio 短信发送
 *
 * @author: wuxiu
 * @date: 2019/5/2 21:41
 */
@Slf4j
@Component
public class SmsSender {

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendSms(SmsBody smsBody) {

        Message message = Message
                // to
                .creator(new PhoneNumber(smsBody.getDepositorPhone()),
                        // from
                        FROM_PHONE,
                        SplicingStringUtils.getSmsContentBySmsType(smsBody))
                .create();

        if (!Objects.equals(message.getStatus(), Message.Status.SENT)) {
            log.info("短信发送失败， status:{}" + message.getStatus());
            throw new SmsException("短信发送失败，请检查原因");
        }

        System.out.println(message.getSid());
    }
}
