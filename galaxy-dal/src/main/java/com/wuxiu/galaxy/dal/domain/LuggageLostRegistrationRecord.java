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
 *  * <p>LuggageLostRegistrationRecordDomian实体对象</p>
 * <p>
 *  行李遗失登记记录表
 * <p>
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @since 2019-04-16
 *  
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_luggage_lost_registration_record")
public class LuggageLostRegistrationRecord extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 行李遗失登记主键id
     */
    @TableId(value = "lost_registration_record_id", type = IdType.AUTO)
    private Long lostRegistrationRecordId;
    /**
     * 管理员id
     */
    @TableField("admin_id")
    private Long adminId;
    /**
     * 行李寄存主键id
     */
    @TableField("luggage_id")
    private Long luggageId;
    /**
     * 行李寄存记录编号(冗余)
     */
    @TableField("luggage_record_code")
    private String luggageRecordCode;
    /**
     * 行李类型主键id(冗余)
     */
    @TableField("luggage_type_id")
    private Long luggageTypeId;
    /**
     * 管理员姓名(冗余)
     */
    @TableField("admin_name")
    private String adminName;
    /**
     * 行李遗失登记者姓名(冗余)
     */
    @TableField("depositor_name")
    private String depositorName;
    /**
     * 行李遗失登记者联系方式(冗余)
     */
    @TableField("depositor_phone")
    private String depositorPhone;
    /**
     * 行李遗失登记备注
     */
    private String remark;
    /**
     * 登记时间
     */
    @TableField("registration_time")
    private LocalDateTime registrationTime;
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
