package com.wuxiu.galaxy.service.core.base.config.bus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.wuxiu.galaxy.api.common.constants.ExecutorConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;

/**
 * 事件总线配置
 *
 * @author: wuxiu
 * @date: 2019/4/24 08:55
 */
@Configuration
public class EventBusConfig {

    /**
     * 全局事件总线
     *
     * @return
     */
    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }

    /**
     * 全局异步事件总线
     *
     * @return
     */
    @Bean
    public AsyncEventBus asyncEventBus() {
        return new AsyncEventBus(busExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService busExecutor() {
        return ExecutorConstant.EVENT_BUS_EXECUTOR;
    }
}
