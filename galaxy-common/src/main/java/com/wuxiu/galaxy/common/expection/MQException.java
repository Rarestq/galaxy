package com.wuxiu.galaxy.common.expection;

/**
 * RocketMQ的自定义异常
 *
 * @author: wuxiu
 * @date: 2019/4/14 21:24
 */
public class MQException extends RuntimeException {
    public MQException(String msg) {
        super(msg);
    }
}

