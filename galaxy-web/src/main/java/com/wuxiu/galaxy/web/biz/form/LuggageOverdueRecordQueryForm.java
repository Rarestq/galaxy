package com.wuxiu.galaxy.web.biz.form;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行李逾期未取清理记录查询表单
 *
 * @author: wuxiu
 * @date: 2019/4/23 21:02
 */
@ApiModel("行李逾期未取清理记录查询表单")
@Data
public class LuggageOverdueRecordQueryForm extends PageInfo {

    private static final long serialVersionUID = -4121459890734373015L;

    /**
     * 查询条件(行李寄存记录编号、寄存人姓名)
     */
    @ApiModelProperty(value = "查询条件(行李寄存记录编号、寄存人姓名)", required = false)
    private String queryCondition;

    /**
     * 逾期清理状态(1-逾期，2-已清理作废)
     */
    @ApiModelProperty(value = "逾期清理状态(1-逾期，2-已清理作废)", required = false)
    private Integer status;
}
