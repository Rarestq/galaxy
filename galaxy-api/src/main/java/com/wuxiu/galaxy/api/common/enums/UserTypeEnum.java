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
}
