package com.wuxiu.galaxy.service.core.bus.event;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 创建遗失登记记录事件
 *
 * @author: wuxiu
 * @date: 2019/4/25 22:14
 */
@Builder
@ToString
@Getter
public class CreateLostRegistrRecordEvent {

    /**
     * 管理员信息
     */
    private Long adminId;

    private String adminName;

    /**
     * 行李寄存记录信息
     */
    private Long luggageId;

    private String luggageRecordNo;

    /**
     * 行李类型id
     */
    private Long luggageTypeId;

    /**
     * 寄存人信息
     */
    private String depositorName;

    private String depositorPhone;
}
