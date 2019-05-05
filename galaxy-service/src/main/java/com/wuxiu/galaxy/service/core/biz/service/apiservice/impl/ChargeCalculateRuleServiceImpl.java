package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.manager.ChargeCalculationRuleManager;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.ChargeCalculateRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/5 14:21
 */
@Slf4j
@Service
public class ChargeCalculateRuleServiceImpl implements ChargeCalculateRuleService {

    @Autowired
    private ChargeCalculationRuleManager calculationRuleManager;

    /**
     * 获取计费规则列表(key-计费规则id，value-行李类型名称)
     *
     * @return
     */
    @Override
    public List<PairDTO<Long, String>> getChargeCalculateRules() {
        List<PairDTO<Long, String>> chargeCalculateRules =
                calculationRuleManager.getChargeCalculateRules();

        if (CollectionUtils.isEmpty(chargeCalculateRules)) {
            return Collections.emptyList();
        }

        return chargeCalculateRules;
    }
}
