package com.wuxiu.galaxy.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建行李逾期记录对象
 *
 * @author: wuxiu
 * @date: 2019/4/23 20:24
 */
@Data
public class SaveLuggageOverdueRecordDTO implements Serializable {

    private static final long serialVersionUID = -7459024447476207927L;

    /**
     * 行李寄存记录主键id
     */
    private Long luggageId;

    /**
     * 行李寄存记录状态(1-已逾期,2-已清理作废)
     */
    private Integer status;

    /**
     * 创建行李逾期未取清理记录的备注
     */
    private String remark;
}
