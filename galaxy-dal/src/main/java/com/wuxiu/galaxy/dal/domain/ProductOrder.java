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
 * <p>ProductOrderDomian实体对象</p>
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
@TableName("product_order")
public class ProductOrder extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 产品订单id，主键，自增长
     */
    @TableId(value = "product_order_id", type = IdType.AUTO)
    private Long productOrderId;
    /**
     * 产品id
     */
    @TableField("product_id")
    private Long productId;
    /**
     * 订单编号
     */
    @TableField("product_order_no")
    private String productOrderNo;
    /**
     * 下单人id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 下单人姓名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 寄送信息
     */
    @TableField("send_information")
    private String sendInformation;
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
