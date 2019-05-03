package com.wuxiu.galaxy.service.core.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 短信类型枚举
 *
 * @author: wuxiu
 * @date: 2019/5/3 10:58
 */
@Getter
@AllArgsConstructor
@ToString
public enum  SmsTypeEnum {

    /**
     * 短信类型
     */
    FINISH_STORAGE_SMS_TYPE(1,"寄存完成"),

    BEFORE_OVERDUE_SMS_TYPE(2,"逾期前"),

    OVERDUE_SMS_TYPE(3,"逾期"),
            ;

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static SmsTypeEnum valueOf(Integer code) {
        for (SmsTypeEnum smsTypeEnum : SmsTypeEnum.values()) {
            if (smsTypeEnum.getCode().equals(code)) {
                return smsTypeEnum;
            }
        }
        return null;
    }

    /**
     * 通过 code 得到枚举描述
     *
     * @param code 枚举的 code
     * @return
     */
    public static String getDescByCode(Integer code) {
        SmsTypeEnum[] arr = values();
        for (SmsTypeEnum smsTypeEnum : arr) {
            if (smsTypeEnum.getCode().equals(code)) {
                return smsTypeEnum.getDesc();
            }
        }
        return null;
    }

}
