package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 计算单位类型
 *
 * @author: wuxiu
 * @date: 2019/4/16 10:28
 */
@Getter
@AllArgsConstructor
@ToString
public enum CalculationUnitsEnum {

    // 计算单位类型
    YUAN_EACH_DAY(1, "元/件/天"),
    YUAN_PER_ITEM(2, "元/件/次"),
    YUAN_EACH(3, "元/件"),
    ;

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static CalculationUnitsEnum valueOf(Integer code) {
        for (CalculationUnitsEnum statusEnum : CalculationUnitsEnum.values()) {
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
        CalculationUnitsEnum[] arr = values();
        for (CalculationUnitsEnum statusEnum : arr) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getDesc();
            }
        }
        return null;
    }
}
