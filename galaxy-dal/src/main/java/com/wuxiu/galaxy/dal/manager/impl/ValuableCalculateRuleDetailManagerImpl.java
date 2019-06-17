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
import com.wuxiu.galaxy.dal.dao.ValuableCalculateRuleDetailDao;
import com.wuxiu.galaxy.dal.domain.ValuableCalculateRuleDetail;
import com.wuxiu.galaxy.dal.manager.ValuableCalculateRuleDetailManager;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>ValuableCalculateRuleDetailManager</p>
 * <p>
 * 贵重行李计费规则表
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-05-02
 */
@Component
public class ValuableCalculateRuleDetailManagerImpl extends BaseManagerImpl<ValuableCalculateRuleDetailDao, ValuableCalculateRuleDetail> implements ValuableCalculateRuleDetailManager{

    /**
     * 根据计费规则id获取 ValuableCalculateRuleDetail 信息
     *
     * @param calculateRuleId 计费规则id
     * @return
     */
    @Override
    public List<ValuableCalculateRuleDetail> getValuableCalculateRuleDetails(
            Long calculateRuleId) {
        Wrapper<ValuableCalculateRuleDetail> wrapper =
                new EntityWrapper<ValuableCalculateRuleDetail>()
                        .eq("calculation_rule_id", calculateRuleId);

        return selectList(wrapper);
    }

}
