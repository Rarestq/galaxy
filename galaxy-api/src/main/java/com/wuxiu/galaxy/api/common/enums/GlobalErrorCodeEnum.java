package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 全局异常错误码枚举
 *
 * @author wuxiu
 */
@AllArgsConstructor
@Getter
@ToString
public enum GlobalErrorCodeEnum implements ErrorCode {

    /**
     * 全局异常错误码
     */
    SUCCESS("200", "OK"),
    FAILURE("-1", "Operation Failed"),
    INVALID_PARAM("100", "parameter Error"),
    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "Unauthorized"),
    FORBIDDEN("403", "Forbidden"),
    NOT_FOUND("404", "Not Found"),
    METHOD_NOT_ALLOWED("405", "Method Not Allowed"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),


    LOGIN_FAILURE("1000", "Login Failure!"),
    LOGOUT_FAILURE("1001", "Logout Failure!"),

    DATA_ERROR("5000", "数据异常!"),

    SYSTEM_EXCEPTION("9999", "System Exception!");


    private final String code;
    private final String message;
}

