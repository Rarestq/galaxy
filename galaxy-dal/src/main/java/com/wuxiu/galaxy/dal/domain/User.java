/** 
 * All rights Reserved, Designed By www.maihaoche.com
 *
 * @Package com.wuxiu.galaxy.dal.domain
 * @author: MHC_Generater（duanzhang@maihaoche.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2017-2020 www.maihaoche.com Inc. All rights reserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wuxiu.galaxy.dal.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.mhc.framework.common.base.dal.BaseModel;
import com.baomidou.mybatisplus.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**  
 * <p>UserDomian实体对象</p>
 *
 * 
 *
 * @author: MHC_Generater（MHC@maihaoche.com）
 * @since 2019-04-01
 */

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    /**
     * 用户名称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 登录手机
     */
    private String phone;
    /**
     * 角色权限，0-管理员，1-品牌商，2-借卖方
     */
    private Integer permission;
    /**
     * 0-审批不通过，1-审批通过，2-未处理
     */
    @TableField("is_approval")
    private Integer isApproval;
    /**
     * 逻辑删除，0-存在，1-已被删除
     */
    @TableField("is_delete")
    private Integer isDelete;
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
