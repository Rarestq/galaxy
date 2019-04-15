package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 日期时间格式化字符串枚举类
 *
 * @author: wuxiu
 * @date: 2019/4/13 15:39
 */
@AllArgsConstructor
@Getter
@ToString
public enum DateTimeFormStrEnum {

    /**
     * 日期时间格式化字符串枚举类
     */
    DEFAULT("", 0),
    YYYY("yyyy", 4),
    YYYY_MM("yyyyMM", 6),
    YYYY_MM_DD("yyyyMMdd", 8),
    YYYY_MM_DD_HH("yyyyMMddHH", 10),
    YYYY_MM_DD_HH_MM("yyyyMMddHHmm", 12),
    YYYY_MM_DD_HH_MM_SS("yyyyMMddHHmmss", 14);

    private String code;
    private Integer len;

    /**
     * 根据code获取枚举值
     *
     * @param code
     * @return DateTimeFormStrEnum
     */
    public static DateTimeFormStrEnum getEnum(String code) {
        DateTimeFormStrEnum[] arry = DateTimeFormStrEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].getCode().equalsIgnoreCase(code)) {
                return arry[i];
            }
        }
        return null;
    }

}

