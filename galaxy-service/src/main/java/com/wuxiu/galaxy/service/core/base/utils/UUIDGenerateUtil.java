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
     * 行李寄存记录编号前缀
     */
    private static final String LUGGAGE_STORAGE_RECORD_PREFIX = "XLJC";

    /**
     * 普通管理员编号前缀
     */
    private static final String ADMIN_PREFIX = "ADMIN";

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
     * 获取行李寄存记录编号
     *
     * @return
     */
    public static String genStorageRecordNo() {
        //编号 = 前缀 + 当前时间(年月日时分秒)
        StringBuilder builder = new StringBuilder();
        String dateStr = getNowDateStr();
        builder.append(LUGGAGE_STORAGE_RECORD_PREFIX).append(dateStr);

        return builder.toString();
    }

    /**
     * 获取普通管理员编号
     *
     * @return
     */
    public static String genAdminNo() {
        //编号 = 前缀 + 当前时间(年月日时分秒)
        StringBuilder builder = new StringBuilder();
        String dateStr = getNowDateStr();
        builder.append(ADMIN_PREFIX).append(dateStr);

        return builder.toString();
    }

    public static void main(String[] args) {
        String s = genStorageRecordNo();
        System.out.println("编号为：" + s);
    }
}
