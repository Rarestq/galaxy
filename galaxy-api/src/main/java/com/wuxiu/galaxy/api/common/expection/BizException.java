package com.wuxiu.galaxy.api.common.expection;

/**
 * 业务异常类
 *
 * @author: wuxiu
 * @date: 2019/4/13 15:17
 */
public class BizException extends RuntimeException {

    /**
     * 错误的信息
     */
    private String message;

    public BizException(String message) {
        super(message);
        this.message = message;
    }

    public static BizException of(String message) {
        return new BizException(message);
    }
}
