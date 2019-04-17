package com.wuxiu.galaxy.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 计费规则删除对象
 *
 * @author: wuxiu
 * @date: 2019/4/17 15:11
 */
@ApiModel("ChargeRuleDeleteDTO")
@Data
public class ChargeRuleDeleteDTO {

    @ApiModelProperty(value = "计费规则主键id", required = true)
    @NotNull(message = "计费规则主键id不能为空")
    private Long chargeRuleId;

    private Integer chargeRuleType;
}
