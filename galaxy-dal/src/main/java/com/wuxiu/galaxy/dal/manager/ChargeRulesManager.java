/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.base.BaseManager;
import com.wuxiu.galaxy.api.dto.ChargeRuleDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeRuleDTO;
import com.wuxiu.galaxy.dal.common.dto.ChargeRuleQueryDTO;
import com.wuxiu.galaxy.dal.domain.ChargeRules;

/**  
 * ChargeRulesManager
 * 计费规则表
 * 
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-16
 */
public interface ChargeRulesManager extends BaseManager<ChargeRules> {

    /**
     * 新增/编辑计费规则
     *
     * @param chargeRuleDTO
     * @return 计费规则主键id
     */
    Long saveChargeRule(SaveChargeRuleDTO chargeRuleDTO);

    /**
     * 获取计费规则列表
     *
     * @param queryDTO
     * @return
     */
    Page<ChargeRuleDTO> getChargeRuleList(ChargeRuleQueryDTO queryDTO);

    /**
     * 根据计费规则名称查询计费规则信息
     *
     * @param chargeRuleName
     * @return
     */
    ChargeRules getChargeRuleByName(String chargeRuleName);
}
