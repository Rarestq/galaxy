package com.wuxiu.galaxy.service.core.biz.service.apiservice;

import com.wuxiu.galaxy.api.dto.ChargeCalculateRuleDTO;
import com.wuxiu.galaxy.api.dto.PairDTO;

import java.util.List;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/5 14:21
 */
public interface ChargeCalculateRuleService {

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
    List<ChargeCalculateRuleDTO> getChargeCalculateRuleList();
}
