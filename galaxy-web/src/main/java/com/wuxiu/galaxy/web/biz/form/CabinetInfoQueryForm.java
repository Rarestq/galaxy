package com.wuxiu.galaxy.web.biz.form;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行李寄存柜查询参数表单
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:42
 */
@ApiModel("行李寄存柜查询参数表单")
@Data
public class CabinetInfoQueryForm extends PageInfo {

    private static final long serialVersionUID = -4215697113201756933L;

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
