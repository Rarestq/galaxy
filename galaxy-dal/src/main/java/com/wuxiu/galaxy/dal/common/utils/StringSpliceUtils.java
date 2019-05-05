package com.wuxiu.galaxy.dal.common.utils;

import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 字符串拼接工具类
 *
 * @author: wuxiu
 * @date: 2019/5/5 14:27
 */
@NoArgsConstructor
public class StringSpliceUtils {

    /**
     * 获取计费规则描述
     *
     * @param luggageTypeId
     * @return
     */
    public static String getChargeCalculateRule(Long luggageTypeId) {
        StringBuilder builder = new StringBuilder();
        String luggageTypeDesc = Objects.requireNonNull(LuggageTypeEnum
                .valueOf(luggageTypeId)).getDesc();
        builder.append(luggageTypeId)
                .append("-")
                .append(luggageTypeDesc)
                .append("计费规则");

        return builder.toString();

    }
}
