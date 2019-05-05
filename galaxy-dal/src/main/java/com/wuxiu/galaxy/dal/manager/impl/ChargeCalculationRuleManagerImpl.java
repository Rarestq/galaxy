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
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.common.utils.StringSpliceUtils;
import com.wuxiu.galaxy.dal.dao.ChargeCalculationRuleDao;
import com.wuxiu.galaxy.dal.domain.ChargeCalculationRule;
import com.wuxiu.galaxy.dal.manager.ChargeCalculationRuleManager;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

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
}
