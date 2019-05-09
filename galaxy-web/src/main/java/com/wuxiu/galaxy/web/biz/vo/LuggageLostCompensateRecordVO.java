package com.wuxiu.galaxy.web.biz.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 遗失赔偿记录页面展示对象
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:12
 */
@ApiModel("遗失赔偿记录页面展示对象")
@Data
public class LuggageLostCompensateRecordVO implements Serializable {

    private static final long serialVersionUID = 3290646436654854972L;

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
    private String luggageType;
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
