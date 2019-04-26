package com.wuxiu.galaxy.dal.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 标记为遗失的对象
 *
 * @author: wuxiu
 * @date: 2019/4/26 15:43
 */
@Data
public class MarkLuggageAsLostDTO implements Serializable {

    private static final long serialVersionUID = 7413784809463824884L;

    /**
     * 行李寄存主键id
     */
    private Long luggageId;

    /**
     * 行李寄存记录编号
     */
    private String luggageRecordNo;

    /**
     * 行李类型主键id(冗余)
     */
    private Long luggageTypeId;

    /**
     * 行李类型
     */
    private String luggageTypeDesc;

    /**
     * 管理员信息
     */
    private Long adminId;
    private String adminName;

    /**
     * 取件人信息
     */
    private String depositorName;
    private String depositorPhone;
}
