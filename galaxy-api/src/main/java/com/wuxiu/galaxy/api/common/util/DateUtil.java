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
    public static long calculateDate2Hours(LocalDateTime now,
                                           LocalDateTime storageEndTime) {
        Duration between = Duration.between(now, storageEndTime);
        return between.toHours();
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = LocalDateTime.of(2019, 5, 2, 15, 21);
        long hours = calculateDate2Hours(now, time);
        System.out.println("时间差：" + hours);
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
