package com.wuxiu.galaxy.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 计费规则数据对象
 *
 * @author: wuxiu
 * @date: 2019/5/7 22:58
 */
@ApiModel(description = "计费规则数据对象")
@Data
public class ChargeCalculateRuleDTO implements Serializable {

    private static final long serialVersionUID = 6210973850986150890L;

    /**
     * 计费规则主键id
     */
    @ApiModelProperty(value = "计费规则主键id", required = true)
    private Long calculationRuleId;
    /**
     * 行李类型主键id
     */
    @ApiModelProperty(value = "行李类型主键id", required = true)
    private Long luggageTypeId;
    /**
     * 计费单位(1-元/件/天，2-元/件/次，3-元/件)
     */
    @ApiModelProperty(value = "计费单位(1-元/件/天，2-元/件/次，3-元/件)", required = true)
    private Integer calculationUnitsId;
    /**
     * 单位金额
     */
    @ApiModelProperty(value = "单位金额", required = true)
    private String feePerUnit;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    private String gmtCreate;

}
