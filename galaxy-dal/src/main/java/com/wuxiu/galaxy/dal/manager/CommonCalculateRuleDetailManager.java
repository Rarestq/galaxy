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

import com.wuxiu.galaxy.api.common.base.BaseManager;
import com.wuxiu.galaxy.dal.domain.CommonCalculateRuleDetail;

import java.util.List;

/**
 *   
 * CommonCalculateRuleDetailManager
 *  * 普通物价计费规则细节
 *  
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *
 * @since 2019-05-02
 *  
 */
public interface CommonCalculateRuleDetailManager extends BaseManager<CommonCalculateRuleDetail> {

    /**
     * 根据计费规则id获取普通行李计费规则信息
     *
     * @param calculateRuleId 计费规则id
     * @return
     */
    List<CommonCalculateRuleDetail> getCommonCalculateRuleDetails(Long calculateRuleId);

}
