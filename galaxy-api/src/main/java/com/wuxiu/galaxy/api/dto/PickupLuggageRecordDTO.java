package com.wuxiu.galaxy.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 行李取件记录对象
 *
 * @author: wuxiu
 * @date: 2019/4/26 21:53
 */
@ApiModel("行李取件记录对象")
@Data
public class PickupLuggageRecordDTO implements Serializable {

    private static final long serialVersionUID = -3369416663407166707L;

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
    @ApiModelProperty(value = "取件时间", required = true)
    private Long luggageId;
    /**
     * 管理员id
     */
    @ApiModelProperty(value = "取件时间", required = true)
    private Long adminId;
    /**
     * 管理员姓名
     */
    @ApiModelProperty(value = "取件时间", required = true)
    private String adminName;
    /**
     * 取件人姓名
     */
    @ApiModelProperty(value = "取件时间", required = true)
    private String pickerName;
    /**
     * 取件人电话
     */
    @ApiModelProperty(value = "取件时间", required = true)
    private String pickerPhone;
    /**
     * 取件类型(0-正常取件，1-行李有遗失，2-逾期取件)
     */
    @ApiModelProperty(value = "取件类型(0-正常取件，1-行李有遗失，2-逾期取件)", required = true)
    private String pickupType;
    /**
     * 取件时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "取件时间", required = true)
    private LocalDateTime pickUpTime;
}
