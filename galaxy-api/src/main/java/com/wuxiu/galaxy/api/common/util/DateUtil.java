package com.wuxiu.galaxy.api.common.util;

import java.sql.Timestamp;
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
     * 计算逾期（寄存）时长(单位：小时)
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
        LocalDateTime now = LocalDateTime.of(2019, 5, 8, 15, 49);
        LocalDateTime time = LocalDateTime.of(2019, 5, 8, 17, 49);
        long hours = calculateDate2Hours(now, time);
        System.out.println("当前时间：" + now);
        System.out.println("当前时间：" + LocalDateTime.now());
        System.out.println("时间差：" + hours);
    }

    /**
     * 计算逾期（寄存）时长(单位：天)
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

    /**
     * 日期转换： todo: 前台传过来的时间格式转换为 LocalDateTime:
     *  todo:string(前台格式：只能 yyyy-MM-dd hh:mm:ss) -> timestamp -> LocalDateTime
     *
     * @param dateValue
     * @return
     */
    public static LocalDateTime string2LocalDateTime(String dateValue) {
        return Timestamp.valueOf(dateValue).toLocalDateTime();
    }

}
