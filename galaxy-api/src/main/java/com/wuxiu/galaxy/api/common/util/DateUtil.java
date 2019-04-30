package com.wuxiu.galaxy.api.common.util;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 日期工具类
 *
 * @author: wuxiu
 * @date: 2019/4/30 20:46
 */
public class DateUtil {

    /**
     * 计算逾期时长(单位：小时)
     *
     * @param storageEndTime
     * @param now
     * @return
     */
    public static long calculateDate2Hours(LocalDateTime storageEndTime,
                                             LocalDateTime now) {
        Duration between = Duration.between(storageEndTime, now);
        return between.toHours();
    }

    /**
     * 计算逾期时长(单位：天)
     *
     * @param storageEndTime
     * @param now
     * @return
     */
    public static long calculateDate2Days(LocalDateTime storageEndTime,
                                           LocalDateTime now) {
        Duration between = Duration.between(storageEndTime, now);
        return between.toDays();
    }
}
