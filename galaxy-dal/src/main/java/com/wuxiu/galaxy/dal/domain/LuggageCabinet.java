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
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 *   
 *  * <p>LuggageCabinetDomian实体对象</p>
 * <p>
 *  行李寄存柜表
 * <p>
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @since 2019-05-15
 *  
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_luggage_cabinet")
public class LuggageCabinet extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 行李柜主键id
     */
    @TableId(value = "luggage_cabinet_id", type = IdType.AUTO)
    private Long luggageCabinetId;
    /**
     * 行李柜编号
     */
    @TableField("luggage_cabinet_no")
    private String luggageCabinetNo;
    /**
     * 行李柜状态(0-空闲、1-已占用、2-逾期占用、3-维修中)
     */
    private Integer status;
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
