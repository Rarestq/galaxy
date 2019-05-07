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
package com.wuxiu.galaxy.dal.manager;

import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.domain.ChargeCalculationRule;
import com.wuxiu.galaxy.api.common.base.BaseManager;

import java.util.List;

/**
 *   
 * ChargeCalculationRuleManager
 *  * 计费规则表
 *  
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *
 * @since 2019-05-02
 *  
 */
public interface ChargeCalculationRuleManager extends BaseManager<ChargeCalculationRule> {

    /**
     * 获取计费规则列表(key-计费规则id，value-行李类型名称)
     *
     * @return
     */
    List<PairDTO<Long, String>> getChargeCalculateRules();

    /**
     * 根据行李类型联动计费规则
     *
     * @param luggageTypeId
     * @return
     */
    List<PairDTO<Long, String>> queryCalculateRulesByLuggageType(Long luggageTypeId);

    /**
     * 获取计费规则列表
     *
     * @return
     */
    List<ChargeCalculationRule> getChargeCalculateRuleList();
}
