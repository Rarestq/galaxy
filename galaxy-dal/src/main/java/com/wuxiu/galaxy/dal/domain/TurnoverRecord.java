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
 *  * <p>TurnoverRecordDomian实体对象</p>
 * <p>
 *  营业额记录表
 * <p>
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @since 2019-04-22
 *  
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("galaxy_turnover_record")
public class TurnoverRecord extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 营业额记录表主键id
     */
    @TableId(value = "turnover_record_id", type = IdType.AUTO)
    private Long turnoverRecordId;
    /**
     * 行李寄存主键id
     */
    @TableField("luggage_id")
    private Long luggageId;
    /**
     * 管理员主键id(冗余)
     */
    @TableField("admin_id")
    private Long adminId;
    /**
     * 计费规则主键id
     */
    @TableField("calculation_rule_id")
    private Long calculationRuleId;
    /**
     * 费用
     */
    private String fee;
    /**
     * 费用类型(0-寄存费用,1-逾期费用,2-赔偿费用)
     */
    @TableField("fee_type")
    private String feeType;
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
