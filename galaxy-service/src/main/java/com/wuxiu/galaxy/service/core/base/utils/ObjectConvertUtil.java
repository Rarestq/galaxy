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
     * 字符串数组转化为Long数组
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
}
