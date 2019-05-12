package com.wuxiu.galaxy.service.core.biz.strategy.impl;

import com.wuxiu.galaxy.api.common.enums.CalculationUnitsEnum;
import com.wuxiu.galaxy.api.dto.FragileLuggageFeeCalculateParamDTO;
import com.wuxiu.galaxy.api.dto.LuggageChargeCalculationResultDTO;
import com.wuxiu.galaxy.api.dto.LuggageFeeBaseCalculationParamDTO;
import com.wuxiu.galaxy.service.core.biz.strategy.LuggageFeeCalculationStrategy;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 易碎物件计价策略
 *
 * @author: wuxiu
 * @date: 2019/4/22 10:56
 */
@Slf4j
public class FragileLuggageFeeCalculateStrategy implements LuggageFeeCalculationStrategy {

    @Override
    public LuggageChargeCalculationResultDTO calculate(
            Integer calculateHours,
            List<LuggageFeeBaseCalculationParamDTO> calculationParamDTOS) {

        LuggageFeeBaseCalculationParamDTO luggageFeeBaseCalculationParamDTO =
                calculationParamDTOS.get(0);

        // 判断计价参数类型是否符合预期
        if (!(luggageFeeBaseCalculationParamDTO instanceof
                FragileLuggageFeeCalculateParamDTO)) {
            throw new RuntimeException("Param class is " +
                    luggageFeeBaseCalculationParamDTO.getClass().getSimpleName()
                    + ", not the expected FragileLuggageFeeCalculateParamDTO.");
        }

        // 转换类型，开始计算
        FragileLuggageFeeCalculateParamDTO fragileLuggageFeeCalculateParamDTO =
                (FragileLuggageFeeCalculateParamDTO) luggageFeeBaseCalculationParamDTO;

        // 获取计价单位和单位金额
        CalculationUnitsEnum calculationUnitsEnum = CalculationUnitsEnum.valueOf(
                fragileLuggageFeeCalculateParamDTO.getCalculationUnitsId());
        BigDecimal calculateFee = new BigDecimal(
                fragileLuggageFeeCalculateParamDTO.getFeePerUnit());

        StringBuilder desc = new StringBuilder();
        desc.append("计费时长").append(calculateHours).append("小时,");

        switch (calculationUnitsEnum) {
            case YUAN_PER_ITEM:
                // 元/件/次, 不处理
                desc.append("收费 ￥").append(fragileLuggageFeeCalculateParamDTO
                        .getFeePerUnit()).append("元/件/次,");
                break;
            case YUAN_EACH_HOUR:
                // 元/件/小时
                desc.append("收费 ￥").append(calculateFee).append("元/件/小时,");
                calculateFee = calculateFee.multiply(new BigDecimal(calculateHours))
                        .multiply(new BigDecimal(9/10));
                break;
            default:
                throw new RuntimeException("Unknown CalculationUnitsId = " +
                        fragileLuggageFeeCalculateParamDTO.getCalculationUnitsId());
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
                FragileLuggageFeeCalculateParamDTO)) {
            throw new RuntimeException("Param class is " +
                    luggageFeeBaseCalculationParamDTO.getClass().getSimpleName()
                    + ", not the expected FragileLuggageFeeCalculateParamDTO.");
        }

        // 转换类型，开始计算
        FragileLuggageFeeCalculateParamDTO fragileLuggageFeeCalculateParamDTO =
                (FragileLuggageFeeCalculateParamDTO) luggageFeeBaseCalculationParamDTO;

        // 获取计价单位和单位金额
        CalculationUnitsEnum calculationUnitsEnum = CalculationUnitsEnum.valueOf(
                fragileLuggageFeeCalculateParamDTO.getCalculationUnitsId());
        BigDecimal calculateFee = new BigDecimal(
                fragileLuggageFeeCalculateParamDTO.getFeePerUnit());

        if (Objects.isNull(calculationUnitsEnum)) {
            throw new RuntimeException("Unknown CalculationUnitsId = " +
                    fragileLuggageFeeCalculateParamDTO.getCalculationUnitsId());
        }

        StringBuilder desc = new StringBuilder();
        desc.append("计费开始日期：").append(startDate.toString()).append(",结束日期：")
                .append(endDate.toString()).append(",计费规则：");
        desc.append("收费").append(fragileLuggageFeeCalculateParamDTO.getFeePerUnit())
                .append(calculationUnitsEnum.getDesc());

        calculateFee = calculateFee.setScale(2, BigDecimal.ROUND_UP);

        LuggageChargeCalculationResultDTO resultDTO =
                new LuggageChargeCalculationResultDTO();
        resultDTO.setFeeValue(calculateFee);
        resultDTO.setFeeCalculationProcessDesc(desc.toString());
        resultDTO.setCalculationUnitsId(
                fragileLuggageFeeCalculateParamDTO.getCalculationUnitsId());

        return resultDTO;
    }
}
