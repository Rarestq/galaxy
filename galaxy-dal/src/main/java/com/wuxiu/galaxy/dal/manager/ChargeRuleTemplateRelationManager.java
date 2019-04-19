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
import com.wuxiu.galaxy.dal.domain.ChargeRuleTemplateRelation;

import java.util.List;

/**  
 * ChargeRuleTemplateRelationManager
 * 计费规则-计费模板关联表
 * 
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-19
 */
public interface ChargeRuleTemplateRelationManager extends BaseManager<ChargeRuleTemplateRelation> {

    /**
     * 刷新 chargeRuleId 下的计费模板列表
     *
     * @param chargeRuleId
     * @param chargeTemplateIds
     */
    void refreshAll(Long chargeRuleId, List<Long> chargeTemplateIds);

    /**
     * 新增 chargeRuleId 对应的计费模板列表
     *
     * @param chargeRuleId
     * @param chargeTemplateIds
     */
    void insertNew(Long chargeRuleId, List<Long> chargeTemplateIds);

    /**
     * 通过计费规则id查询其对应的计费模板信息
     *
     * @param chargeRuleIds
     * @return
     */
    List<ChargeRuleTemplateRelation> selectTemplatesByChargeRuleIds(List<Long> chargeRuleIds);
}
