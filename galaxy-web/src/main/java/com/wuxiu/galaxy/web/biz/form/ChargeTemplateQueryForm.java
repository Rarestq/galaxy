package com.wuxiu.galaxy.web.biz.form;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 计费模板查询表单
 *
 * @author: wuxiu
 * @date: 2019/4/19 08:54
 */
@ApiModel("计费模板查询表单")
@Data
public class ChargeTemplateQueryForm extends PageInfo {

    private static final long serialVersionUID = -3687841165272309877L;

    @ApiModelProperty(value = "计费模板主键id", required = false)
    private Long chargeTemplateId;

    @ApiModelProperty(value = "计费模板名称", required = false)
    private String chargeTemplateName;
}
