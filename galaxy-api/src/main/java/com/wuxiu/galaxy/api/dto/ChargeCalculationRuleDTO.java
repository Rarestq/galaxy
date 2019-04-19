package com.wuxiu.galaxy.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 计算规则对象
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:41
 */
@ApiModel("计算规则对象")
@Data
public class ChargeCalculationRuleDTO implements Serializable {

    private static final long serialVersionUID = -4463083838715541545L;

    @NotNull(message = "计算规则主键id不能为空")
    private Long calculationRuleId;

    @NotNull(message = "计算类型不能为空")
    private Integer calculationType;

    @NotNull(message = "计费单位ID不能为空")
    private Integer calculationUnitsId;

    @NotNull(message = "每个计费单位的金额不能为空")
    private String feePerUnit;

    /**
     * 计算周期(单位：天)
     */
    private Integer calculationPeriod;

    /**
     * 计费开始天数
     */
    private Integer calculationStartDay;

    /**
     * 计费结束天数
     */
    private Integer calculationEndDay;

    private String minFee;

    private String maxFee;
}
