package com.wuxiu.galaxy.web.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 行李取件记录展示对象
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:15
 */
@ApiModel("行李取件记录展示对象")
@Data
public class PickupLuggageRecordVO implements Serializable {

    private static final long serialVersionUID = -1547101160768561098L;

    /**
     * 行李取件记录主键id
     */
    @ApiModelProperty(value = "行李取件记录主键id", required = true)
    private Long pickupLuggageRecordId;
    /**
     * 取件记录编号
     */
    @ApiModelProperty(value = "取件记录编号", required = true)
    private String pickupRecordNo;
    /**
     * 行李寄存主键id
     */
    @ApiModelProperty(value = "行李寄存主键id", required = true)
    private Long luggageId;
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
     * 取件人姓名
     */
    @ApiModelProperty(value = "取件人姓名", required = true)
    private String pickerName;
    /**
     * 取件人电话
     */
    @ApiModelProperty(value = "取件人电话", required = true)
    private String pickerPhone;
    /**
     * 取件类型(0-正常取件，1-行李有遗失，2-逾期取件)
     */
    @ApiModelProperty(value = "取件类型(0-正常取件，1-行李有遗失，2-逾期取件)", required = true)
    private String pickupType;
    /**
     * 取件时间
     */
    @ApiModelProperty(value = "取件时间", required = true)
    private String pickUpTime;

}
