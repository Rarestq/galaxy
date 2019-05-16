package com.wuxiu.galaxy.dal.common.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
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
     * 计费规则id
     */
    private Long calculateRuleId;
    /**
     * 行李类型主键id
     */
    private Long luggageTypeId;
    /**
     * 行李寄存记录编号
     */
    private String luggageRecordNo;
    /**
     * 行李柜编号
     */
    private String luggageCabinetNo;
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
     * 费用计算过程描述
     */
    private String feeCalculationProcessDesc;
    /**
     * 备注
     */
    private String remark;
    /**
     * 寄存开始时间
     */
    @JsonFormat(pattern = CommonConstant.TIME_PATTERN)
    private LocalDateTime storageStartTime;
    /**
     * 寄存结束时间
     */
    @JsonFormat(pattern = CommonConstant.TIME_PATTERN)
    private LocalDateTime storageEndTime;
}
