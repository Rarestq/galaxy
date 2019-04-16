package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 取件类型状态枚举
 *
 * @author: wuxiu
 * @date: 2019/4/16 10:28
 */
@Getter
@AllArgsConstructor
@ToString
public enum PickUpLuggageTypeEnum {

    // 取件类型状态枚举
    NORMAL(0, "正常取件"),
    LUGGAGE_HAVE_LOST(1, "行李有遗失"),
    OVERDUE_PICK_UP(2, "逾期取件"),
    ;

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static PickUpLuggageTypeEnum valueOf(Integer code) {
        for (PickUpLuggageTypeEnum statusEnum : PickUpLuggageTypeEnum.values()) {
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
        PickUpLuggageTypeEnum[] arr = values();
        for (PickUpLuggageTypeEnum statusEnum : arr) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getDesc();
            }
        }
        return null;
    }
}
