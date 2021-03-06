package com.wuxiu.galaxy.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 营业额信息对象
 *
 * @author: wuxiu
 * @date: 2019/4/23 11:41
 */
@ApiModel("营业额信息对象")
@Data
public class TurnoverRecordDTO implements Serializable {

    private static final long serialVersionUID = -6499392166815736279L;

    /**
     * 营业额记录表主键id
     */
    private Long turnoverRecordId;
    /**
     * 行李寄存主键id
     */
    private Long luggageId;
    /**
     * 行李寄存记录编号
     */
    @ApiModelProperty(value = "行李寄存记录编号", required = true)
    private String luggageStorageRecordNo;
    /**
     * 行李类型
     */
    private String luggageType;

    /**
     * 管理员主键id
     */
    private Long adminId;
    /**
     * 管理员姓名
     */
    private String adminName;
    /**
     * 计费规则主键id
     */
    private Long calculationRuleId;
    /**
     * 费用
     */
    private String fee;
    /**
     * 费用类型(0-寄存费用,1-逾期费用,2-赔偿费用)
     */
    private String feeType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private String gmtCreate;
    /**
     * 修改时间
     */
    private String gmtModified;
}
