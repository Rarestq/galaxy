package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

/**
 * 行李类型对应的计费规则
 *
 * @author: wuxiu
 * @date: 2019/5/24 17:00
 */
@Getter
@AllArgsConstructor
@ToString
public enum LuggageType2CalculateRuleEnum {

    // 行李类型对应的计费规则
    COMMON(1L, 1L),
    FRAGILE(2L, 3L),
    VALUABLE(3L, 5L)
    ;

    private Long luggageTypeId;
    private Long calculateRuleId;

    /**
     * 将code包装成枚举类型
     *
     * @param luggageTypeId
     * @return
     */
    public static LuggageType2CalculateRuleEnum valueOf(Long luggageTypeId) {
        if (Objects.isNull(luggageTypeId)) {
            return null;
        }

        for (LuggageType2CalculateRuleEnum ruleEnum :
                LuggageType2CalculateRuleEnum.values()) {

            if (ruleEnum.getLuggageTypeId().equals(luggageTypeId)) {
                return ruleEnum;
            }
        }
        return null;
    }

    /**
     * 通过 行李类型id 得到 计费规则id
     *
     * @param luggageTypeId
     * @return
     */
    public static Long getRuleIdByLuggageTypeId(Long luggageTypeId) {
        if (Objects.isNull(luggageTypeId)) {
            return null;
        }

        LuggageType2CalculateRuleEnum[] arr = values();
        for (LuggageType2CalculateRuleEnum ruleEnum : arr) {
            if (ruleEnum.getLuggageTypeId().equals(luggageTypeId)) {
                return ruleEnum.getCalculateRuleId();
            }
        }
        return null;
    }
}
