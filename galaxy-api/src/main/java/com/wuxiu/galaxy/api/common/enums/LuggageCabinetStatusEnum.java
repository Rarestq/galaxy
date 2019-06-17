package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 行李寄存柜状态枚举类
 *
 * @author: wuxiu
 * @date: 2019/5/15 11:49
 */
@Getter
@AllArgsConstructor
@ToString
public enum LuggageCabinetStatusEnum {

    // 行李寄存柜状态枚举 (0-空闲、1-已占用、2-逾期占用、3-维修中)
    FREE(0, "空闲"),
    HAD_OCCUPIED(1, "已占用"),
    OVERDUE_OCCUPIED(2, "逾期占用"),
    REPAIRING(3, "维修中"),
    ;

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static LuggageCabinetStatusEnum valueOf(Integer code) {
        for (LuggageCabinetStatusEnum statusEnum : LuggageCabinetStatusEnum
                .values()) {
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
        LuggageCabinetStatusEnum[] arr = values();
        for (LuggageCabinetStatusEnum statusEnum : arr) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getDesc();
            }
        }
        return null;
    }
}
