/**
 *  
 *  * All rights Reserved, Designed By wuxiu
 * <p>
 *  * @Package com.wuxiu.galaxy.dal.dao
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @date: 2018-04-16 20:35:12
 *  * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 *  
 */
package com.wuxiu.galaxy.web.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 行李寄存记录页面展示对象
 *
 * @author wuxiu
 */
@ApiModel("行李寄存记录页面展示对象")
@Data
public class LuggageStorageRecordVO implements Serializable {

    private static final long serialVersionUID = -2149920244516242581L;

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
    private String luggageTypeDesc;
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
     * 管理员类型
     */
    @ApiModelProperty(value = "管理员类型", required = true)
    private String adminType;
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
     * 寄存所需费用
     */
    @ApiModelProperty(value = "寄存所需费用", required = true)
    private String storageFee;
    /**
     * 行李寄存柜主键id
     */
    @ApiModelProperty(value = "行李寄存柜主键id", required = true)
    private Long cabinetId;
    /**
     * 行李柜编号
     */
    @ApiModelProperty(value = "行李柜编号", required = true)
    private String luggageCabinetNo;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;
    /**
     * 开始寄存时间
     */
    @ApiModelProperty(value = "开始寄存时间", required = true)
    private String storageStartTime;
    /**
     * 寄存结束时间
     */
    @ApiModelProperty(value = "寄存结束时间", required = true)
    private String storageEndTime;
    /**
     * 行李寄存状态(0-寄存中，1-已取件，2-已逾期)
     */
    @ApiModelProperty(value = "行李寄存状态(0-寄存中，1-已取件，2-已逾期)", required = true)
    private String status;
}
