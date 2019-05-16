package com.wuxiu.galaxy.web.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 行李寄存柜信息展示对象
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:40
 */
@ApiModel("行李寄存柜信息展示对象")
@Data
public class CabinetInfoVO implements Serializable {

    private static final long serialVersionUID = 758592318801408001L;

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
