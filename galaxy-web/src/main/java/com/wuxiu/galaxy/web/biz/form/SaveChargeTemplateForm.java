package com.wuxiu.galaxy.web.biz.form;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 计费模板表单
 *
 * @author: wuxiu
 * @date: 2019/4/19 08:51
 */
@ApiModel("计费模板表单")
@Data
public class SaveChargeTemplateForm implements Serializable {

    private static final long serialVersionUID = 5680458469891384666L;

    private Long chargeTemplateId;

    @NotNull(message = "计费模板编号不能为空")
    private String chargeTemplateNo;

    @NotNull(message = "计费模板名称不能为空")
    private String chargeTemplateName;

    @NotNull(message = "费用类型ID不能为空")
    private Long chargeTypeId;

    private String minFee;

    private String maxFee;

    @NotNull(message = "chargeCalculationRuleFormList不能为空")
    @FluentValid
    @Valid
    private List<ChargeCalculationRuleForm> chargeCalculationRuleFormList;
}
