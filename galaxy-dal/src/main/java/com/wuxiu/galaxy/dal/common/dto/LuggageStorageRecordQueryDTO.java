package com.wuxiu.galaxy.dal.common.dto;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import lombok.Data;

import java.io.Serializable;

/**
 * 行李寄存记录查询表单
 *
 * @author: wuxiu
 * @date: 2019/4/15 11:40
 */
@Data
public class LuggageStorageRecordQueryDTO implements Serializable {

    private static final long serialVersionUID = -3681674043002095978L;

    /**
     * 分页条件
     */
    Page<LuggageStorageRecord> page;
    /**
     * 行李寄存主键id
     */
    private Long luggageId;
    /**
     * 行李寄存记录编号
     */
    private String luggageRecordNo;
    /**
     * 寄存人姓名
     */
    private String depositorName;
    /**
     * 寄存人电话
     */
    private String depositorPhone;
    /**
     * 寄存结束时间(from)
     */
//    private LocalDateTime storageEndTimeFrom;
    /**
     * 寄存结束时间(to)
     */
//    private LocalDateTime storageEndTimeTo;
}
