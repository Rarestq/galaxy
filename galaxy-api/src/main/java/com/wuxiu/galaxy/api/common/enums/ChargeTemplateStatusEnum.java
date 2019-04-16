package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 计费模板状态枚举
 *
 * @author: wuxiu
 * @date: 2019/4/16 10:28
 */
@Getter
@AllArgsConstructor
@ToString
public enum ChargeTemplateStatusEnum {

    // 计费模板状态枚举
    DISABLE(0, "开启"),
    ENABLE(1, "禁用");

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static ChargeTemplateStatusEnum valueOf(Integer code) {
        for (ChargeTemplateStatusEnum statusEnum : ChargeTemplateStatusEnum.values()) {
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
        ChargeTemplateStatusEnum[] arr = values();
        for (ChargeTemplateStatusEnum statusEnum : arr) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getDesc();
            }
        }
        return null;
    }
}
