package com.wuxiu.galaxy.dal.common.utils;

import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import lombok.NoArgsConstructor;

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
        String luggageTypeDesc = LuggageTypeEnum.getDescByCode(luggageTypeId);
        builder.append(luggageTypeDesc)
                .append("-")
                .append("计费规则");

        return builder.toString();

    }

    public static String getChargeCalculateRule(Long luggageTypeId, Integer unitsId) {
        StringBuilder builder = new StringBuilder();
        String luggageTypeDesc = LuggageTypeEnum.getDescByCode(luggageTypeId);
        builder.append(luggageTypeDesc)
                .append("-")
                .append("计费规则")
                .append(unitsId);

        return builder.toString();

    }
}
