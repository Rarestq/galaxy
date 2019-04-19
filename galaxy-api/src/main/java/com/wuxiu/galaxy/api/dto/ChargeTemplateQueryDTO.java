package com.wuxiu.galaxy.api.dto;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 计费模板查询对象
 *
 * @author: wuxiu
 * @date: 2019/4/19 09:07
 */
@ApiModel("计费模板查询对象")
@Data
public class ChargeTemplateQueryDTO extends PageInfo {

    private static final long serialVersionUID = 6652315649883352277L;

    @ApiModelProperty(value = "计费模板主键id", required = false)
    private Long chargeTemplateId;

    @ApiModelProperty(value = "计费模板名称", required = false)
    private String chargeTemplateName;
}
