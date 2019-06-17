package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 行李类型枚举
 *
 * @author: wuxiu
 * @date: 2019/4/16 09:37
 */
@Getter
@AllArgsConstructor
@ToString
public enum LuggageTypeEnum {

    // 行李类型
    COMMON_LUGGAGE_TYPE(1L, "普通物件"),
    FRAGILE_LUGGAGE_TYPE(2L, "易碎物件"),
    VALUABLE_LUGGAGE_TYPE(3L, "贵重物件"),
    ;

    private Long code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static LuggageTypeEnum valueOf(Long code) {
        for (LuggageTypeEnum luggageTypeEnum : LuggageTypeEnum.values()) {
            if (luggageTypeEnum.getCode().equals(code)) {
                return luggageTypeEnum;
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
    public static String getDescByCode(Long code) {
        LuggageTypeEnum[] arr = values();
        for (LuggageTypeEnum luggageTypeEnum : arr) {
            if (luggageTypeEnum.getCode().equals(code)) {
                return luggageTypeEnum.getDesc();
            }
        }
        return null;
    }
}
