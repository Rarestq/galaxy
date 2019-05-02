package com.wuxiu.galaxy.service.core.biz.service.smsservice;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 短信内容
 *
 * @author: wuxiu
 * @date: 2019/5/2 22:05
 */
@Data
public class SmsBody implements Serializable {

    private static final long serialVersionUID = -4747920433288571795L;

    /**
     * 行李寄存编号
     */
    private String storageRecordNo;

    /**
     * 用户姓名
     */
    private String depositorName;

    /**
     * 用户电话
     */
    private String depositorPhone;

    /**
     * 管理员电话
     */
    private String adminPhone;

    /**
     * 寄存结束时间
     */
    private LocalDateTime storageEndTime;

    /**
     * 寄存所需费用/逾期补收费用
     */
    private String fee;
}
