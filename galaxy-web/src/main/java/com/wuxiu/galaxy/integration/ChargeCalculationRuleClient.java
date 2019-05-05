package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.api.service.ChargeCalculateRuleFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/5 14:18
 */
@Service
public class ChargeCalculationRuleClient {

    @Autowired
    private ChargeCalculateRuleFacade calculateRuleFacade;

    /**
     * 获取计费规则列表(key-计费规则id，value-行李类型名称)
     *
     * @return
     */
    public APIResult<List<PairDTO<Long, String>>> getChargeCalculateRules() {
        return calculateRuleFacade.getChargeCalculateRules();
    }
}
