package com.wuxiu.galaxy.api.common.util;

import com.wuxiu.galaxy.api.common.constants.CommonConstant;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
     * @param now
     * @param endTime
     * @return
     */
    public static long calculateDate2Hours(LocalDateTime endTime,
                                           LocalDateTime now) {

        Duration between = Duration.between(endTime, now);
        long toHours = between.toHours();
        // 不满一小时算一小时
        LocalDateTime localDateTime = now.minusHours(toHours);
        Duration duration = Duration.between(endTime, localDateTime);
        if (Math.abs(duration.toMinutes()) > 0) {
            toHours += 1;
        }
        return toHours;
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
     * todo:string(前台格式：只能 yyyy-MM-dd hh:mm:ss) -> timestamp -> LocalDateTime
     *
     * @param dateValue
     * @return
     */
    public static LocalDateTime string2LocalDateTime(String dateValue) {
        return Timestamp.valueOf(dateValue).toLocalDateTime();
    }

    /**
     * 将 LocalDateTime 格式的时间转化为格式为 yyyy-MM-dd hh:mm:ss 的 timestamp 格式
     *
     * @param localDateTime
     * @return
     */
    public static String convertLocalDateTime2Timestamp(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern(CommonConstant.SECONDS_PATTERN);

        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 将 LocalDateTime 转化为 yyyy-MM-dd HH:mm 格式的时间
     * @param localDateTime
     * @return
     */
    public static LocalDateTime convertDateFormat(LocalDateTime localDateTime) {
        String timestamp = convertLocalDateTime2Timestamp(localDateTime);
        LocalDateTime time = string2LocalDateTime(timestamp);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonConstant
                .TIME_PATTERN);

        String format = formatter.format(time);

        return LocalDateTime.parse(format, formatter);
    }

//    public static void main(String[] args) {
////        String endTime = "2019-05-07 16:36:23";
//        String endTime = "2019-05-10 15:36:23";
//        LocalDateTime parse = string2LocalDateTime(endTime);
//        LocalDateTime now = LocalDateTime.of(2019, 5, 10, 1, 32);
//        LocalDateTime time = LocalDateTime.of(2019, 5, 8, 17, 49);
//        long hours = calculateDate2Hours(parse, LocalDateTime.now());
//        System.out.println("当前时间：" + parse);
//        System.out.println("当前时间：" + LocalDateTime.now());
//        System.out.println("时间差：" + hours);
//
//
//        System.out.println("================================================");
//
//        String timestamp = convertLocalDateTime2Timestamp(LocalDateTime.now());
//        LocalDateTime localDateTime = string2LocalDateTime(timestamp);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonConstant
//                .TIME_PATTERN);
//
//        String format = formatter.format(localDateTime);
//        System.out.println("最终" + format);
//        LocalDateTime dateTime = LocalDateTime.parse(format, formatter);
//
//
//        System.out.println(12 + calculateDate2Hours(parse, dateTime));
//    }
}
