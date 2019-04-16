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
}
