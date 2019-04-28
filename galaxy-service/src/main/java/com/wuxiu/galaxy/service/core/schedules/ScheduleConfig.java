package com.wuxiu.galaxy.service.core.schedules;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ExecutorService;

import static com.wuxiu.galaxy.api.common.constants.ExecutorConstant.SPRING_SCHEDULER;

/**
 * 定时任务配置
 *
 * @author: wuxiu
 * @date: 2019/4/28 20:09
 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public ExecutorService taskExecutor() {
        return SPRING_SCHEDULER;
    }
}
