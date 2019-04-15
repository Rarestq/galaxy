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
 * <p>LuggageOverdueRecordDomian实体对象</p>
 *
 * 行李逾期未取记录表
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-15
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_luggage_overdue_record")
public class LuggageOverdueRecord extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 行李逾期未取记录主键id
     */
    @TableId(value = "luggage_overdue_record_id", type = IdType.AUTO)
    private Long luggageOverdueRecordId;
    /**
     * 管理员主键id
     */
    @TableField("admin_id")
    private Integer adminId;
    /**
     * 行李寄存主键id
     */
    @TableField("luggage_id")
    private Long luggageId;
    /**
     * 管理员姓名(冗余)
     */
    @TableField("admin_name")
    private String adminName;
    /**
     * 行李寄存者姓名(冗余)
     */
    @TableField("depositor_name")
    private String depositorName;
    /**
     * 行李寄存者联系方式(冗余)
     */
    @TableField("depositor_phone")
    private String depositorPhone;
    /**
     * 行李逾期未取备注
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
