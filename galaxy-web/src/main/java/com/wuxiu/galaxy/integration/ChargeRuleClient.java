package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.*;
import com.wuxiu.galaxy.api.service.ChargeRuleFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:31
 */
@Service
public class ChargeRuleClient {

    @Autowired
    private ChargeRuleFacade chargeRuleFacade;

    /**
     * 新增/编辑计费规则
     *
     * @param chargeRuleDTO
     * @return
     */
    public APIResult<Long> saveChargeRule(SaveChargeRuleDTO chargeRuleDTO) {
        return chargeRuleFacade.saveChargeRule(chargeRuleDTO);
    }

    /**
     * 获取计费规则列表
     *
     * @param queryDTO
     * @return
     */
    public APIResult<PageInfo<ChargeRuleDTO>> getChargeRuleList(ChargeRuleQueryDTO queryDTO) {
        return chargeRuleFacade.getChargeRuleList(queryDTO);
    }

    /**
     * 删除计费规则
     *
     * @return
     */
    public APIResult<Void> deleteChargeRule(ChargeRuleDeleteDTO deleteDTO) {
        return chargeRuleFacade.deleteChargeRule(deleteDTO);
    }
}
