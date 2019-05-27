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

    public static final String ACCOUNT_SID1 = "ACeadeb7d0a6be188125e6f8c14b7abbc8";
    public static final String AUTH_TOKEN1 = "c96ffd232f2cb9f265fbab058a6b812a";


    /**
     * who send sms
     */
    public static final PhoneNumber dFROM_PHONE = new PhoneNumber("+16194576189");

    public static final PhoneNumber FROM_PHONE1 = new PhoneNumber("+19165206371");

    /**
     * who receive sms(must be verified on twilio.com)
     */
    public static final PhoneNumber TO_PHONE = new PhoneNumber("+8615180354187");

    public static final PhoneNumber TO_PHONE1 = new PhoneNumber("+8617314857019");

}
