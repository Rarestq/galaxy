/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager;

import com.wuxiu.galaxy.dal.domain.ValuableCalculateRuleDetail;
import com.wuxiu.galaxy.api.common.base.BaseManager;

import java.util.List;

/**  
 * ValuableCalculateRuleDetailManager
 * 贵重行李计费规则表
 * 
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-05-02
 */
public interface ValuableCalculateRuleDetailManager extends BaseManager<ValuableCalculateRuleDetail> {

    /**
     * 根据计费规则id获取 ValuableCalculateRuleDetail 信息
     *
     * @param calculateRuleId 计费规则id
     * @return
     */
    List<ValuableCalculateRuleDetail> getValuableCalculateRuleDetails(
            Long calculateRuleId);
}
