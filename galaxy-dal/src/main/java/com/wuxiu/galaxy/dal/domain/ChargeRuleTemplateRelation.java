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
 * <p>ChargeRuleTemplateRelationDomian实体对象</p>
 *
 * 计费规则-计费模板关联表
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-19
 */

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_charge_rule_template_relation")
public class ChargeRuleTemplateRelation extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 计费规则-计费模板关联主键id
     */
    @TableId(value = "charge_rule_template_relation_id", type = IdType.AUTO)
    private Long chargeRuleTemplateRelationId;
    /**
     * 计费规则主键id
     */
    @TableField("charge_rule_id")
    private Long chargeRuleId;
    /**
     * 计费模板主键id
     */
    @TableField("charge_template_id")
    private Long chargeTemplateId;
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
