package com.wuxiu.galaxy.service.core.base.utils;

import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 唯一 No 生成器（单例模式）
 *
 * @author: wuxiu
 * @date: 2019/4/14 21:02
 */
@NoArgsConstructor
public class UUIDGenerateUtil {

    /**
     * 创建一个空实例对象，类需要用的时候才赋值
     */
    private static final UUIDGenerateUtil generateUtil = new UUIDGenerateUtil();

    /**
     * 获取当前时间年月日时分秒毫秒字符串
     *
     * @return
     */
    private static String getNowDateStr() {
        /**
         * 格式化的时间字符串
         */
        final SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyyMMddHHmmssS");

        return sdf.format(new Date());
    }

    /**
     * 生成唯一编号
     *
     * @param prefix
     * @return
     */
    public static String generateUniqueNo(String prefix) {
        StringBuilder stringBuilder = new StringBuilder();
        String dateStr = getNowDateStr();
        stringBuilder.append(prefix).append(dateStr).append(
                new Random().nextInt(10000));

        return stringBuilder.toString();
    }

}
