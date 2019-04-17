package com.wuxiu.galaxy.web.biz.form;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 计费规则查询表单
 *
 * @author: wuxiu
 * @date: 2019/4/17 15:04
 */
@ApiModel("计费规则查询表单")
@Data
public class ChargeRuleQueryForm extends PageInfo {

    private static final long serialVersionUID = 6736554433098662964L;

    @ApiModelProperty(value = "计费规则主键id", required = false)
    private Long chargeRuleId;

    @ApiModelProperty(value = "计费规则名称", required = false)
    private String chargeRuleName;

    @ApiModelProperty(value = "计费规则类型", required = false)
    private Integer chargeRuleType;

}
