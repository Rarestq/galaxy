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
 *  * <p>PickupLuggageRecordDomian实体对象</p>
 * <p>
 *  行李取件记录表
 * <p>
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @since 2019-04-22
 *  
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_pickup_luggage_record")
public class PickupLuggageRecord extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 行李取件记录主键id
     */
    @TableId(value = "pickup_luggage_record_id", type = IdType.AUTO)
    private Long pickupLuggageRecordId;
    /**
     * 行李寄存主键id
     */
    @TableField("luggage_id")
    private Long luggageId;
    /**
     * 管理员id(冗余)
     */
    @TableField("admin_id")
    private Long adminId;
    /**
     * 管理员姓名(冗余)
     */
    @TableField("admin_name")
    private String adminName;
    /**
     * 取件人姓名(冗余)
     */
    @TableField("picker_name")
    private String pickerName;
    /**
     * 取件人电话(冗余)
     */
    @TableField("picker_phone")
    private String pickerPhone;
    /**
     * 取件类型(0-正常取件，1-行李有遗失，2-逾期取件)
     */
    @TableField("pickup_type")
    private Integer pickupType;
    /**
     * 取件时间
     */
    @TableField("pick_up_time")
    private LocalDateTime pickUpTime;
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
