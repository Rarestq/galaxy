package com.wuxiu.galaxy.service.core.base.enums;

import com.twilio.type.PhoneNumber;
import lombok.NoArgsConstructor;

/**
 * 短信服务相关常量
 *
 * @author: wuxiu
 * @date: 2019/5/2 21:57
 */
@NoArgsConstructor
public class SmsConstant {

    /**
     * find Account Sid and Auth Token at twilio.com/console
     */
    public static final String ACCOUNT_SID =
            "AC3e09e9612f0ec8639bf05971978bdd4e";
    public static final String AUTH_TOKEN =
            "c3591d28c9c963aea9703e6570640ddc";

    /**
     * who send sms
     */
    public static final PhoneNumber FROM_PHONE = new PhoneNumber("+16194576189");

    /**
     * who receive sms
     */
    public static final PhoneNumber TO_PHONE = new PhoneNumber("+8615180354187");
}
