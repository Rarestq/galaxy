package com.wuxiu.galaxy.web.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 计费规则页面展示对象
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:41
 */
@ApiModel("计费规则页面展示对象")
@Data
public class ChargeCalculationRuleVO implements Serializable {

    private static final long serialVersionUID = -4463083838715541545L;

    @ApiModelProperty(value = "详情的规则id", required = true)
    private Long detailRuleId;

    @ApiModelProperty(value = "计费方式id", required = true)
    private Long calculationTypeId;

    @ApiModelProperty(value = "计费方式名称", required = true)
    private String calculationTypeName;

    @ApiModelProperty(value = "计费单位ID", required = true)
    private Integer calculationUnitsId;

    @ApiModelProperty(value = "计费单位名称", required = true)
    private String calculationUnitsName;

    @ApiModelProperty(value = "每个计费单位的金额", required = true)
    private String feePerUnit;

    @ApiModelProperty(value = "计费周期", required = true)
    private Integer calculationPeriod;

    @ApiModelProperty(value = "计费开始天数", required = true)
    private Integer calculationStartDay;

    @ApiModelProperty(value = "计费结束天数", required = true)
    private Integer calculationEndDay;

    @ApiModelProperty(value = "最低金额,单位元", required = false)
    private String minFee;

    @ApiModelProperty(value = "最高金额,单位元", required = false)
    private String maxFee;
}
