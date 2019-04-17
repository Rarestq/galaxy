package com.wuxiu.galaxy.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 计费规则对象
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:37
 */
@ApiModel("计费规则对象")
@Data
public class ChargeRuleDTO implements Serializable {

    private static final long serialVersionUID = -4545898179001725877L;

    /**
     * 计费规则主键id
     */
    @ApiModelProperty(value = "计费规则主键id", required = true)
    private Long chargeRuleId;
    /**
     * 计费规则名称
     */
    @ApiModelProperty(value = "计费规则名称", required = true)
    private String chargeRuleName;
    /**
     * 计费模板列表
     */
    @ApiModelProperty(value = "计费模板列表", required = true)
    private List<ChargeTemplateDTO> chargeTemplateDTOS;


}
