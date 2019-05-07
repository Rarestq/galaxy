package com.wuxiu.galaxy.api.dto;

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
@ApiModel("行李寄存记录查询表单")
@Data
public class LuggageStorageRecordQueryDTO extends PageInfo {

    private static final long serialVersionUID = -3681674043002095978L;

    /**
     * 行李寄存主键id
     */
    @ApiModelProperty(value = "行李寄存主键id", required = false)
    private Long luggageId;
    /**
     * 行李寄存记录编号
     */
    @ApiModelProperty(value = "行李寄存记录编号", required = false)
    private String luggageRecordNo;
    /**
     * 寄存人姓名
     */
    @ApiModelProperty(value = "寄存人姓名", required = false)
    private String depositorName;
    /**
     * 寄存人电话
     */
    @ApiModelProperty(value = "寄存人电话", required = false)
    private String depositorPhone;
    /**
     * 寄存开始时间
     */
//    @ApiModelProperty(value = "寄存结束时间范围", required = false)
//    private String storageTimeRange;
    /**
     * 寄存结束时间
     */
//    @ApiModelProperty(value = "寄存结束时间", required = false)
//    private String storageEndTime;
}
