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
import com.wuxiu.galaxy.common.base.BaseModel;
import com.baomidou.mybatisplus.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**  
 * <p>GalaxyChargeTypeTemplateRelationDomian实体对象</p>
 *
 * 计费模板-费用类型关联表
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-14
 */

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_charge_type_template_relation")
public class GalaxyChargeTypeTemplateRelation extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 计费模板-费用类型关联主键id
     */
    @TableId(value = "charge_template_type_relation_id", type = IdType.AUTO)
    private Long chargeTemplateTypeRelationId;
    /**
     * 计费模板主键id
     */
    @TableField("charge_template_id")
    private Long chargeTemplateId;
    /**
     * 费用类型主键id
     */
    @TableField("charge_type_id")
    private Long chargeTypeId;
    /**
     * 记录状态(1-删除、0-正常)
     */
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
