package com.wuxiu.galaxy.web.biz.form;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行李寄存记录查询表单
 *
 * @author: wuxiu
 * @date: 2019/4/15 11:40
 */
@ApiModel("行李寄存记录表单")
@Data
public class LuggageStorageRecordQueryForm extends PageInfo {

    private static final long serialVersionUID = -3681674043002095978L;

    /**
     * 行李寄存主键id
     */
    @ApiModelProperty(value = "行李寄存主键id", required = false)
    private Long luggageId;

    /**
     * 查询条件(行李寄存记录编号、寄存人姓名、寄存人电话)
     */
    @ApiModelProperty(value = "查询条件(行李寄存记录编号、寄存人姓名、寄存人电话)", required = false)
    private String queryCondition;

}
