package com.wuxiu.galaxy.service.core.biz.service;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.ChargeRuleDTO;
import com.wuxiu.galaxy.api.dto.ChargeRuleDeleteDTO;
import com.wuxiu.galaxy.api.dto.ChargeRuleQueryDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeRuleDTO;

/**
 * ChargeRuleFacadeImpl
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:33
 */
public interface ChargeRuleService {

    /**
     * 新增/编辑计费规则
     *
     * @param chargeRuleDTO
     * @return
     */
    Long saveChargeRule(SaveChargeRuleDTO chargeRuleDTO);

    /**
     * 获取计费规则列表
     *
     * @param queryDTO
     * @return
     */
    PageInfo<ChargeRuleDTO> getChargeRuleList(ChargeRuleQueryDTO queryDTO);

    /**
     * 删除计费规则
     *
     * @return
     */
    void deleteChargeRule(ChargeRuleDeleteDTO deleteDTO);
}
