package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.ChargeCalculateRuleDTO;
import com.wuxiu.galaxy.api.dto.PairDTO;

import java.util.List;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/5 14:19
 */
public interface ChargeCalculateRuleFacade {

    /**
     * 获取计费规则列表(key-计费规则id，value-行李类型名称)
     *
     * @return
     */
    APIResult<List<PairDTO<Long, String>>> getChargeCalculateRules();

    /**
     * 根据行李类型联动计费规则
     *
     * @param luggageTypeId
     * @return
     */
    APIResult<List<PairDTO<Long, String>>> queryCalculateRulesByLuggageType(
            Long luggageTypeId);

    /**
     * 获取计费规则列表
     *
     * @return
     */
    APIResult<List<ChargeCalculateRuleDTO>> getChargeCalculateRuleList();
}
