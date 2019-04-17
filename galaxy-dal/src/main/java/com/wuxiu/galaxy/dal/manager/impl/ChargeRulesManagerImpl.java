/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager.impl;

import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.api.dto.SaveChargeRuleDTO;
import com.wuxiu.galaxy.dal.dao.ChargeRulesDao;
import com.wuxiu.galaxy.dal.domain.ChargeRules;
import com.wuxiu.galaxy.dal.manager.ChargeRulesManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>ChargeRulesManager</p>
 * <p>
 * 计费规则表
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-16
 */
@Component
public class ChargeRulesManagerImpl extends BaseManagerImpl<ChargeRulesDao, ChargeRules> implements ChargeRulesManager{

    /**
     * 新增/编辑计费规则
     *
     * @param chargeRuleDTO
     * @return 计费规则主键id
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Long saveChargeRule(SaveChargeRuleDTO chargeRuleDTO) {
        //todo:新增/编辑计费规则信息时，还应当同步更新「计费模板」和「计算规则表的信息」
        if (Objects.nonNull(chargeRuleDTO.getChargeRuleId())) {
            // 编辑计费规则信息
            ChargeRules updateChargeRule = new ChargeRules();
            updateChargeRule.setChargeRuleId(chargeRuleDTO.getChargeRuleId());
            updateChargeRule.setChargeRuleName(chargeRuleDTO.getChargeRuleName());
            updateChargeRule.setChargeRuleType(chargeRuleDTO.getChargeRuleType());
            updateChargeRule.setGmtModified(LocalDateTime.now());

            updateById(updateChargeRule);

            return chargeRuleDTO.getChargeRuleId();
        }

        // 新增计费规则信息
        ChargeRules insertChargeRule = new ChargeRules();
        insertChargeRule.setChargeRuleName(chargeRuleDTO.getChargeRuleName());
        insertChargeRule.setChargeRuleType(chargeRuleDTO.getChargeRuleType());

        insert(insertChargeRule);

        return insertChargeRule.getChargeRuleId();
    }
}
