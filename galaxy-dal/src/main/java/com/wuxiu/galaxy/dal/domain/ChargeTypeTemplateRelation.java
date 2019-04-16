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
 *  * <p>ChargeTypeTemplateRelationDomian实体对象</p>
 * <p>
 *  计费模板-费用类型关联表
 * <p>
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @since 2019-04-16
 *  
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_charge_type_template_relation")
public class ChargeTypeTemplateRelation extends BaseModel {

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
