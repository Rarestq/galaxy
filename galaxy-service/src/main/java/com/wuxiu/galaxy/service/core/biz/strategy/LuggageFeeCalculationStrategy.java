package com.wuxiu.galaxy.service.core.biz.strategy;

import com.wuxiu.galaxy.api.dto.LuggageChargeCalculationResultDTO;
import com.wuxiu.galaxy.api.dto.LuggageFeeBaseCalculationParamDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 行李寄存计价策略接口
 *
 * @author: wuxiu
 * @date: 2019/4/22 10:47
 */
public interface LuggageFeeCalculationStrategy {

    /**
     * 按照寄存天数
     *
     * @param calculateDays          计算天数
     * @param calculationParamDTOS   计算参数
     * @return
     */
    LuggageChargeCalculationResultDTO calculate(
            Integer calculateDays,
            List<LuggageFeeBaseCalculationParamDTO> calculationParamDTOS);

    /**
     * 按照寄存日期进行计费
     *
     * @param startDate                寄存开始日期
     * @param endDate                  寄存结束日期
     * @param calculationParamDTOS     计算参数
     * @return
     */
    LuggageChargeCalculationResultDTO dailyCalculate(
            LocalDateTime startDate, LocalDateTime endDate,
            List<LuggageFeeBaseCalculationParamDTO> calculationParamDTOS);
}
