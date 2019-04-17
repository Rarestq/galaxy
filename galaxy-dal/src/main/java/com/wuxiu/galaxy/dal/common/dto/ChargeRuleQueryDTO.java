package com.wuxiu.galaxy.dal.common.dto;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.dal.domain.ChargeRules;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 计费规则查询对象
 *
 * @author: wuxiu
 * @date: 2019/4/17 15:04
 */
@ApiModel("计费规则查询对象")
@Data
public class ChargeRuleQueryDTO implements Serializable {

    private static final long serialVersionUID = 6736554433098662964L;

    /**
     * 分页条件
     */
    Page<ChargeRules> page;

    /**
     * 计费规则名称
     */
    private String chargeRuleName;

    /**
     * 计费规则类型(1-应收，2-应付，3-增值)
     */
    private Integer chargeRuleType;

}
