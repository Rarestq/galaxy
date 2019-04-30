package com.wuxiu.galaxy.service.core.biz.strategy.impl;

import com.wuxiu.galaxy.api.dto.CommonLuggageFeeCalculateParamDTO;
import com.wuxiu.galaxy.api.dto.LuggageChargeCalculationResultDTO;
import com.wuxiu.galaxy.api.dto.LuggageFeeBaseCalculationParamDTO;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * 普通物件计费策略
 *
 * @author: wuxiu
 * @date: 2019/4/22 10:55
 */
@Slf4j
public class CommonLuggageFeeCalculateStrategy extends LuggageFixedFeeCalculationStrategy {

    @Override
    public LuggageChargeCalculationResultDTO calculate(
            Integer calculateDays,
            List<LuggageFeeBaseCalculationParamDTO> calculationParamDTOS) {

        LuggageFeeBaseCalculationParamDTO luggageFeeBaseCalculationParamDTO =
                calculationParamDTOS.get(0);

        // 判断计价参数类型是否符合预期
        if (!(luggageFeeBaseCalculationParamDTO instanceof
                CommonLuggageFeeCalculateParamDTO)) {
            throw new RuntimeException("Param class is " +
                    luggageFeeBaseCalculationParamDTO.getClass().getSimpleName()
                    + ", not the expected CommonLuggageFeeCalculateParamDTO.");
        }

        // 转换类型，开始计算
        CommonLuggageFeeCalculateParamDTO commonLuggageFeeCalculateParamDTO =
                (CommonLuggageFeeCalculateParamDTO) luggageFeeBaseCalculationParamDTO;

        return super.calculate(calculateDays,
                Collections.singletonList(commonLuggageFeeCalculateParamDTO));
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
                CommonLuggageFeeCalculateParamDTO)) {
            throw new RuntimeException("Param class is " +
                    luggageFeeBaseCalculationParamDTO.getClass().getSimpleName()
                    + ", not the expected CommonLuggageFeeCalculateParamDTO.");
        }

        // 转换类型，开始计算
        CommonLuggageFeeCalculateParamDTO commonLuggageFeeCalculateParamDTO =
                (CommonLuggageFeeCalculateParamDTO) luggageFeeBaseCalculationParamDTO;

        return super.dailyCalculate(startDate, endDate, Collections.singletonList(
                commonLuggageFeeCalculateParamDTO));
    }
}
