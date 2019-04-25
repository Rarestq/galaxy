package com.wuxiu.galaxy.service.core.bus.event;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 自动创建逾期记录事件
 *
 * @author: wuxiu
 * @date: 2019/4/23 17:07
 */
@Builder
@ToString
@Getter
public class CreateOverdueRecordEvent {

    /**
     * 行李寄存记录主键id
     */
    private Long luggageId;

    /**
     * 行李寄存记录状态
     */
    private String status;

    /**
     * 创建行李逾期未取清理记录的备注
     */
    private String remark;
}
