package com.wuxiu.galaxy.web.biz.form;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 新增/编辑计费规则表单
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:39
 */
@ApiModel("新增/编辑计费规则表单")
@Data
public class SaveChargeRuleForm implements Serializable {

    private static final long serialVersionUID = 1927608931329425791L;

    private Long chargeRuleId;

    @NotNull(message = "chargeRuleName不能为空")
    private String chargeRuleName;

    private Integer chargeRuleType;

    @NotNull(message = "chargeTemplateFormList不能为空")
    @FluentValid
    @Valid
    private List<ChargeTemplateForm> chargeTemplateFormList;
}
