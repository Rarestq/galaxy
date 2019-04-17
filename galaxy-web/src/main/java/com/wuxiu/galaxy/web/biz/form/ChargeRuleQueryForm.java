package com.wuxiu.galaxy.web.biz.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 计费规则查询表单
 *
 * @author: wuxiu
 * @date: 2019/4/17 15:04
 */
@ApiModel("计费规则查询表单")
@Data
public class ChargeRuleQueryForm implements Serializable {

    private static final long serialVersionUID = 6736554433098662964L;

    @ApiModelProperty(value = "计费规则名称", required = false)
    private String chargeRuleName;

    @ApiModelProperty(value = "计费规则类型", required = false)
    private Integer chargeRuleType;

}