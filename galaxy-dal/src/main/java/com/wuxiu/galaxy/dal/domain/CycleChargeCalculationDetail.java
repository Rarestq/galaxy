/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.wuxiu.galaxy.api.common.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 *   
 *  * <p>CycleChargeCalculationDetailDomian实体对象</p>
 * <p>
 *  周期计费表
 * <p>
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @since 2019-04-16
 *  
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_cycle_charge_calculation_detail")
public class CycleChargeCalculationDetail extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 周期计费主键id
     */
    @TableId(value = "cycle_rule_id", type = IdType.AUTO)
    private Long cycleRuleId;
    /**
     * 计算规则主键id
     */
    @TableField("calculation_rule_id")
    private Long calculationRuleId;
    /**
     * 计费单位(1-元/件/天，2-元/件/次，3-元/件)
     */
    @TableField("calculation_units_id")
    private Integer calculationUnitsId;
    /**
     * 单位金额
     */
    @TableField("fee_per_unit")
    private String feePerUnit;
    /**
     * 计费周期(单位：天)
     */
    @TableField("calculation_period")
    private Integer calculationPeriod;
    /**
     * 每段周期的最低金额,单位元
     */
    @TableField("min_fee")
    private String minFee;
    /**
     * 每段周期的最高金额,单位元
     */
    @TableField("max_fee")
    private String maxFee;
    /**
     * 记录状态(1-删除、0-正常)
     */
    @TableLogic
    private Integer deleted;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;

}
