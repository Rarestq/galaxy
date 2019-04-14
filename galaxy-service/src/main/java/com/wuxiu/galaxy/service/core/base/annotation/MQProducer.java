package com.wuxiu.galaxy.service.core.base.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * RocketMQ生产者自动装配注解
 *
 * @author: wuxiu
 * @date: 2019/4/14 21:32
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MQProducer {
    String topic() default "";

    String tag() default "";

    String namesrv() default "";
}
