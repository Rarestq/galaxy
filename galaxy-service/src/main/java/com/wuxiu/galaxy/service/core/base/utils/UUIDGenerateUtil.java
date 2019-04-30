package com.wuxiu.galaxy.service.core.base.utils;

import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

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
     * 格式化的时间字符串
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyyMMddHHmmss");

    /**
     * 获取当前时间年月日时分秒毫秒字符串
     *
     * @return
     */
    private static String getNowDateStr() {
        return sdf.format(new Date());
    }

    /**
     * 生成唯一编号
     *
     * @param prefix
     * @return
     */
    public static String generateUniqueNo(final String prefix) {
        StringBuilder stringBuilder = new StringBuilder();
        String dateStr = getNowDateStr();
        stringBuilder.append(prefix).append(dateStr);

        return stringBuilder.toString();
    }

}
