package com.wuxiu.galaxy.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 行李遗失赔偿记录对象
 *
 * @author: wuxiu
 * @date: 2019/4/28 09:31
 */
@ApiModel("行李遗失赔偿记录对象")
@Data
public class LostCompensateRecordInfoDTO implements Serializable {

    private static final long serialVersionUID = 5714864466721710026L;

    /**
     * 赔偿记录主键id
     */
    private Long luggageLostCompensationRecordId;
    /**
     * 遗失赔偿记录编号
     */
    private String lostCompensateRecordNo;
    /**
     * 行李遗失登记主键id
     */
    private Long lostRegistrationRecordId;
    /**
     * 遗失登记记录编号
     */
    private String lostRegistRecordNo;
    /**
     * 管理员id
     */
    private Long adminId;
    /**
     * 管理员姓名
     */
    private String adminName;
    /**
     * 赔偿对象姓名
     */
    private String depositorName;
    /**
     * 赔偿对象电话
     */
    private String depositorPhone;
    /**
     * 行李类型
     */
    private Long luggageType;
    /**
     * 赔偿金额
     */
    private String compensationFee;
    /**
     * 备注
     */
    private String remark;
    /**
     * 赔偿时间
     */
    private String compensateTime;
}
