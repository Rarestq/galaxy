/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.controller.dal.dao
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

import java.time.LocalDateTime;

/**  
 * <p>LuggageLostCompensationRecordDomian实体对象</p>
 *
 * 行李遗失赔偿记录表
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-15
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_luggage_lost_compensation_record")
public class LuggageLostCompensationRecord extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 赔偿记录主键id
     */
    @TableId(value = "luggage_lost_compensation_record_id", type = IdType.AUTO)
    private Long luggageLostCompensationRecordId;
    /**
     * 赔偿方式（1-现金，2-微信，3-支付宝
     */
    @TableField("compensation_method")
    private Integer compensationMethod;
    /**
     * 赔偿金额
     */
    @TableField("compensation_amount")
    private String compensationAmount;
    /**
     * 备注
     */
    private String remark;
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
