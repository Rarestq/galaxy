/**
 *  
 *  * All rights Reserved, Designed By wuxiu
 * <p>
 *  * @Package com.wuxiu.galaxy.dal.dao
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @date: 2018-04-16 20:35:12
 *  * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 *  
 */
package com.wuxiu.galaxy.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.api.dto.ChargeRuleDTO;
import com.wuxiu.galaxy.api.dto.ChargeTemplateDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeRuleDTO;
import com.wuxiu.galaxy.dal.common.dto.ChargeRuleQueryDTO;
import com.wuxiu.galaxy.dal.common.utils.StreamUtil;
import com.wuxiu.galaxy.dal.dao.ChargeRulesDao;
import com.wuxiu.galaxy.dal.domain.ChargeRuleTemplateRelation;
import com.wuxiu.galaxy.dal.domain.ChargeRules;
import com.wuxiu.galaxy.dal.manager.ChargeRuleTemplateRelationManager;
import com.wuxiu.galaxy.dal.manager.ChargeRulesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
public class ChargeRulesManagerImpl extends BaseManagerImpl<ChargeRulesDao, ChargeRules> implements ChargeRulesManager {

    @Autowired
    private ChargeRuleTemplateRelationManager relationManager;

    /**
     * 新增/编辑计费规则
     *
     * @param chargeRuleDTO
     * @return 计费规则主键id
     */
    @Override
    public Long saveChargeRule(SaveChargeRuleDTO chargeRuleDTO) {

        List<ChargeTemplateDTO> chargeTemplateDTOS =
                chargeRuleDTO.getChargeTemplateDTOList();
        List<Long> chargeTemplateIds = StreamUtil.collectDistinctKeyProperty(
                chargeTemplateDTOS, ChargeTemplateDTO::getChargeTemplateId);

        if (Objects.nonNull(chargeRuleDTO.getChargeRuleId())) {
            // 编辑计费规则信息
            ChargeRules updateChargeRule = new ChargeRules();
            updateChargeRule.setChargeRuleId(chargeRuleDTO.getChargeRuleId());
            updateChargeRule.setChargeRuleName(chargeRuleDTO.getChargeRuleName());
            updateChargeRule.setChargeRuleType(chargeRuleDTO.getChargeRuleType());
            updateChargeRule.setGmtModified(LocalDateTime.now());

            updateById(updateChargeRule);

            // 同步更新计费规则-计费模板关联表的信息(先逻辑删除原来的，再添加)
            relationManager.refreshAll(chargeRuleDTO.getChargeRuleId(), chargeTemplateIds);

            return chargeRuleDTO.getChargeRuleId();
        }

        // 新增计费规则信息
        ChargeRules insertChargeRule = new ChargeRules();
        insertChargeRule.setChargeRuleName(chargeRuleDTO.getChargeRuleName());
        insertChargeRule.setChargeRuleType(chargeRuleDTO.getChargeRuleType());

        insert(insertChargeRule);

        // 将计费规则对应的计费模板插入到计费规则-计费模板关联表中
        relationManager.insertNew(chargeRuleDTO.getChargeRuleId(), chargeTemplateIds);

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
        List<Long> chargeRuleIds =
                StreamUtil.convert(chargeRules, ChargeRules::getChargeRuleId);

        // 通过计费规则id查询其对应的计费模板信息
        List<ChargeRuleTemplateRelation> ruleTemplateRelations =
                relationManager.selectTemplatesByChargeRuleIds(chargeRuleIds);
        List<Long> chargeTemplateIds = StreamUtil.convert(ruleTemplateRelations,
                ChargeRuleTemplateRelation::getChargeTemplateId);

        // 将查询到的计费模板信息按照计费规则id进行分组
        Map<Long, List<ChargeRuleTemplateRelation>> chargeRuleTemplateMap =
                ruleTemplateRelations.stream().collect(Collectors.groupingBy
                        (ChargeRuleTemplateRelation::getChargeRuleId));

        // 将 ChargeRules 对象转化为 ChargeRuleDTO 对象
        chargeRules.forEach(rule -> {
            ChargeRuleDTO ruleDTO = new ChargeRuleDTO();
            ruleDTO.setChargeRuleId(rule.getChargeRuleId());
            ruleDTO.setChargeRuleName(rule.getChargeRuleName());

            //todo: 将 ChargeTemplate 转化为 ChargeTemplateDTO

            //ruleDTO.setChargeTemplateDTOS();
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
        wrapper.eq("charge_rule_name", chargeRuleName);

        return selectOne(wrapper);
    }
}
