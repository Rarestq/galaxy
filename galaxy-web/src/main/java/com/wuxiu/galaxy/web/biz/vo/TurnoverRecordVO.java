package com.wuxiu.galaxy.web.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 营业额记录页面展示对象
 *
 * @author: wuxiu
 * @date: 2019/4/23 10:13
 */
@ApiModel("营业额记录页面展示对象")
@Data
public class TurnoverRecordVO implements Serializable {

    private static final long serialVersionUID = 9057976395849788200L;

    /**
     * 营业额记录表主键id
     */
    @ApiModelProperty(value = "营业额记录表主键id", required = true)
    private Long turnoverRecordId;
    /**
     * 行李寄存主键id
     */
    @ApiModelProperty(value = "行李寄存主键id", required = true)
    private Long luggageId;
    /**
     * 行李寄存记录编号
     */
    @ApiModelProperty(value = "行李寄存记录编号", required = true)
    private String luggageStorageRecordNo;
    /**
     * 行李类型
     */
    @ApiModelProperty(value = "行李类型", required = true)
    private String luggageType;

    /**
     * 管理员主键id
     */
    @ApiModelProperty(value = "管理员主键id", required = true)
    private Long adminId;
    /**
     * 管理员姓名
     */
    @ApiModelProperty(value = "管理员姓名", required = true)
    private String adminName;
    /**
     * 计费规则主键id
     */
    @ApiModelProperty(value = "计费规则主键id", required = true)
    private Long calculationRuleId;
    /**
     * 费用
     */
    @ApiModelProperty(value = "费用", required = true)
    private String fee;
    /**
     * 费用类型
     */
    @ApiModelProperty(value = "费用类型", required = true)
    private String feeType;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = false)
    private String gmtCreate;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", required = false)
    private String gmtModified;
}
