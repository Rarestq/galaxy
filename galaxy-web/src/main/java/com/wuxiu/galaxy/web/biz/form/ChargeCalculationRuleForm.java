package com.wuxiu.galaxy.web.biz.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 计费规则表单
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:41
 */
@ApiModel("计费规则表单")
@Data
public class ChargeCalculationRuleForm implements Serializable {

    private static final long serialVersionUID = -4463083838715541545L;

    private Long calculationRuleId;

    @NotNull(message = "计费方式不能为空")
    private Long calculationType;

    @NotNull(message = "计费单位ID不能为空")
    private Integer calculationUnitsId;

    @NotNull(message = "每个计费单位的金额不能为空")
    private String feePerUnit;

    /**
     * 计费周期
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
