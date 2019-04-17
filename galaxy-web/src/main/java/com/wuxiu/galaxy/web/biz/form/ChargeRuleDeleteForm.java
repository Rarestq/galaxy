package com.wuxiu.galaxy.web.biz.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 计费规则删除表单
 *
 * @author: wuxiu
 * @date: 2019/4/17 15:11
 */
@ApiModel("计费规则删除表单")
@Data
public class ChargeRuleDeleteForm {

    @ApiModelProperty(value = "计费规则主键id", required = true)
    @NotNull(message = "计费规则主键id不能为空")
    private Long chargeRuleId;

    private Integer chargeRuleType;
}
