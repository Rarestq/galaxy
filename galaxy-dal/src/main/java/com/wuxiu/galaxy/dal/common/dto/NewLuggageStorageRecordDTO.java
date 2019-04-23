package com.wuxiu.galaxy.dal.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 新增行李寄存记录对象
 *
 * @author: wuxiu
 * @date: 2019/4/22 16:32
 */
@ApiModel("新增行李寄存记录对象")
@Data
public class NewLuggageStorageRecordDTO implements Serializable {

    private static final long serialVersionUID = 5771410961612080528L;

    /**
     * 行李类型主键id
     */
    @ApiModelProperty(value = "行李类型主键id", required = true)
    private Long luggageTypeId;
    /**
     * 行李寄存记录编号
     */
    @ApiModelProperty(value = "行李寄存记录编号", required = true)
    private String luggageRecordNo;
    /**
     * 管理员id
     */
    @ApiModelProperty(value = "管理员id", required = true)
    private Long adminId;
    /**
     * 管理员姓名
     */
    @ApiModelProperty(value = "管理员姓名", required = true)
    private String adminName;
    /**
     * 管理员电话
     */
    @ApiModelProperty(value = "管理员电话", required = true)
    private String adminPhone;
    /**
     * 寄存人姓名
     */
    @ApiModelProperty(value = "寄存人姓名", required = true)
    private String depositorName;
    /**
     * 寄存人电话
     */
    @ApiModelProperty(value = "寄存人电话", required = true)
    private String depositorPhone;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false)
    private String remark;
    /**
     * 寄存开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "寄存开始时间", required = true)
    private LocalDateTime storageStartTime;
    /**
     * 寄存结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "寄存结束时间", required = true)
    private LocalDateTime storageEndTime;
}