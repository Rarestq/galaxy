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
import com.google.common.collect.Lists;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.common.utils.StringSpliceUtils;
import com.wuxiu.galaxy.dal.dao.ChargeCalculationRuleDao;
import com.wuxiu.galaxy.dal.domain.ChargeCalculationRule;
import com.wuxiu.galaxy.dal.manager.ChargeCalculationRuleManager;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;

/**
 * <p>ChargeCalculationRuleManager</p>
 * <p>
 * 计费规则表
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-05-02
 */
@Component
public class ChargeCalculationRuleManagerImpl extends BaseManagerImpl<ChargeCalculationRuleDao, ChargeCalculationRule> implements ChargeCalculationRuleManager{

    /**
     * 获取计费规则列表(key-计费规则id，value-行李类型名称)
     *
     * @return
     */
    @Override
    public List<PairDTO<Long, String>> getChargeCalculateRules() {
        // 构造查询条件（因为逻辑删除字段加了 @TableLogic 注解，所以构造条件中不需要加此条件）
        Wrapper<ChargeCalculationRule> wrapper = new EntityWrapper<>();

        List<ChargeCalculationRule> calculationRules = selectList(wrapper);

        if (CollectionUtils.isEmpty(calculationRules)) {
            return Collections.emptyList();
        }

        // 将查询出来的 calculationRules 转化为 List<PairDTO<Long, String>> 形式
        List<PairDTO<Long, String>> pairDTOList = Lists.newArrayList();
        calculationRules.forEach(calculationRule -> {
            PairDTO<Long, String> pairDTO = new PairDTO<>();
            pairDTO.setKey(calculationRule.getCalculationRuleId());
            pairDTO.setValue(StringSpliceUtils.getChargeCalculateRule(calculationRule.getLuggageTypeId()));
            pairDTOList.add(pairDTO);
        });

        return pairDTOList;
    }

    /**
     * 根据行李类型联动计费规则
     *
     * @param luggageTypeId
     * @return
     */
    @Override
    public List<PairDTO<Long, String>> queryCalculateRulesByLuggageType(
            Long luggageTypeId) {

        if (Objects.isNull(luggageTypeId)) {
            return Collections.emptyList();
        }

        // 构造查询条件
        Wrapper<ChargeCalculationRule> wrapper = new EntityWrapper<>();
        wrapper.eq("luggage_type_id", luggageTypeId);

        // 根据费用类型获取计费规则信息
        List<ChargeCalculationRule> chargeCalculationRules = selectList(wrapper);
        if (CollectionUtils.isEmpty(chargeCalculationRules)) {
            return Collections.emptyList();
        }

        List<PairDTO<Long, String>> ruleLuggageTypePairList = newArrayList();

        // 构建计费规则 PairDTO 对象
        chargeCalculationRules.forEach(rule -> {
            PairDTO<Long, String> ruleLuggageTypePair = new PairDTO<>();
            ruleLuggageTypePair.setKey(rule.getCalculationRuleId());
            ruleLuggageTypePair.setValue(spliceRuleDesc(rule));
            ruleLuggageTypePairList.add(ruleLuggageTypePair);
        });

        return ruleLuggageTypePairList;
    }

    /**
     * 获取计费规则列表
     *
     * @return
     */
    @Override
    public List<ChargeCalculationRule> getChargeCalculateRuleList() {
        Wrapper<ChargeCalculationRule> wrapper = new EntityWrapper<>();

        return selectList(wrapper);
    }

    private static String spliceRuleDesc(ChargeCalculationRule rule) {
        return LuggageTypeEnum.getDescByCode(rule.getLuggageTypeId()) + "规则-单位：" +
                rule.getCalculationUnitsId();
    }
}
