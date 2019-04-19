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
import com.wuxiu.galaxy.dal.dao.ChargeTemplateCalculationRelationDao;
import com.wuxiu.galaxy.dal.domain.ChargeTemplateCalculationRelation;
import com.wuxiu.galaxy.dal.manager.ChargeTemplateCalculationRelationManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * <p>ChargeTemplateCalculationRelationManager</p>
 * <p>
 * 计费模板-计算规则关联表
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-19
 */
@Component
public class ChargeTemplateCalculationRelationManagerImpl extends BaseManagerImpl<ChargeTemplateCalculationRelationDao, ChargeTemplateCalculationRelation> implements ChargeTemplateCalculationRelationManager{

    /**
     * 刷新 chargeTemplateId 对应的计算规则列表
     *
     * @param chargeTemplateId
     * @param calculationRuleIds
     */
    @Override
    public void refreshAll(Long chargeTemplateId,
                           List<Long> calculationRuleIds) {
        if (Objects.isNull(chargeTemplateId) || CollectionUtils.isEmpty(calculationRuleIds)) {
            log.info("刷新失败，参数不能为空，chargeTemplateId:{}, calculationRuleIds:{}",
                    chargeTemplateId, calculationRuleIds);
            return;
        }

        Wrapper<ChargeTemplateCalculationRelation> wrapper = new EntityWrapper<>();
        wrapper.eq("charge_template_id", chargeTemplateId);
        delete(wrapper);

    }

    /**
     * 新增 chargeTemplateId 对应的计算规则列表
     *
     * @param chargeTemplateId
     * @param calculationRuleIds
     */
    @Override
    public void insertNew(Long chargeTemplateId, List<Long> calculationRuleIds) {
        if (Objects.isNull(chargeTemplateId) || CollectionUtils.isEmpty(calculationRuleIds)) {
            log.info("新增失败，参数不能为空，chargeRuleId:{}, calculationRuleIds:{}",
                    chargeTemplateId, calculationRuleIds);
            return;
        }

        List<ChargeTemplateCalculationRelation> templateCalculationRelations =
                StreamUtil.convert(calculationRuleIds, calculationRuleId -> {
                    ChargeTemplateCalculationRelation templateCalculationRelation =
                            new ChargeTemplateCalculationRelation();
                    templateCalculationRelation.setChargeTemplateId(chargeTemplateId);
                    templateCalculationRelation.setCalculationRuleId(calculationRuleId);

                    return templateCalculationRelation;
                });

        insertBatch(templateCalculationRelations);
    }
}
