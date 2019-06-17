package com.wuxiu.galaxy.web.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 计费规则展示对象
 *
 * @author: wuxiu
 * @date: 2019/5/7 22:55
 */
@ApiModel("计费规则展示对象")
@Data
public class ChargeCalculateRuleVO implements Serializable {

    private static final long serialVersionUID = 2243939964870479813L;

    /**
     * 计费规则主键id
     */
    @ApiModelProperty(value = "计费规则主键id", required = true)
    private Long calculationRuleId;
    /**
     * 计费规则描述
     */
    @ApiModelProperty(value = "计费规则描述", required = true)
    private String calculateRuleDesc;
    /**
     * 行李类型
     */
    @ApiModelProperty(value = "行李类型", required = true)
    private String luggageType;
    /**
     * 计费单位(1-元/件/天，2-元/件/次，3-元/件)
     */
    @ApiModelProperty(value = "计费单位(1-元/件/天，2-元/件/次，3-元/件)", required = true)
    private String calculationUnits;
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
