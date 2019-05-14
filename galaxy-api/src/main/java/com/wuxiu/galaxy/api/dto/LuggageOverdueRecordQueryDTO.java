package com.wuxiu.galaxy.api.dto;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行李寄存逾期未取清理记录查询对象
 *
 * @author: wuxiu
 * @date: 2019/4/23 21:33
 */
@ApiModel("行李寄存逾期未取清理记录查询对象")
@Data
public class LuggageOverdueRecordQueryDTO extends PageInfo {

    private static final long serialVersionUID = 1192913045896876865L;
    /**
     * 查询条件(行李寄存记录编号、寄存人姓名、寄存人电话)
     */
    @ApiModelProperty(value = "查询条件(行李寄存记录编号、寄存人姓名、寄存人电话)", required = false)
    private String queryCondition;

    /**
     * 逾期清理状态(1-逾期，2-已清理作废)
     */
    @ApiModelProperty(value = "逾期清理状态(1-逾期，2-已清理作废)", required = false)
    private Integer status;
}
