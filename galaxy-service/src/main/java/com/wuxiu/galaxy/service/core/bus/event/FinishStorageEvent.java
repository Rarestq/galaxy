package com.wuxiu.galaxy.service.core.bus.event;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 完成寄存后触发的事件
 *
 * @author: wuxiu
 * @date: 2019/4/25 22:33
 */
@Builder
@ToString
@Getter
public class FinishStorageEvent {

    /**
     * 行李寄存编号
     */
    private String luggageRecordNo;

    /**
     * 寄存所需费用
     */
    private String storageFee;

    /**
     * 寄存开始时间
     */
    private LocalDateTime storageStartTime;

    /**
     * 寄存结束时间
     */
    private LocalDateTime storageEndTime;

    /**
     * 管理员姓名
     */
    private String adminName;

    /**
     * 管理员电话
     */
    private String adminPhone;
}
