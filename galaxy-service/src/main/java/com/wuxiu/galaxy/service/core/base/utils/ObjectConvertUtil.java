package com.wuxiu.galaxy.service.core.base.utils;

import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * 对象转化工具类
 *
 * @author: wuxiu
 * @date: 2019/5/6 15:59
 */
@NoArgsConstructor
public class ObjectConvertUtil {


    /**
     * 删除管理员信息时，adminIds -> List<Long> adminId
     *
     * @param str
     * @param regex
     * @return
     */
    public static List<Long> string2Long(String str, String regex) {
        String substring;
        if (str.contains(regex)) {
            substring = str.substring(13, str.length() - 2);
        } else {
            substring = str.substring(12, str.length() - 1);
        }

        Long[] array;

        String[] strs = substring.split(regex);
        array = new Long[strs.length];
        for (int i = 0; i < strs.length; i++) {
            array[i] = Long.valueOf(strs[i]);
        }

        return Arrays.asList(array);
    }

    /**
     * 取件操作时，"luggageIds"(String) -> List<Long> luggageId(Long)
     *
     * @param str
     * @param regex
     * @return
     */
    public static List<Long> luggageIdFromString2Long(String str, String regex) {
        String subStr;
        if (str.contains(regex)) {
            subStr = str.substring(15, str.length() - 2);
        } else {
            subStr = str.substring(14, str.length() - 1);
        }

        Long[] array;

        String[] strs = subStr.split(regex);
        array = new Long[strs.length];
        for (int i = 0; i < strs.length; i++) {
            array[i] = Long.valueOf(strs[i]);
        }

        return Arrays.asList(array);
    }

    /**
     * 对遗失行李进行赔偿时：lostRegistRecordIds -> List<Long> lostRegistRecordId
     *
     * @param str
     * @param regex
     * @return
     */
    public static List<Long> lostRegisterRecordIdsIdFromString2Long(String str,
                                                                    String regex) {
        String substring;
        if (str.contains(regex)) {
            substring = str.substring(24, str.length() - 2);
        } else {
            substring = str.substring(23, str.length() - 1);
        }

        Long[] array;

        String[] strs = substring.split(regex);
        array = new Long[strs.length];
        for (int i = 0; i < strs.length; i++) {
            array[i] = Long.valueOf(strs[i]);
        }

        return Arrays.asList(array);
    }
}
