package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.api.service.ChargeCalculateRuleFacade;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.ChargeCalculateRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/5 14:20
 */
@Service("chargeCalculateRuleFacade")
public class ChargeCalculateRuleFacadeImpl implements ChargeCalculateRuleFacade {

    @Autowired
    private ChargeCalculateRuleService calculateRuleService;

    /**
     * 获取计费规则列表(key-计费规则id，value-行李类型名称)
     *
     * @return
     */
    @Override
    public APIResult<List<PairDTO<Long, String>>> getChargeCalculateRules() {
        return APIResult.ok(calculateRuleService.getChargeCalculateRules());
    }
}
