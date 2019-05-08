package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.enums.CalculationUnitsEnum;
import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import com.wuxiu.galaxy.api.dto.ChargeCalculateRuleDTO;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.integration.ChargeCalculationRuleClient;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.service.core.base.utils.StreamUtil;
import com.wuxiu.galaxy.web.biz.service.GwChargeCalculateRuleService;
import com.wuxiu.galaxy.web.biz.vo.ChargeCalculateRuleVO;
import com.wuxiu.galaxy.web.biz.vo.Pair;
import com.wuxiu.galaxy.web.utils.ObjectConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据行李类型联动计费规则
     *
     * @param luggageTypeId
     * @return
     */
    @Override
    public APIResult<List<Pair<Long, String>>> queryCalculateRulesByLuggageType(
            Long luggageTypeId) {

        APIResult<List<PairDTO<Long, String>>> rulesByLuggageTypeAPIResult =
                calculationRuleClient.queryCalculateRulesByLuggageType(luggageTypeId);
        if (!rulesByLuggageTypeAPIResult.isSuccess()) {
            log.warn("根据行李类型联动计费规则失败, result:{}, luggageTypeId:{}",
                    rulesByLuggageTypeAPIResult, luggageTypeId);
            return CommonUtil.errorAPIResult(rulesByLuggageTypeAPIResult);
        }

        // 根据行李类型获取计费规则
        List<PairDTO<Long, String>> rulesByLuggageTypeData =
                rulesByLuggageTypeAPIResult.getData();

        // 将 PairDTO 转化为 Pair
        List<Pair<Long, String>> rulesByLuggageType = ObjectConvertUtil
                .convertDTO2Domain(rulesByLuggageTypeData);

        return APIResult.ok(rulesByLuggageType);
    }

    /**
     * 获取计费规则列表
     *
     * @return
     */
    @Override
    public APIResult<List<ChargeCalculateRuleVO>> getChargeCalculateRuleList() {
        APIResult<List<ChargeCalculateRuleDTO>> calculateRulesAPIResult =
                calculationRuleClient.getChargeCalculateRuleList();
        if (!calculateRulesAPIResult.isSuccess()) {
            log.warn("获取计费规则列表失败，result:{}", calculateRulesAPIResult);
            return CommonUtil.errorAPIResult(calculateRulesAPIResult);
        }

        List<ChargeCalculateRuleDTO> ruleDTOS = calculateRulesAPIResult.getData();
        if (CollectionUtils.isEmpty(ruleDTOS)) {
            return APIResult.ok(Collections.emptyList());
        }

        Map<Long, ChargeCalculateRuleDTO> ruleDTOMap = StreamUtil.toMap(
                ruleDTOS, ChargeCalculateRuleDTO::getCalculationRuleId);

        // 封装成 ChargeCalculateRuleVO 对象返回
        List<ChargeCalculateRuleVO> calculateRuleVOS =
                StreamUtil.convertBeanCopy(ruleDTOS,
                        ChargeCalculateRuleVO.class);

        // 将 luggageType 和 calculationUnits 转化为中文类型
        calculateRuleVOS.forEach(recordVO -> {
            recordVO.setLuggageType(
                    LuggageTypeEnum.getDescByCode(ruleDTOMap.get(recordVO
                            .getCalculationRuleId()).getLuggageTypeId()));
            recordVO.setCalculationUnits(CalculationUnitsEnum
                    .getDescByCode(ruleDTOMap.get(recordVO
                            .getCalculationRuleId()).getCalculationUnitsId()));
        });


        return APIResult.ok(calculateRuleVOS);
    }
}
