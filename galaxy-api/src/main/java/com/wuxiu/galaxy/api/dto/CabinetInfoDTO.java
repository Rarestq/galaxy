package com.wuxiu.galaxy.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 行李寄存信息对象
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:35
 */
@ApiModel(description = "行李寄存信息对象")
@Data
public class CabinetInfoDTO implements Serializable {

    private static final long serialVersionUID = -6317230351314237787L;

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
    private String status;
}
