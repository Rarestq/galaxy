package com.wuxiu.galaxy.dal.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 新增行李寄存记录对象
 *
 * @author: wuxiu
 * @date: 2019/4/22 16:32
 */
@Data
public class NewLuggageStorageRecordDTO implements Serializable {

    private static final long serialVersionUID = 5771410961612080528L;

    /**
     * 行李类型主键id
     */
    private Long luggageTypeId;
    /**
     * 行李寄存记录编号
     */
    private String luggageRecordNo;
    /**
     * 管理员id
     */
    private Long adminId;
    /**
     * 管理员姓名
     */
    private String adminName;
    /**
     * 管理员电话
     */
    private String adminPhone;
    /**
     * 寄存人姓名
     */
    private String depositorName;
    /**
     * 寄存人电话
     */
    private String depositorPhone;
    /**
     * 寄存所需费用
     */
    private BigDecimal feeValue;
    /**
     * 备注
     */
    private String remark;
    /**
     * 寄存开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime storageStartTime;
    /**
     * 寄存结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime storageEndTime;
}
