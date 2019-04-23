package com.wuxiu.galaxy.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 行李寄存费用计算结果
 *
 * @author: wuxiu
 * @date: 2019/4/22 14:14
 */
@ApiModel(description = "行李寄存费用计算结果")
@Data
public class LuggageChargeCalculationResultDTO implements Serializable {

    private static final long serialVersionUID = 1728946635031270027L;

    /**
     * 计费单位Id
     */
    private Long calculationUnitsId;

    /**
     * 费用值
     */
    private BigDecimal feeValue;

    /**
     * 费用计算过程描述
     */
    private String feeCalculationProcessDesc;
}
