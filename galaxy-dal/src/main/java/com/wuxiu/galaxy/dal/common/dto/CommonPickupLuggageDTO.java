package com.wuxiu.galaxy.dal.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 正常行李取件的对象
 *
 * @author: wuxiu
 * @date: 2019/4/26 15:42
 */
@Data
public class CommonPickupLuggageDTO implements Serializable {

    private static final long serialVersionUID = -7394359133274896660L;

    /**
     * 取件记录编号
     */
    private String pickupRecordNo;

    /**
     * 行李寄存主键id
     */
    private Long luggageId;

    /**
     * 行李寄存状态(0-寄存中，1-已取件，2-已逾期)
     */
    private Integer status;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 管理员信息
     */
    private Long adminId;
    private String adminName;

    /**
     * 取件人信息
     */
    private String pickerName;
    private String pickerPhone;

    /**
     * 取件类型(0-正常取件，1-行李有遗失，2-逾期取件)
     */
    private Integer pickupType;
    /**
     * 取件时间
     */
    private LocalDateTime pickUpTime;
}
