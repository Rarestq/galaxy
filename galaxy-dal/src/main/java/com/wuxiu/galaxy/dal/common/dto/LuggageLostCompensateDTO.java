package com.wuxiu.galaxy.dal.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 行李遗失赔偿参数对象
 *
 * @author: wuxiu
 * @date: 2019/4/28 14:37
 */
@ApiModel("行李遗失赔偿参数对象")
@Data
public class LuggageLostCompensateDTO implements Serializable {

    private static final long serialVersionUID = 651792778276131395L;

    /**
     * 行李遗失登记主键id
     */
    @ApiModelProperty(value = "行李遗失登记主键id", required = true)
    private Long lostRegistrationRecordId;
    /**
     * 遗失赔偿记录编号
     */
    @ApiModelProperty(value = "遗失赔偿记录编号", required = true)
    private String lostCompensateRecordNo;
    /**
     * 行李寄存主键id
     */
    @ApiModelProperty(value = "行李寄存主键id", required = true)
    private Long luggageId;
    /**
     * 管理员id
     */
    @ApiModelProperty(value = "管理员id", required = true)
    private Long adminId;
    /**
     * 管理员姓名
     */
    @ApiModelProperty(value = "管理员姓名", required = true)
    private String adminName;
    /**
     * 赔偿对象姓名
     */
    @ApiModelProperty(value = "赔偿对象姓名", required = true)
    private String depositorName;
    /**
     * 赔偿对象电话
     */
    @ApiModelProperty(value = "赔偿对象电话", required = true)
    private String depositorPhone;
    /**
     * 行李类型
     */
    @ApiModelProperty(value = "行李类型", required = true)
    private Long luggageType;
    /**
     * 赔偿金额
     */
    @ApiModelProperty(value = "赔偿金额", required = true)
    private String compensationFee;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;
}
