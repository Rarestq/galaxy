package com.wuxiu.galaxy.service.core.base.utils;

import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

/**
 * 字母、数字工具类
 *
 * @author wuxiu
 */
@NoArgsConstructor
public class AlphabeticNumericUtil {

    /**
     * 判断是否是纯字母、数字组成的字符串
     *
     * @param str
     * @return
     */
    public static boolean isAlphabeticNumericString(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        char[] chars = str.toCharArray();
        for (char ch : chars) {
            if (!isNumericChar(ch) && !isAlphabeticChar(ch)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断char是否是属于数字范围[0-9]
     *
     * @param ch
     * @return
     */
    public static boolean isNumericChar(char ch) {
        return '0' <= ch && '9' >= ch;
    }

    /**
     * 判断char是否是属于小写字母范围[A-Z]
     *
     * @param ch
     * @return
     */
    public static boolean isLowercaseAlphabeticChar(char ch) {
        return 'a' <= ch && 'z' >= ch;
    }

    /**
     * 判断char是否是属于大写字母范围[a-z]
     *
     * @param ch
     * @return
     */
    public static boolean isUppercaseAlphabeticChar(char ch) {
        return 'A' <= ch && 'Z' >= ch;
    }

    /**
     * 判断char是否是属于字母范围[a-zA-Z]
     *
     * @param ch
     * @return
     */
    public static boolean isAlphabeticChar(char ch) {
        return isUppercaseAlphabeticChar(ch) || isLowercaseAlphabeticChar(ch);
    }

    /**
     * 判断输入的字符串是否是Double类型的
     *
     * @param str
     * @return
     */
    public static boolean isDoubleString(String str) {
        try {
            double strToDouble = Double.parseDouble(str);
            if (!ObjectUtils.isEmpty(strToDouble)) {
                return true;
            } else {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }
    }
}

