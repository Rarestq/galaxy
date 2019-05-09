package com.wuxiu.galaxy.web.biz.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
     * 计费规则id
     */
    @ApiModelProperty(value = "计费规则id", required = true)
    @NotNull(message = "计费规则id不能为空")
    private Long calculateRuleId;
    /**
     * 行李类型主键id
     */
    @ApiModelProperty(value = "行李类型主键id", required = true)
    @NotNull(message = "行李类型主键id不能为空")
    private Long luggageTypeId;
    /**
     * 当前登录的管理员姓名
     */
    @ApiModelProperty(value = "当前登录的管理员姓名", required = true)
    @NotNull(message = "当前登录的管理员姓名不能为空")
    private String adminName;
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
    @ApiModelProperty(value = "备注", required = false)
    private String remark;
    /**
     * 寄存开始时间
     */
    @ApiModelProperty(value = "寄存开始时间", required = true)
    @NotNull(message = "寄存开始时间不能为空")
    private String storageStartTime;
    /**
     * 寄存结束时间
     */
    @ApiModelProperty(value = "寄存结束时间", required = true)
    @NotNull(message = "寄存结束时间不能为空")
    private String storageEndTime;

}
