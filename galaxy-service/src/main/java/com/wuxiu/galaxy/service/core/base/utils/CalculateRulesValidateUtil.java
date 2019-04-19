package com.wuxiu.galaxy.service.core.base.utils;

import com.wuxiu.galaxy.api.common.enums.CalculationUnitsEnum;
import com.wuxiu.galaxy.api.common.enums.ChargeCalculationTypeEnum;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.dto.ChargeCalculationRuleDTO;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 计算规则参数校验工具类
 *
 * @author: wuxiu
 * @date: 2019/4/19 14:17
 */
@NoArgsConstructor
public class CalculateRulesValidateUtil {

    /**
     * 周期计算规则参数校验
     *
     * @param calculationRuleDTO
     */
    public static void validateCycleCalculateParams(
            ChargeCalculationRuleDTO calculationRuleDTO) {

        if (Objects.isNull(calculationRuleDTO)) {
            throw new ParamException("计算规则不能为空");
        }

        if (!Objects.equals(calculationRuleDTO.getCalculationType(),
                ChargeCalculationTypeEnum.CYCLE_CHARGE_CALCULATION.getCode())) {
            throw new ParamException("周期计算规则类型错误");
        }

        if (Objects.isNull(CalculationUnitsEnum.valueOf(
                calculationRuleDTO.getCalculationUnitsId()))) {
            throw new ParamException("计费单位错误");
        }

        Integer calculationPeriod = calculationRuleDTO.getCalculationPeriod();
        if (Objects.isNull(calculationPeriod) || calculationPeriod <= 0) {
            throw new ParamException("计算周期数值输入错误");
        }

        String feePerUnit = calculationRuleDTO.getFeePerUnit();
        if (Objects.isNull(feePerUnit) ||
                !AlphabeticNumericUtil.isDoubleString(feePerUnit.trim())) {
            throw new ParamException("单位金额输入错误");
        }
    }

    /**
     * 固定计算规则参数校验
     *
     * @param calculationRuleDTO
     */
    public static void validateFixedCalculateParams(
            ChargeCalculationRuleDTO calculationRuleDTO) {

        if (Objects.isNull(calculationRuleDTO)) {
            throw new ParamException("计算规则不能为空");
        }

        if (!Objects.equals(calculationRuleDTO.getCalculationType(),
                ChargeCalculationTypeEnum.FIXED_CHARGE_CALCULATION.getCode())) {
            throw new ParamException("固定计算规则类型错误");
        }

        if (Objects.isNull(CalculationUnitsEnum.valueOf(
                calculationRuleDTO.getCalculationUnitsId()))) {
            throw new ParamException("计费单位错误");
        }

        Integer calculationPeriod = calculationRuleDTO.getCalculationPeriod();
        if (Objects.isNull(calculationPeriod) || calculationPeriod <= 0) {
            throw new ParamException("计算周期数值输入错误");
        }

        String feePerUnit = calculationRuleDTO.getFeePerUnit();
        if (Objects.isNull(feePerUnit) ||
                !AlphabeticNumericUtil.isDoubleString(feePerUnit.trim())) {
            throw new ParamException("单位金额输入错误");
        }

        String maxFee = calculationRuleDTO.getMaxFee();
        String minFee = calculationRuleDTO.getMinFee();

        if (Objects.nonNull(maxFee) && Objects.nonNull(minFee)
                && (new BigDecimal(maxFee).compareTo(new BigDecimal(minFee)) < 0)) {
            throw new ParamException("最高收费应该大于等于最低收费");
        }
        //todo:对计费开始和结束天数进行校验

    }

}
