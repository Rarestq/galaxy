package com.wuxiu.galaxy.web.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 计费模板页面展示对象
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:40
 */
@ApiModel("计费模板页面展示对象")
@Data
public class ChargeTemplateVO implements Serializable {

    private static final long serialVersionUID = -3221598513694571896L;

    @ApiModelProperty(value = "模板ID", required = true)
    private Long chargeTemplateId;

    @ApiModelProperty(value = "费用类型ID", required = true)
    private Long chargeTypeId;

    @ApiModelProperty(value = "费用类型ID", required = true)
    private String chargeTypeName;

    @ApiModelProperty(value = "最低金额,单位元", required = false)
    private String minFee;

    @ApiModelProperty(value = "最高金额,单位元", required = false)
    private String maxFee;

    @ApiModelProperty(value = "计费规则列表", required = true)
    private List<ChargeCalculationRuleVO> chargeCalculationRuleVOS;
}
