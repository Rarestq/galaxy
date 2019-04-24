package com.wuxiu.galaxy.api.common.constants;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * 常用线程池
 *
 * @author: wuxiu
 * @date: 2019/4/24 08:59
 */
@NoArgsConstructor
public class ExecutorConstant {

    /**
     * 公共线程池
     * 一般用于内存内计算任务
     */
    public static final Executor COMMON_EXECUTOR = Executors.newCachedThreadPool();

    /**
     * 用于spring定时任务
     */
    public static final ScheduledExecutorService SPRING_SCHEDULER = new ScheduledThreadPoolExecutor(
            10,
            new BasicThreadFactory.Builder()
                    .namingPattern("spring-schedule-pool-%d")
                    .build()
    );

    /**
     * EventBus线程池
     */
    public static final ExecutorService EVENT_BUS_EXECUTOR = new ThreadPoolExecutor(
            8, 16,
            60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            new BasicThreadFactory.Builder()
                    .namingPattern("event-bus-pool-%d")
                    .daemon(true)
                    .build());
}
