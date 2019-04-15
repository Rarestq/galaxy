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
 * <p>LuggageLostRegistrCompensationRelationDomian实体对象</p>
 *
 * 行李遗失登记记录-赔偿关联表
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-15
 */

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_luggage_lost_registr_compensation_relation")
public class LuggageLostRegistrCompensationRelation extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 行李遗失登记记录-赔偿关联主键id
     */
    @TableId(value = "lost_registr_compensation_relation_id", type = IdType.AUTO)
    private Long lostRegistrCompensationRelationId;
    /**
     * 赔偿记录主键id
     */
    @TableField("luggage_lost_compensation_record_id")
    private Long luggageLostCompensationRecordId;
    /**
     * 行李遗失登记主键id
     */
    @TableField("lost_registration_record_id")
    private Long lostRegistrationRecordId;
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
