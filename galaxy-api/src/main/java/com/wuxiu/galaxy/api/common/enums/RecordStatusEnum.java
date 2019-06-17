package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 记录状态枚举
 *
 * @author: wuxiu
 * @date: 2019/5/1 21:33
 */
@Getter
@AllArgsConstructor
@ToString
public enum RecordStatusEnum {

    /**
     * 记录状态枚举
     */
    NORMAL(0, "正常"),
    DELETED(1, "删除")
            ;

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static RecordStatusEnum valueOf(Integer code) {
        for (RecordStatusEnum statusEnum : RecordStatusEnum.values()) {
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
        RecordStatusEnum[] arr = values();
        for (RecordStatusEnum statusEnum : arr) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getDesc();
            }
        }
        return null;
    }
}
