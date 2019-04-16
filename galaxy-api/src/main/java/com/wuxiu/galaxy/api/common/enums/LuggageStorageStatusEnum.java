package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 寄存行李状态
 *
 * @author: wuxiu
 * @date: 2019/4/16 10:28
 */
@Getter
@AllArgsConstructor
@ToString
public enum LuggageStorageStatusEnum {

    // 寄存行李状态类型
    DEPOSITING(0, "寄存中"),
    PICKED_UP(1, "已取件"),
    OVERDUE(2, "已逾期"),
    ;

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static LuggageStorageStatusEnum valueOf(Integer code) {
        for (LuggageStorageStatusEnum statusEnum : LuggageStorageStatusEnum.values()) {
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
        LuggageStorageStatusEnum[] arr = values();
        for (LuggageStorageStatusEnum statusEnum : arr) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getDesc();
            }
        }
        return null;
    }
}
