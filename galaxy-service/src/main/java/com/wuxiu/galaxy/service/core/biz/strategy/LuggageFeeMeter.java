package com.wuxiu.galaxy.service.core.biz.strategy;

import com.wuxiu.galaxy.api.dto.LuggageChargeCalculationResultDTO;
import com.wuxiu.galaxy.api.dto.LuggageFeeBaseCalculationParamDTO;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 行李费用计价器
 *
 * @author: wuxiu
 * @date: 2019/4/22 10:57
 */
@AllArgsConstructor
public class LuggageFeeMeter {

    private LuggageFeeCalculationStrategy strategy;
    private List<LuggageFeeBaseCalculationParamDTO> baseCalculationParamDTOS;

    public LuggageChargeCalculationResultDTO calculate(int calculateHours) {
        return strategy.calculate(calculateHours, baseCalculationParamDTOS);
    }

    public LuggageChargeCalculationResultDTO dailyFeeCalculate(LocalDateTime startDate,
                                                               LocalDateTime endDate) {
        return strategy.dailyCalculate(startDate, endDate, baseCalculationParamDTOS);
    }
}
