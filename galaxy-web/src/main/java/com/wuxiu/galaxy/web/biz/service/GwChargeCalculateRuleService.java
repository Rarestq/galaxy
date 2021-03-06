package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.web.biz.vo.ChargeCalculateRuleVO;
import com.wuxiu.galaxy.web.biz.vo.Pair;

import java.util.List;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/5 14:15
 */
public interface GwChargeCalculateRuleService {

    /**
     * 获取计费规则列表(key-计费规则id，value-行李类型名称)
     *
     * @return
     */
    APIResult<List<Pair<Long, String>>> getChargeCalculateRules();

    /**
     * 根据行李类型联动计费规则
     *
     * @param luggageTypeId
     * @return
     */
    APIResult<List<Pair<Long, String>>> queryCalculateRulesByLuggageType(
            Long luggageTypeId);

    /**
     * 获取计费规则列表
     *
     * @return
     */
    APIResult<List<ChargeCalculateRuleVO>> getChargeCalculateRuleList();
}
