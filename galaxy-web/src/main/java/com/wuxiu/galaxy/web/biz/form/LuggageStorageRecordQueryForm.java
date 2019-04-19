package com.wuxiu.galaxy.web.biz.form;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 行李寄存记录查询表单
 *
 * @author: wuxiu
 * @date: 2019/4/15 11:40
 */
@ApiModel("行李寄存记录表单")
@Data
public class LuggageStorageRecordQueryForm extends PageInfo {

    private static final long serialVersionUID = -3681674043002095978L;

    /**
     * 行李寄存主键id
     */
    @ApiModelProperty(value = "行李寄存主键id", required = false)
    private Long luggageId;
    /**
     * 行李寄存记录编号
     */
    @ApiModelProperty(value = "行李寄存记录编号", required = true)
    private String luggageRecordNo;
    /**
     * 行李类型主键id
     */
    @ApiModelProperty(value = "行李类型主键id", required = true)
    private Long luggageTypeId;
    /**
     * 管理员id
     */
    @ApiModelProperty(value = "管理员id", required = true)
    private Long adminId;
    /**
     * 管理员姓名(冗余)
     */
    @ApiModelProperty(value = "管理员姓名", required = true)
    private String adminName;
    /**
     * 管理员电话(冗余)
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
     * 寄存人性别(0-男，1-女)
     */
    @ApiModelProperty(value = "寄存人性别", required = true)
    private Integer depositorGender;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;
    /**
     * 寄存结束时间
     */
    @ApiModelProperty(value = "寄存结束时间", required = true)
    private LocalDateTime storageEndTime;

}
