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
import com.wuxiu.galaxy.api.common.base.BaseModel;
import com.baomidou.mybatisplus.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**  
 * <p>AdminDomian实体对象</p>
 *
 * 管理员表 - 按照编号前缀的不同具有不同权限
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-16
 */

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_admin")
public class Admin extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 管理员id(主键)
     */
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Long adminId;
    /**
     * 管理员编号(随机生成)
     */
    @TableField("admin_no")
    private String adminNo;
    /**
     * 管理员姓名
     */
    @TableField("admin_name")
    private String adminName;
    /**
     * 管理员电话
     */
    @TableField("admin_phone")
    private String adminPhone;
    /**
     * 管理员性别(0-男，1-女)
     */
    private Integer gender;
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
