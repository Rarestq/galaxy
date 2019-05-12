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
 *  * <p>CommonCalculateRuleDetailDomian实体对象</p>
 * <p>
 *  普通物价计费规则细节
 * <p>
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @since 2019-05-02
 *  
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_common_calculate_rule_detail")
public class CommonCalculateRuleDetail extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 普通物件计费规则的主键id
     */
    @TableId(value = "common_rule_id", type = IdType.AUTO)
    private Long commonRuleId;
    /**
     * 计费规则主键id
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
