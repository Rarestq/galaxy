package com.wuxiu.galaxy.dal.common.dto;

import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuxiu.galaxy.dal.domain.PickupLuggageRecord;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 行李取件记录查询参数对象
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:16
 */
@Data
public class PickupLuggageRecordQueryDTO implements Serializable {

    private static final long serialVersionUID = 6883399169024073300L;

    /**
     * 分页参数
     */
    Page<PickupLuggageRecord> page;

    /**
     * 行李寄存记录主键id
     */
    private Long luggageId;

    /**
     * 行李寄存者姓名
     */
    private String depositorName;

    /**
     * 取件时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime pickupTime;
}
