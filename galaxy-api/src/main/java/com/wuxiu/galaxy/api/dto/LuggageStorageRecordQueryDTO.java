package com.wuxiu.galaxy.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 行李寄存记录查询表单
 *
 * @author: wuxiu
 * @date: 2019/4/15 11:40
 */
@ApiModel("行李寄存记录查询表单")
@Data
public class LuggageStorageRecordQueryDTO implements Serializable {

    private static final long serialVersionUID = -3681674043002095978L;

    /**
     * 行李类型主键id
     */
    @ApiModelProperty(value = "管理员id", required = false)
    private Long luggageTypeId;
    /**
     * 寄存人姓名
     */
    @ApiModelProperty(value = "寄存人姓名", required = false)
    private String depositorName;
    /**
     * 寄存开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "寄存开始时间", required = false)
    private LocalDateTime storageStartTime;
    /**
     * 寄存结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "寄存结束时间", required = false)
    private LocalDateTime storageEndTime;
}
