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
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.wuxiu.galaxy.api.common.base.BaseModel;
import com.baomidou.mybatisplus.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**  
 * <p>ChargeCalculationRuleDomian实体对象</p>
 *
 * 计费规则表
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-05-02
 */

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_charge_calculation_rule")
public class ChargeCalculationRule extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 计费规则主键id
     */
    @TableId(value = "calculation_rule_id", type = IdType.AUTO)
    private Long calculationRuleId;
    /**
     * 行李类型主键id
     */
    @TableField("luggage_type_id")
    private Long luggageTypeId;
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
     * 记录状态(1-删除、0-正常)
     */
    @TableLogic
    private Integer deleted;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private Date gmtModified;

}
