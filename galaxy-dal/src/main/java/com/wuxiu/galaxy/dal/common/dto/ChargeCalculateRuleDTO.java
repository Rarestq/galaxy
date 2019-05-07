package com.wuxiu.galaxy.dal.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 计费规则数据对象
 *
 * @author: wuxiu
 * @date: 2019/5/7 22:58
 */
@Data
public class ChargeCalculateRuleDTO implements Serializable {

    private static final long serialVersionUID = 6210973850986150890L;

    /**
     * 计费规则主键id
     */
    private Long calculationRuleId;
    /**
     * 行李类型主键id
     */
    private Long luggageTypeId;
    /**
     * 计费单位(1-元/件/天，2-元/件/次，3-元/件)
     */
    private Integer calculationUnitsId;
    /**
     * 单位金额
     */
    private String feePerUnit;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

}
