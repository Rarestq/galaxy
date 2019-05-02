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
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.dal.dao.FragileCalculateRuleDetailDao;
import com.wuxiu.galaxy.dal.domain.FragileCalculateRuleDetail;
import com.wuxiu.galaxy.dal.manager.FragileCalculateRuleDetailManager;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>FragileCalculateRuleDetailManager</p>
 * <p>
 * 易碎行李计费规则细节表
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-05-02
 */
@Component
public class FragileCalculateRuleDetailManagerImpl extends BaseManagerImpl<FragileCalculateRuleDetailDao, FragileCalculateRuleDetail> implements FragileCalculateRuleDetailManager{

    /**
     * 根据计费规则id获取 FragileCalculateRuleDetail 信息
     *
     * @param calculateRuleId 计费规则id
     * @return
     */
    @Override
    public List<FragileCalculateRuleDetail> getFragileCalculateRuleDetails(
            Long calculateRuleId) {
        Wrapper<FragileCalculateRuleDetail> wrapper =
                new EntityWrapper<FragileCalculateRuleDetail>()
                        .eq("calculation_rule_id", calculateRuleId);

        return selectList(wrapper);
    }

}
