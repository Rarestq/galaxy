package com.wuxiu.galaxy.service.core.biz.strategy.impl;

import com.wuxiu.galaxy.api.common.enums.CalculationUnitsEnum;
import com.wuxiu.galaxy.api.dto.LuggageChargeCalculationResultDTO;
import com.wuxiu.galaxy.api.dto.LuggageFeeBaseCalculationParamDTO;
import com.wuxiu.galaxy.api.dto.ValuableLuggageFeeCalculateParamDTO;
import com.wuxiu.galaxy.service.core.biz.strategy.LuggageFeeCalculationStrategy;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 贵重物件计价策略
 *
 * @author: wuxiu
 * @date: 2019/4/22 10:57
 */
@Slf4j
public class ValuableLuggageFeeCalculateStrategy implements LuggageFeeCalculationStrategy {

    private static final Integer HOUR_PER_DAY = 24;

    @Override
    public LuggageChargeCalculationResultDTO calculate(
            Integer calculateHours,
            List<LuggageFeeBaseCalculationParamDTO> calculationParamDTOS) {

        LuggageFeeBaseCalculationParamDTO luggageFeeBaseCalculationParamDTO =
                calculationParamDTOS.get(0);

        // 判断计价参数类型是否符合预期
        if (!(luggageFeeBaseCalculationParamDTO instanceof
                ValuableLuggageFeeCalculateParamDTO)) {
            throw new RuntimeException("Param class is " +
                    luggageFeeBaseCalculationParamDTO.getClass().getSimpleName()
                    + ", not the expected ValuableLuggageFeeCalculateParamDTO.");
        }

        // 转换类型，开始计算
        ValuableLuggageFeeCalculateParamDTO valuableLuggageFeeCalculateParamDTO =
                (ValuableLuggageFeeCalculateParamDTO) luggageFeeBaseCalculationParamDTO;

        // 获取计价单位和单位金额
        CalculationUnitsEnum calculationUnitsEnum = CalculationUnitsEnum.valueOf(
                valuableLuggageFeeCalculateParamDTO.getCalculationUnitsId());
        BigDecimal calculateFee = new BigDecimal(
                valuableLuggageFeeCalculateParamDTO.getFeePerUnit());

        StringBuilder desc = new StringBuilder();
        desc.append("计费时长").append(calculateHours).append("小时,");

        switch (calculationUnitsEnum) {
            case YUAN_PER_ITEM:
                // 元/件/次, 不处理
                desc.append("收费").append(valuableLuggageFeeCalculateParamDTO
                        .getFeePerUnit()).append("元/件/次,");
                break;
            case YUAN_EACH_HOUR:
                // 元/件/小时
                calculateFee = calculateFee.multiply(new BigDecimal(calculateHours));
                desc.append("收费").append(valuableLuggageFeeCalculateParamDTO
                        .getFeePerUnit()).append("元/件/小时,");
                break;
            default:
                throw new RuntimeException("Unknown CalculationUnitsId = " +
                        valuableLuggageFeeCalculateParamDTO.getCalculationUnitsId());
        }

        // 保留两位小数
        calculateFee = calculateFee.setScale(2, BigDecimal.ROUND_UP);
        desc.append("计算金额").append(calculateFee.toString()).append("元.");

        LuggageChargeCalculationResultDTO resultDTO =
                new LuggageChargeCalculationResultDTO();
        resultDTO.setFeeValue(calculateFee);
        resultDTO.setFeeCalculationProcessDesc(desc.toString());

        return resultDTO;
    }

    @Override
    public LuggageChargeCalculationResultDTO dailyCalculate(
            LocalDateTime startDate,
            LocalDateTime endDate,
            List<LuggageFeeBaseCalculationParamDTO> calculationParamDTOS) {

        LuggageFeeBaseCalculationParamDTO luggageFeeBaseCalculationParamDTO =
                calculationParamDTOS.get(0);

        // 判断计价参数类型是否符合预期
        if (!(luggageFeeBaseCalculationParamDTO instanceof
                ValuableLuggageFeeCalculateParamDTO)) {
            throw new RuntimeException("Param class is " +
                    luggageFeeBaseCalculationParamDTO.getClass().getSimpleName()
                    + ", not the expected ValuableLuggageFeeCalculateParamDTO.");
        }

        // 转换类型，开始计算
        ValuableLuggageFeeCalculateParamDTO valuableLuggageFeeCalculateParamDTO =
                (ValuableLuggageFeeCalculateParamDTO) luggageFeeBaseCalculationParamDTO;

        // 获取计价单位和单位金额
        CalculationUnitsEnum calculationUnitsEnum = CalculationUnitsEnum.valueOf(
                valuableLuggageFeeCalculateParamDTO.getCalculationUnitsId());
        BigDecimal calculateFee = new BigDecimal(
                valuableLuggageFeeCalculateParamDTO.getFeePerUnit());

        if (Objects.isNull(calculationUnitsEnum)) {
            throw new RuntimeException("Unknown CalculationUnitsId = " +
                    valuableLuggageFeeCalculateParamDTO.getCalculationUnitsId());
        }

        StringBuilder desc = new StringBuilder();
        desc.append("计费开始日期：").append(startDate.toString()).append(",结束日期：")
                .append(endDate.toString()).append(",计费规则：");
        desc.append("收费").append(valuableLuggageFeeCalculateParamDTO.getFeePerUnit())
                .append(calculationUnitsEnum.getDesc());

        calculateFee = calculateFee.setScale(2, BigDecimal.ROUND_UP);

        LuggageChargeCalculationResultDTO resultDTO =
                new LuggageChargeCalculationResultDTO();
        resultDTO.setFeeValue(calculateFee);
        resultDTO.setFeeCalculationProcessDesc(desc.toString());
        resultDTO.setCalculationUnitsId(
                valuableLuggageFeeCalculateParamDTO.getCalculationUnitsId());

        return resultDTO;
    }
}
