package com.wuxiu.galaxy.api.dto;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 计费模板对象
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:40
 */
@ApiModel("计费模板对象")
@Data
public class ChargeTemplateDTO implements Serializable {

    private static final long serialVersionUID = -3221598513694571896L;

    @NotNull(message = "计费模板主键id不能为空")
    private Long chargeTemplateId;

    @NotNull(message = "计费模板编号不能为空")
    private String chargeTemplateNo;

    @NotNull(message = "计费模板名称不能为空")
    private String chargeTemplateName;

    @NotNull(message = "费用类型ID不能为空")
    private Long chargeTypeId;

    private String minFee;

    private String maxFee;

    @NotNull(message = "计算规则不能为空")
    @FluentValid
    @Valid
    private List<ChargeCalculationRuleDTO> chargeCalculationRuleDTOList;
}
