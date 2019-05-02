package com.wuxiu.galaxy.api.common.expection;

/**
 * 短信异常
 *
 * @author: wuxiu
 * @date: 2019/5/2 22:20
 */
public class SmsException extends RuntimeException {

    /**
     * 错误的信息
     */
    private String message;

    public SmsException(String message) {
        super(message);
        this.message = message;
    }
}
