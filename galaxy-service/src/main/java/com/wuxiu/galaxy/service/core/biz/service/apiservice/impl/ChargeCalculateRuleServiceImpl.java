package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.dto.ChargeCalculateRuleDTO;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.common.utils.StringSpliceUtils;
import com.wuxiu.galaxy.dal.domain.ChargeCalculationRule;
import com.wuxiu.galaxy.dal.manager.ChargeCalculationRuleManager;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.ChargeCalculateRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

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

    /**
     * 根据行李类型联动计费规则
     *
     * @param luggageTypeId
     * @return
     */
    @Override
    public List<PairDTO<Long, String>> queryCalculateRulesByLuggageType(
            Long luggageTypeId) {

        log.info("根据行李类型联动计费规则，仓库id：{}", luggageTypeId);

        if (Objects.isNull(luggageTypeId)) {
            log.info("参数错误，行李类型 id 不能为空");
            throw new ParamException("参数错误，行李类型 id 不能为空");
        }

        List<PairDTO<Long, String>> calculateRulesByType = calculationRuleManager
                .queryCalculateRulesByLuggageType(luggageTypeId);
        if (CollectionUtils.isEmpty(calculateRulesByType)) {
            return Collections.emptyList();
        }

        return calculateRulesByType;
    }

    /**
     * 获取计费规则列表
     *
     * @return
     */
    @Override
    public List<ChargeCalculateRuleDTO> getChargeCalculateRuleList() {
        List<ChargeCalculationRule> calculationRules =
                calculationRuleManager.getChargeCalculateRuleList();

        return buildChargeCalculateRuleDTOs(calculationRules);
    }

    /**
     * 构造 ChargeCalculateRuleDTO 对象
     *
     * @param calculationRules
     * @return
     */
    private List<ChargeCalculateRuleDTO> buildChargeCalculateRuleDTOs(
            List<ChargeCalculationRule> calculationRules) {

        List<ChargeCalculateRuleDTO> ruleDTOS = newArrayList();
        calculationRules.forEach(calculationRule -> {
            ChargeCalculateRuleDTO calculateRuleDTO = new ChargeCalculateRuleDTO();
            Long luggageTypeId = calculationRule.getLuggageTypeId();
            Integer unitsId = calculationRule.getCalculationUnitsId();
            calculateRuleDTO.setLuggageTypeId(luggageTypeId);
            calculateRuleDTO.setCalculateRuleDesc(StringSpliceUtils
                    .getChargeCalculateRule(luggageTypeId, unitsId));
            calculateRuleDTO.setCalculationRuleId(calculationRule
                    .getCalculationRuleId());
            calculateRuleDTO.setFeePerUnit(calculationRule.getFeePerUnit());
            calculateRuleDTO.setCalculationUnitsId(unitsId);
            calculateRuleDTO.setGmtCreate(calculationRule.getGmtCreate().toString());
            ruleDTOS.add(calculateRuleDTO);
        });

        return ruleDTOS;
    }
}
