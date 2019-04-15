package com.wuxiu.galaxy.api.common.expection;

/**
 * 参数异常类
 *
 * @author: wuxiu
 * @date: 2019/4/13 15:18
 */
public class ParamException extends RuntimeException {

    /**
     * 错误的信息
     */
    private String message;

    public ParamException(String message) {
        super(message);
        this.message = message;
    }
}
