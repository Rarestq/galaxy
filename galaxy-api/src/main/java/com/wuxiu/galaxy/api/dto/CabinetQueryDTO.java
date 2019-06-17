package com.wuxiu.galaxy.api.dto;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行李寄存柜查询参数
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:45
 */
@ApiModel(description = "行李寄存柜查询参数")
@Data
public class CabinetQueryDTO extends PageInfo {

    private static final long serialVersionUID = -1130592876609114868L;

    /**
     * 行李柜主键id
     */
    @ApiModelProperty(value = "行李柜主键id", required = true)
    private Long luggageCabinetId;
    /**
     * 行李柜编号
     */
    @ApiModelProperty(value = "行李柜编号", required = true)
    private String luggageCabinetNo;
    /**
     * 行李柜状态(0-空闲、1-已占用、2-逾期占用、3-维修中)
     */
    @ApiModelProperty(value = "行李柜状态(0-空闲、1-已占用、2-逾期占用、3-维修中)",
            required = true)
    private Integer status;
}
