package com.wuxiu.galaxy.service.core.biz.strategy.impl;

import com.wuxiu.galaxy.api.common.enums.CalculationUnitsEnum;
import com.wuxiu.galaxy.api.dto.LuggageChargeCalculationResultDTO;
import com.wuxiu.galaxy.api.dto.LuggageFeeBaseCalculationParamDTO;
import com.wuxiu.galaxy.service.core.biz.strategy.LuggageFeeCalculationStrategy;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 固定计费策略
 *
 * @author: wuxiu
 * @date: 2019/4/30 09:31
 */
@Slf4j
public class LuggageFixedFeeCalculationStrategy implements LuggageFeeCalculationStrategy {

    private static final Integer HOUR_PER_DAY = 24;

    @Override
    public LuggageChargeCalculationResultDTO calculate(
            Integer calculateDays,
            List<LuggageFeeBaseCalculationParamDTO> calculationParamDTOS) {

        LuggageFeeBaseCalculationParamDTO luggageFeeBaseCalculationParamDTO =
                calculationParamDTOS.get(0);
        // 获取计价单位和单位金额
        CalculationUnitsEnum calculationUnitsEnum = CalculationUnitsEnum.valueOf(
                luggageFeeBaseCalculationParamDTO.getCalculationUnitsId());
        BigDecimal calculateFee = new BigDecimal(
                luggageFeeBaseCalculationParamDTO.getFeePerUnit());

        StringBuilder desc = new StringBuilder();
        desc.append("计费时长").append(calculateDays).append("天,");

        switch (calculationUnitsEnum) {
            case YUAN_PER_ITEM:
                // 元/件/次, 不处理
                desc.append("收费").append(luggageFeeBaseCalculationParamDTO
                        .getFeePerUnit()).append("元/件/次,");
                break;
            case YUAN_EACH_DAY:
                // 元/件/天
                calculateFee = calculateFee.multiply(new BigDecimal(calculateDays));
                desc.append("收费").append(luggageFeeBaseCalculationParamDTO
                        .getFeePerUnit()).append("元/件/天,");
                break;
//            case YUAN_EACH_HOUR:
//                // 元/件/小时
//                calculateFee = calculateFee.multiply(new BigDecimal(calculateDays)
//                        .multiply(new BigDecimal(HOUR_PER_DAY)));
//                break;
            default:
                throw new RuntimeException("Unknown CalculationUnitsId = " +
                        luggageFeeBaseCalculationParamDTO.getCalculationUnitsId());
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

        // 获取计价单位和单位金额
        CalculationUnitsEnum calculationUnitsEnum = CalculationUnitsEnum.valueOf(
                luggageFeeBaseCalculationParamDTO.getCalculationUnitsId());
        BigDecimal calculateFee = new BigDecimal(
                luggageFeeBaseCalculationParamDTO.getFeePerUnit());

        if (Objects.isNull(calculationUnitsEnum)) {
            throw new RuntimeException("Unknown CalculationUnitsId = " +
                    luggageFeeBaseCalculationParamDTO.getCalculationUnitsId());
        }

        StringBuilder desc = new StringBuilder();
        desc.append("计费开始日期：").append(startDate.toString()).append(",结束日期：")
                .append(endDate.toString()).append(",计费规则：");
        desc.append("收费").append(luggageFeeBaseCalculationParamDTO.getFeePerUnit())
                .append(calculationUnitsEnum.getDesc());

        calculateFee = calculateFee.setScale(2, BigDecimal.ROUND_UP);

        LuggageChargeCalculationResultDTO resultDTO =
                new LuggageChargeCalculationResultDTO();
        resultDTO.setFeeValue(calculateFee);
        resultDTO.setFeeCalculationProcessDesc(desc.toString());
        resultDTO.setCalculationUnitsId(
                luggageFeeBaseCalculationParamDTO.getCalculationUnitsId());

        return resultDTO;
    }
}
