package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.integration.ChargeCalculationRuleClient;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.web.biz.service.GwChargeCalculateRuleService;
import com.wuxiu.galaxy.web.biz.vo.Pair;
import com.wuxiu.galaxy.web.utils.ObjectConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/5 14:16
 */
@Slf4j
@Service
public class GwChargeCalculateRuleServiceImpl implements GwChargeCalculateRuleService {

    @Autowired
    private ChargeCalculationRuleClient calculationRuleClient;

    /**
     * 获取计费规则列表(key-计费规则id，value-行李类型名称)
     *
     * @return
     */
    @Override
    public APIResult<List<Pair<Long, String>>> getChargeCalculateRules() {
        APIResult<List<PairDTO<Long, String>>> calculateRulesAPIResult =
                calculationRuleClient.getChargeCalculateRules();

        if (!calculateRulesAPIResult.isSuccess()) {
            log.warn("获取计费规则列表失败，result:{}", calculateRulesAPIResult);
            return CommonUtil.errorAPIResult(calculateRulesAPIResult);
        }

        List<PairDTO<Long, String>> data = calculateRulesAPIResult.getData();
        if (CollectionUtils.isEmpty(data)) {
            return APIResult.ok(Collections.emptyList());
        }
        // 将 PairDTO 转化为 Pair
        List<Pair<Long, String>> calculateRulesPair =
                ObjectConvertUtil.convertDTO2Domain(data);

        return APIResult.ok(calculateRulesPair);
    }
}
