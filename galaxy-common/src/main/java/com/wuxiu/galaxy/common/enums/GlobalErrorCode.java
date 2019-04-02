package com.wuxiu.galaxy.common.enums;

/**
 * @author Shihaahs
 */

@SuppressWarnings("ALL")
public enum GlobalErrorCode implements ErrorCode{

    SUCCESS("200", "OK"),
    FAILURE("-1", "Operation Failed"),
    INVALID_PARAM("100", "Parameter Error"),
    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "Unauthorized"),
    FORBIDDEN("403", "Forbidden"),
    NOT_FOUND("404", "Not Found"),
    METHOD_NOT_ALLOWED("405", "Method Not Allowed"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),



    LOGIN_FAILURE("1000","Login Failure!"),
    LOGOUT_FAILURE("1001","Logout Failure!"),
    REGISTER_FAILURE("1002","Register Failure!"),
    REGISTER_FAILURE_PHONE_REPEAT("1003","Phone has been existed!"),

    WITHDRAW_ERROR("5000", "Account balance is not enough!"),


    SYSTEM_EXCEPTION("9999","System Exception!");



    private final String code;
    private final String message;

    private GlobalErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

