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
import com.wuxiu.galaxy.common.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 *   
 *  * <p>WalletOrderDomian实体对象</p>
 * <p>
 *  
 * <p>
 *  * @author: MHC_Generater（MHC@maihaoche.com）
 *  * @since 2019-04-01
 *  
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wallet_order")
public class WalletOrder extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 钱包流水id，主键，自增长
     */
    @TableId(value = "wallet_order_id", type = IdType.AUTO)
    private Long walletOrderId;
    /**
     * 钱包id
     */
    @TableField("wallet_id")
    private Long walletId;
    /**
     * 流水是否被审批，0-审批不通过，1-审批通过，2-未处理
     */
    @TableField("wallet_order_state")
    private Integer walletOrderState;
    /**
     * 余额充值
     */
    private BigDecimal recharge;
    /**
     * 余额提现
     */
    private BigDecimal withdraw;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 用户名称
     */
    @TableField("user_name")
    private String userName;
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
    /**
     * 钱包订单流水号
     */
    @TableField("wallet_order_no")
    private String walletOrderNo;

}
