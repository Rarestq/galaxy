package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 行李逾期未取清理状态
 *
 * @author: wuxiu
 * @date: 2019/4/16 10:28
 */
@Getter
@AllArgsConstructor
@ToString
public enum LuggageOverdueStatusEnum {

    // 寄存行李状态类型
    OVERDUE(1, "已逾期"),
    CLEARED_UP(2, "已清理作废"),
    ;

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static LuggageOverdueStatusEnum valueOf(Integer code) {
        for (LuggageOverdueStatusEnum statusEnum : LuggageOverdueStatusEnum.values()) {
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
        LuggageOverdueStatusEnum[] arr = values();
        for (LuggageOverdueStatusEnum statusEnum : arr) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getDesc();
            }
        }
        return null;
    }
}
