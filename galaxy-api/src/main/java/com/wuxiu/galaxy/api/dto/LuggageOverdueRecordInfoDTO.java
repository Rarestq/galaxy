package com.wuxiu.galaxy.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 行李逾期记录对象
 *
 * @author: wuxiu
 * @date: 2019/4/23 20:33
 */
@ApiModel("行李逾期记录对象")
@Data
public class LuggageOverdueRecordInfoDTO implements Serializable {

    private static final long serialVersionUID = -563776810242112872L;

    /**
     * 行李逾期未取记录主键id
     */
    @ApiModelProperty(value = "行李逾期未取记录主键id", required = true)
    private Long luggageOverdueRecordId;
    /**
     * 逾期记录编号
     */
    @ApiModelProperty(value = "逾期记录编号", required = true)
    private String overdueRecordNo;
    /**
     * 行李寄存主键id
     */
    @ApiModelProperty(value = "行李寄存主键id", required = true)
    private Long luggageId;
    /**
     * 行李寄存记录编号
     */
    @ApiModelProperty(value = "行李寄存记录编号", required = true)
    private String luggageRecordNo;
    /**
     * 行李类型
     */
    @ApiModelProperty(value = "行李类型", required = true)
    private Long luggageTypeId;
    /**
     * 行李寄存者姓名
     */
    @ApiModelProperty(value = "行李寄存者姓名", required = true)
    private String depositorName;
    /**
     * 行李寄存者联系方式
     */
    @ApiModelProperty(value = "行李寄存者联系方式", required = true)
    private String depositorPhone;
    /**
     * 行李寄存记录状态(1-已逾期,2-已清理作废)
     */
    @ApiModelProperty(value = "行李寄存记录状态(1-已逾期,2-已清理作废)", required = true)
    private Integer status;
    /**
     * 创建行李逾期未取清理记录的备注
     */
    @ApiModelProperty(value = "创建行李逾期未取清理记录的备注", required = true)
    private String remark;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    private String gmtCreate;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", required = true)
    private String gmtModified;
}
