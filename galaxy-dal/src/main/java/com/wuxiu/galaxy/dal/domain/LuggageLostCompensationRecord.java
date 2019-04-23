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
 *  * <p>LuggageLostCompensationRecordDomian实体对象</p>
 * <p>
 *  行李遗失赔偿记录表
 * <p>
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @since 2019-04-22
 *  
 */
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
     * 行李遗失登记主键id
     */
    @TableField("lost_registration_record_id")
    private Long lostRegistrationRecordId;
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
     * 赔偿对象姓名(冗余)
     */
    @TableField("depositor_name")
    private String depositorName;
    /**
     * 赔偿对象电话(冗余)
     */
    @TableField("depositor_phone")
    private String depositorPhone;
    /**
     * 行李类型主键id(冗余)
     */
    @TableField("luggage_type_id")
    private Long luggageTypeId;
    /**
     * 赔偿金额
     */
    @TableField("compensation_fee")
    private String compensationFee;
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
