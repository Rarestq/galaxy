/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.dal.common.utils.StreamUtil;
import com.wuxiu.galaxy.dal.dao.ChargeRuleTemplateRelationDao;
import com.wuxiu.galaxy.dal.domain.ChargeRuleTemplateRelation;
import com.wuxiu.galaxy.dal.manager.ChargeRuleTemplateRelationManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * <p>ChargeRuleTemplateRelationManager</p>
 * <p>
 * 计费规则-计费模板关联表
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-19
 */
@Component
public class ChargeRuleTemplateRelationManagerImpl extends BaseManagerImpl<ChargeRuleTemplateRelationDao, ChargeRuleTemplateRelation> implements ChargeRuleTemplateRelationManager{

    /**
     * 刷新 chargeRuleId 下的计费模板列表
     *
     * @param chargeRuleId
     * @param chargeTemplateIds
     */
    @Override
    public void refreshAll(Long chargeRuleId, List<Long> chargeTemplateIds) {
        if (Objects.isNull(chargeRuleId) || CollectionUtils.isEmpty(chargeTemplateIds)) {
            log.info("刷新失败，参数不能为空，chargeRuleId:{}, chargeTemplateIds:{}",
                    chargeRuleId, chargeTemplateIds);
            return;
        }

        Wrapper<ChargeRuleTemplateRelation> wrapper = new EntityWrapper<>();
        wrapper.eq("charge_rule_id", chargeRuleId);
        delete(wrapper);

        insertNew(chargeRuleId, chargeTemplateIds);
    }

    /**
     * 新增 chargeRuleId 对应的计费模板列表
     *
     * @param chargeRuleId
     * @param chargeTemplateIds
     */
    @Override
    public void insertNew(Long chargeRuleId, List<Long> chargeTemplateIds) {
        if (Objects.isNull(chargeRuleId) || CollectionUtils.isEmpty(chargeTemplateIds)) {
            log.info("新增失败，参数不能为空，chargeRuleId:{}, chargeTemplateIds:{}",
                    chargeRuleId, chargeTemplateIds);
            return;
        }

        List<ChargeRuleTemplateRelation> ruleTemplateRelations =
                StreamUtil.convert(chargeTemplateIds, chargeTemplateId -> {
                    ChargeRuleTemplateRelation ruleTemplateRelation =
                            new ChargeRuleTemplateRelation();
                    ruleTemplateRelation.setChargeRuleId(chargeRuleId);
                    ruleTemplateRelation.setChargeTemplateId(chargeTemplateId);

                    return ruleTemplateRelation;
                });

        insertBatch(ruleTemplateRelations);
    }
}
