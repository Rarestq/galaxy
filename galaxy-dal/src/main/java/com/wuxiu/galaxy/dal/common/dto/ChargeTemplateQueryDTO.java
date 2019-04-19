package com.wuxiu.galaxy.dal.common.dto;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.dal.domain.ChargeTemplate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 计费模板查询对象
 *
 * @author: wuxiu
 * @date: 2019/4/19 09:07
 */
@ApiModel("计费模板查询对象")
@Data
public class ChargeTemplateQueryDTO implements Serializable {

    private static final long serialVersionUID = 6652315649883352277L;

    Page<ChargeTemplate> page;

    @ApiModelProperty(value = "计费模板主键id", required = false)
    private Long chargeTemplateId;

    @ApiModelProperty(value = "计费模板名称", required = false)
    private String chargeTemplateName;
}
