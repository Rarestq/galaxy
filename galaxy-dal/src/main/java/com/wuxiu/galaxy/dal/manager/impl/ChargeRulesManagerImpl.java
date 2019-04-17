/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.api.dto.ChargeRuleDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeRuleDTO;
import com.wuxiu.galaxy.dal.common.dto.ChargeRuleQueryDTO;
import com.wuxiu.galaxy.dal.dao.ChargeRulesDao;
import com.wuxiu.galaxy.dal.domain.ChargeRules;
import com.wuxiu.galaxy.dal.manager.ChargeRulesManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;

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

    /**
     * 获取计费规则列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public Page<ChargeRuleDTO> getChargeRuleList(ChargeRuleQueryDTO queryDTO) {
        // 构造查询参数
        EntityWrapper<ChargeRules> wrapper = new EntityWrapper<>();
        if (Objects.nonNull(queryDTO.getChargeRuleId())) {
            wrapper.eq("charge_rule_id", queryDTO.getChargeRuleId());
        }

        if (Objects.nonNull(queryDTO.getChargeRuleName())) {
            wrapper.eq("charge_rule_name", queryDTO.getChargeRuleName());
        }

        if (Objects.nonNull(queryDTO.getChargeRuleType())) {
            wrapper.eq("charge_rule_type", queryDTO.getChargeRuleType());
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("charge_rule_id", false);

        // 获取计费规则列表信息
        Page<ChargeRules> chargeRulesPage = selectPage(queryDTO.getPage(), wrapper);

        return buildChargeRuleDTOS(chargeRulesPage);
    }

    /**
     * 构造 ChargeRules 列表信息
     *
     * @param chargeRulesPage
     * @return
     */
    private Page<ChargeRuleDTO> buildChargeRuleDTOS(Page<ChargeRules> chargeRulesPage) {

        List<ChargeRuleDTO> ruleDTOS = newArrayList();
        List<ChargeRules> chargeRules = chargeRulesPage.getRecords();

        // 将 ChargeRules 对象转化为 ChargeRuleDTO 对象
        chargeRules.forEach(rule -> {
            ChargeRuleDTO ruleDTO = new ChargeRuleDTO();
            ruleDTO.setChargeRuleId(rule.getChargeRuleId());
            ruleDTO.setChargeRuleName(rule.getChargeRuleName());

            ruleDTOS.add(ruleDTO);
        });

        Page<ChargeRuleDTO> page = new Page<>();
        page.setRecords(ruleDTOS);
        page.setTotal(chargeRulesPage.getTotal());

        return page;
    }

    /**
     * 根据计费规则名称查询计费规则信息
     *
     * @param chargeRuleName
     * @return
     */
    @Override
    public ChargeRules getChargeRuleByName(String chargeRuleName) {
        Wrapper<ChargeRules> wrapper = new EntityWrapper<>();
        wrapper.eq("charge_template_name", chargeRuleName);

        return selectOne(wrapper);
    }
}
