package com.wuxiu.galaxy.web.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 行李遗失登记记录展示对象
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:33
 */
@ApiModel("行李遗失登记记录展示对象")
@Data
public class LuggageLostRegisterRecordVO implements Serializable {

    private static final long serialVersionUID = 9002047504708575458L;

    /**
     * 行李遗失登记主键id
     */
    @ApiModelProperty(value = "行李遗失登记主键id", required = true)
    private Long lostRegistrationRecordId;
    /**
     * 行李遗失登记记录编号
     */
    @ApiModelProperty(value = "行李遗失登记记录编号", required = true)
    private String registerRecordNo;
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
     * 行李寄存主键id
     */
    @ApiModelProperty(value = "行李寄存主键id", required = true)
    private Long luggageId;
    /**
     * 行李寄存记录编号
     */
    @ApiModelProperty(value = "行李寄存记录编号", required = true)
    private String luggageRecordNo;
    /**
     * 行李类型
     */
    @ApiModelProperty(value = "行李类型", required = true)
    private String luggageTypeDesc;
    /**
     * 行李遗失登记者姓名
     */
    @ApiModelProperty(value = "行李遗失登记者姓名", required = true)
    private String depositorName;
    /**
     * 行李遗失登记者联系方式
     */
    @ApiModelProperty(value = "行李遗失登记者联系方式", required = true)
    private String depositorPhone;
    /**
     * 行李遗失登记备注
     */
    @ApiModelProperty(value = "行李遗失登记备注", required = true)
    private String remark;

}
