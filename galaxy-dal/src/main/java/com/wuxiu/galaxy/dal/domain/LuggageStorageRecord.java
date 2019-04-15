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
 * <p>LuggageStorageRecordDomian实体对象</p>
 *
 * 行李寄存-取件表
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-15
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_luggage_storage_record")
public class LuggageStorageRecord extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 行李寄存主键id
     */
    @TableId(value = "luggage_id", type = IdType.AUTO)
    private Long luggageId;
    /**
     * 行李类型主键id
     */
    @TableField("luggage_type_id")
    private Integer luggageTypeId;
    /**
     * 管理员id
     */
    @TableField("admin_id")
    private Long adminId;
    /**
     * 管理员姓名(冗余)
     */
    @TableField("admin_name")
    private String adminName;
    /**
     * 管理员电话(冗余)
     */
    @TableField("admin_phone")
    private String adminPhone;
    /**
     * 寄存人姓名
     */
    @TableField("depositor_name")
    private String depositorName;
    /**
     * 寄存人电话
     */
    @TableField("depositor_phone")
    private String depositorPhone;
    /**
     * 寄存人性别(0-男，1-女)
     */
    @TableField("depositor_gender")
    private Integer depositorGender;
    /**
     * 备注
     */
    private String remark;
    /**
     * 开始寄存时间
     */
    @TableField("storage_start_time")
    private LocalDateTime storageStartTime;
    /**
     * 寄存结束时间
     */
    @TableField("storage_end_time")
    private LocalDateTime storageEndTime;
    /**
     * 行李取件时间
     */
    @TableField("luggage_pick_up_time")
    private LocalDateTime luggagePickUpTime;
    /**
     * 行李寄存状态(0-寄存中，1-已取件，2-已逾期，3-已作废）
，3-已逾期，4-已作废)
     */
    private Integer status;
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
