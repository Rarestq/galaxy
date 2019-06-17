package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 用户类型枚举
 *
 * @author: wuxiu
 * @date: 2019/4/15 15:47
 */
@Getter
@AllArgsConstructor
@ToString
public enum UserTypeEnum {

    // 管理员类型
    ADMIN(1, "普通管理员"),
    SUPER_ADMIN(2, "超级管理员"),
    SYSTEM(3, "系统"),
    ;

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static UserTypeEnum valueOf(Integer code) {
        for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
            if (userTypeEnum.getCode().equals(code)) {
                return userTypeEnum;
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
        UserTypeEnum[] arr = values();
        for (UserTypeEnum userTypeEnum : arr) {
            if (userTypeEnum.getCode().equals(code)) {
                return userTypeEnum.getDesc();
            }
        }
        return null;
    }
}
