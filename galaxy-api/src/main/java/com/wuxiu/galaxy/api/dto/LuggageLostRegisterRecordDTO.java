package com.wuxiu.galaxy.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 行李遗失登记记录对象
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:33
 */
@ApiModel("行李遗失登记记录对象")
@Data
public class LuggageLostRegisterRecordDTO implements Serializable {

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
     * 行李类型主键id
     */
    @ApiModelProperty(value = "行李类型主键id", required = true)
    private Long luggageTypeId;
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
     * 遗失记录状态(0-遗失，1-已赔偿)
     */
    @ApiModelProperty(value = "遗失记录状态(0-遗失，1-已赔偿)", required = true)
    private String status;
    /**
     * 行李遗失登记备注
     */
    @ApiModelProperty(value = "行李遗失登记备注", required = true)
    private String remark;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    private String gmtCreate;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", required = true)
    private String gmtModified;

}
