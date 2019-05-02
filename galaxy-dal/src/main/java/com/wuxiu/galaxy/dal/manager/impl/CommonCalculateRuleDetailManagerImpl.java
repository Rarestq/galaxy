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
import com.wuxiu.galaxy.dal.domain.CommonCalculateRuleDetail;
import com.wuxiu.galaxy.dal.dao.CommonCalculateRuleDetailDao;
import com.wuxiu.galaxy.dal.manager.CommonCalculateRuleDetailManager;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>CommonCalculateRuleDetailManager</p>
 * <p>
 * 普通物价计费规则细节
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-05-02
 */
@Component
public class CommonCalculateRuleDetailManagerImpl extends BaseManagerImpl<CommonCalculateRuleDetailDao, CommonCalculateRuleDetail> implements CommonCalculateRuleDetailManager{

    /**
     * 根据计费规则id获取普通行李计费规则信息
     *
     * @param calculateRuleId 计费规则id
     * @return
     */
    @Override
    public List<CommonCalculateRuleDetail> getCommonCalculateRuleDetails(
            Long calculateRuleId) {
        Wrapper<CommonCalculateRuleDetail> wrapper =
                new EntityWrapper<CommonCalculateRuleDetail>()
                .eq("calculation_rule_id", calculateRuleId);

        return selectList(wrapper);
    }
}
