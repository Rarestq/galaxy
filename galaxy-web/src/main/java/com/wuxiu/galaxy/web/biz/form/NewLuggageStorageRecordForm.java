package com.wuxiu.galaxy.web.biz.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 行李寄存记录表单
 *
 * @author: wuxiu
 * @date: 2019/4/15 11:40
 */
@ApiModel("行李寄存记录表单")
@Data
public class NewLuggageStorageRecordForm implements Serializable {

    private static final long serialVersionUID = -3681674043002095978L;

    /**
     * 行李类型主键id
     */
    @ApiModelProperty(value = "行李类型主键id", required = true)
    @NotNull(message = "行李类型主键id不能为空")
    private Long luggageTypeId;
    /**
     * 寄存人姓名
     */
    @ApiModelProperty(value = "寄存人姓名", required = true)
    @NotNull(message = "寄存人姓名不能为空")
    private String depositorName;
    /**
     * 寄存人电话
     */
    @ApiModelProperty(value = "寄存人电话", required = true)
    @NotNull(message = "寄存人电话不能为空")
    private String depositorPhone;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    @NotNull(message = "备注不能为空")
    private String remark;
    /**
     * 寄存结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "寄存结束时间", required = true)
    @NotNull(message = "寄存结束时间不能为空")
    private LocalDateTime storageEndTime;

}
