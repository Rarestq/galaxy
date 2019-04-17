package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.ChargeRuleDTO;
import com.wuxiu.galaxy.api.dto.ChargeRuleDeleteDTO;
import com.wuxiu.galaxy.api.dto.ChargeRuleQueryDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeRuleDTO;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:31
 */
public interface ChargeRuleFacade {

    /**
     * 新增/编辑计费规则
     *
     * @param chargeRuleDTO
     * @return
     */
    APIResult<Long> saveChargeRule(SaveChargeRuleDTO chargeRuleDTO);

    /**
     * 获取计费规则列表
     *
     * @param queryDTO
     * @return
     */
    APIResult<PageInfo<ChargeRuleDTO>> getChargeRuleList(ChargeRuleQueryDTO queryDTO);

    /**
     * 删除计费规则
     *
     * @return
     */
    APIResult<Void> deleteChargeRule(ChargeRuleDeleteDTO deleteDTO);
}
