package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 计算规则类型
 *
 * @author: wuxiu
 * @date: 2019/4/16 10:28
 */
@Getter
@AllArgsConstructor
@ToString
public enum ChargeCalculationTypeEnum {

    // 计算规则类型
    FIXED_CHARGE_CALCULATION(1, "固定计费"),
    CYCLE_CHARGE_CALCULATION(2, "周期计费"),
    ;

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static ChargeCalculationTypeEnum valueOf(Integer code) {
        for (ChargeCalculationTypeEnum statusEnum : ChargeCalculationTypeEnum.values()) {
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
        ChargeCalculationTypeEnum[] arr = values();
        for (ChargeCalculationTypeEnum statusEnum : arr) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getDesc();
            }
        }
        return null;
    }
}
