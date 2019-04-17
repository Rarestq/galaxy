package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 计费规则类型
 *
 * @author: wuxiu
 * @date: 2019/4/16 10:28
 */
@Getter
@AllArgsConstructor
@ToString
public enum ChargeRuleTypeEnum {

    // 计费规则类型
    RECEIVABLE_RULE(1, "应收规则"),
    PAYABLE_RULE(2, "应付规则"),
    SERVICE_RULE(3, "增值规则"),
    ;

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static ChargeRuleTypeEnum valueOf(Integer code) {
        for (ChargeRuleTypeEnum statusEnum : ChargeRuleTypeEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum;
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
        ChargeRuleTypeEnum[] arr = values();
        for (ChargeRuleTypeEnum statusEnum : arr) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getDesc();
            }
        }
        return null;
    }
}
