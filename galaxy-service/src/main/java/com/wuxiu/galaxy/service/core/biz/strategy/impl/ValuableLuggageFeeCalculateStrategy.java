package com.wuxiu.galaxy.service.core.biz.strategy.impl;

import com.wuxiu.galaxy.api.dto.LuggageChargeCalculationResultDTO;
import com.wuxiu.galaxy.api.dto.LuggageFeeBaseCalculationParamDTO;
import com.wuxiu.galaxy.service.core.biz.strategy.LuggageFeeCalculationStrategy;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 贵重物件计价策略
 *
 * @author: wuxiu
 * @date: 2019/4/22 10:57
 */
@Slf4j
public class ValuableLuggageFeeCalculateStrategy implements LuggageFeeCalculationStrategy {

    @Override
    public LuggageChargeCalculationResultDTO calculate(
            Integer calculateDays,
            List<LuggageFeeBaseCalculationParamDTO> calculationParamDTOS) {
        return null;
    }

    @Override
    public LuggageChargeCalculationResultDTO dailyCalculate(
            LocalDateTime startDate,
            LocalDateTime endDate,
            List<LuggageFeeBaseCalculationParamDTO> calculationParamDTOS) {
        return null;
    }
}
