package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 行李遗失记录状态枚举类
 *
 * @author: wuxiu
 * @date: 2019/5/9 20:07
 */
@Getter
@AllArgsConstructor
@ToString
public enum LostRegisterRecordStatusEnum {

    // 行李遗失记录状态枚举
    HAD_LOST(0, "已遗失"),
    HAD_COMPENSATE(1, "已赔偿"),
    ;

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static LostRegisterRecordStatusEnum valueOf(Integer code) {
        for (LostRegisterRecordStatusEnum statusEnum : LostRegisterRecordStatusEnum
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
        LostRegisterRecordStatusEnum[] arr = values();
        for (LostRegisterRecordStatusEnum statusEnum : arr) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getDesc();
            }
        }
        return null;
    }
}
