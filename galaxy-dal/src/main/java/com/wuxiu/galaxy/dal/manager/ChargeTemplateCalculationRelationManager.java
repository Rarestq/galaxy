/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager;

import com.wuxiu.galaxy.api.common.base.BaseManager;
import com.wuxiu.galaxy.dal.domain.ChargeTemplateCalculationRelation;

import java.util.List;

/**  
 * ChargeTemplateCalculationRelationManager
 * 计费模板-计算规则关联表
 * 
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-19
 */
public interface ChargeTemplateCalculationRelationManager extends BaseManager<ChargeTemplateCalculationRelation> {

    /**
     * 刷新 chargeTemplateId 对应的计算规则列表
     *
     * @param chargeTemplateId
     * @param calculationRuleIds
     */
    void refreshAll(Long chargeTemplateId, List<Long> calculationRuleIds);

    /**
     * 新增 chargeTemplateId 对应的计算规则列表
     *
     * @param chargeTemplateId
     * @param calculationRuleIds
     */
    void insertNew(Long chargeTemplateId, List<Long> calculationRuleIds);

    /**
     * 通过计费模板id查询其对应的计算规则信息
     *
     * @param chargeTemplateIds
     * @return
     */
    List<ChargeTemplateCalculationRelation> selectCalculateRulesByTemplateIds(List<Long> chargeTemplateIds);
}
