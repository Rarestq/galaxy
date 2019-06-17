package com.wuxiu.galaxy.dal.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 逾期取件的对象
 *
 * @author: wuxiu
 * @date: 2019/4/26 15:43
 */
@Data
public class PickupOverdueLuggageDTO implements Serializable {

    private static final long serialVersionUID = 6372556757159927979L;

    /**
     * 取件记录编号
     */
    private String pickupRecordNo;

    private Long adminId;

    private String adminName;

    /**
     * 行李寄存主键id
     */
    private Long luggageId;
    /**
     * 行李寄存记录编号
     */
    private String luggageRecordNo;
    /**
     * 行李寄存者姓名
     */
    private String depositorName;
    /**
     * 行李寄存者联系方式
     */
    private String depositorPhone;

    /**
     * 计费规则id
     */
    private Long calculateRuleId;

    /**
     * 计费单位Id
     */
    private Integer calculationUnitsId;

    /**
     * 费用值
     */
    private BigDecimal feeValue;

    /**
     * 费用计算过程描述
     */
    private String feeCalculationProcessDesc;
}
