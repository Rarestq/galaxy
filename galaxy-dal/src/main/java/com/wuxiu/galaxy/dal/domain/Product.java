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
 * <p>ProductDomian实体对象</p>
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
public class Product extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 商品id，主键，自增长
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;
    /**
     * 商品名称
     */
    @TableField("product_name")
    private String productName;
    /**
     * 商品价格
     */
    @TableField("product_price")
    private BigDecimal productPrice;
    /**
     * 商品图片
     */
    @TableField("product_image")
    private String productImage;
    /**
     * 商品描述
     */
    @TableField("product_description")
    private String productDescription;
    /**
     * 生产商id
     */
    @TableField("product_manufacture_id")
    private Long productManufactureId;
    /**
     * 生产商名称
     */
    @TableField("product_manufacture_name")
    private String productManufactureName;
    /**
     * 逻辑删除，0-存在，1-已删除
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
     * 是否上架，0-未上架，1-已上架
     */
    @TableField("is_shelf")
    private Integer isShelf;

}
