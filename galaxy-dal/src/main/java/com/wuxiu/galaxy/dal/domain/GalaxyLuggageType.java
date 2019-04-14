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
 * <p>GalaxyLuggageTypeDomian实体对象</p>
 *
 * 行李类型表
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
@TableName("galaxy_luggage_type")
public class GalaxyLuggageType extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 行李类型主键id
     */
    @TableId(value = "luggage_type_id", type = IdType.AUTO)
    private Integer luggageTypeId;
    /**
     * 行李类型(0-、1-、2、)
     */
    @TableField("luggage_type")
    private Integer luggageType;
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
