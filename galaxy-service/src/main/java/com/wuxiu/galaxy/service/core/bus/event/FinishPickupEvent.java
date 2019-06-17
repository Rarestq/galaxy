package com.wuxiu.galaxy.service.core.bus.event;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 取件完成事件
 *
 * @author: wuxiu
 * @date: 2019/5/28 15:07
 */
@Builder
@ToString
@Getter
public class FinishPickupEvent {

    /**
     * 行李寄存编号
     */
    private String luggageRecordNo;

    /**
     * 寄存者姓名
     */
    private String depositorName;

    /**
     * 寄存者电话
     */
    private String depositorPhone;

}
