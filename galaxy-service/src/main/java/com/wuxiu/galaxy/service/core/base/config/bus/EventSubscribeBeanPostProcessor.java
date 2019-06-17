package com.wuxiu.galaxy.service.core.base.config.bus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 注册事件消费者逻辑到事件总线
 *
 * @author: wuxiu
 * @date: 2019/4/24 09:03
 */
@Component
public class EventSubscribeBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        // foreach method in the bean
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            // check the annotations on that method
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                // if it contains the @Subscribe annotation
                if (Objects.equals(annotation.annotationType(), Subscribe.class)) {
                    // 如果这是一个 Guava @Subscribe 注解的事件监听器方法，说明所在 bean 实例
                    // 对应一个 Guava 事件监听器类，将该 bean 实例注册到 Guava 事件总线
                    eventBus.register(bean);
                    asyncEventBus.register(bean);
                    return bean;
                }
            }
        }

        return bean;
    }
}
