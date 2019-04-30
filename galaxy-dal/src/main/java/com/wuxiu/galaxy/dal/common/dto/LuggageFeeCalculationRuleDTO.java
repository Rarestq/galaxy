package com.wuxiu.galaxy.dal.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 计费规则对象
 *
 * @author: wuxiu
 * @date: 2019/4/30 11:20
 */
@Data
public class LuggageFeeCalculationRuleDTO implements Serializable {

    private static final long serialVersionUID = 1549800927130038841L;

    /**
     * 行李类型id
     */
    private Long luggageTypeId;

    /**
     * 行李寄存时长（单位：天）
     */
    private Integer luggageStorageDays;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
}
