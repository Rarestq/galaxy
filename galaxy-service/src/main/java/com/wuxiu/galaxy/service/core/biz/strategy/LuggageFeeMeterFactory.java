package com.wuxiu.galaxy.service.core.biz.strategy;

import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import com.wuxiu.galaxy.api.dto.LuggageFeeBaseCalculationParamDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageFeeCalculationRuleDTO;
import com.wuxiu.galaxy.dal.domain.FixedChargeCalculationDetail;
import com.wuxiu.galaxy.dal.manager.FixedChargeCalculationDetailManager;
import com.wuxiu.galaxy.service.core.biz.strategy.impl.CommonLuggageFeeCalculateStrategy;
import com.wuxiu.galaxy.service.core.biz.strategy.impl.FragileLuggageFeeCalculateStrategy;
import com.wuxiu.galaxy.service.core.biz.strategy.impl.ValuableLuggageFeeCalculateStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 行李寄存计价器工厂类
 *
 * @author: wuxiu
 * @date: 2019/4/22 10:46
 */
@Slf4j
@Component
public class LuggageFeeMeterFactory {

    @Autowired
    private FixedChargeCalculationDetailManager fixedChargeCalculationDetailManager;

    /**
     * 计价器的缓存
     */
    private static final Map<String, LuggageFeeMeter> METER_CACHE =
            new ConcurrentHashMap<>();

    /**
     * 获取计价器实例
     *
     * @param feeCalculationRuleDTO 计费规则对象
     * @return
     */
    public LuggageFeeMeter getLuggageFeeMeter(
            LuggageFeeCalculationRuleDTO feeCalculationRuleDTO) {
        return getLuggageFeeMeter(feeCalculationRuleDTO.getLuggageTypeId(),
                feeCalculationRuleDTO.getLuggageStorageDays(),
                feeCalculationRuleDTO.getGmtModified()
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    public LuggageFeeMeter getLuggageFeeMeter(
            Long luggageTypeId, Integer calculateHours, Long lastUpdateTime) {
        String cacheKey = luggageTypeId + "_" + calculateHours + "_" + lastUpdateTime;
        LuggageFeeMeter luggageFeeMeter = METER_CACHE.get(cacheKey);
        if (Objects.isNull(luggageFeeMeter)) {
            // 缓存不存在，创建
            LuggageTypeEnum luggageTypeEnum =
                    LuggageTypeEnum.valueOf(luggageTypeId);
            if (luggageTypeEnum == null) {
                log.error("Unknown luggageTypeId={}", luggageTypeId);
                return null;
            }

            List<LuggageFeeBaseCalculationParamDTO> calculationParamDTO = null;
            LuggageFeeCalculationStrategy strategy = null;
            switch (luggageTypeEnum) {
                case COMMON_LUGGAGE_TYPE:
                    calculationParamDTO =
                            buildLuggageFeeCalculateParamDTOsByLuggageType(luggageTypeId);
                    strategy = new CommonLuggageFeeCalculateStrategy();
                    break;
                case FRAGILE_LUGGAGE_TYPE:
                    calculationParamDTO =
                            buildLuggageFeeCalculateParamDTOsByLuggageType(luggageTypeId);
                    strategy = new FragileLuggageFeeCalculateStrategy();
                    break;
                case VALUABLE_LUGGAGE_TYPE:
                    calculationParamDTO =
                            buildLuggageFeeCalculateParamDTOsByLuggageType(luggageTypeId);
                    strategy = new ValuableLuggageFeeCalculateStrategy();
                    break;
                default:
                    log.error("Unsupported luggageTypeId={}", luggageTypeId);
                    break;
            }

            if (Objects.isNull(strategy) || Objects.isNull(calculationParamDTO)) {
                return null;
            }

            luggageFeeMeter = new LuggageFeeMeter(strategy, calculationParamDTO);
            METER_CACHE.put(cacheKey, luggageFeeMeter);
            log.info("METER_CACHE is created. cache_key={}", cacheKey);
        }

        return luggageFeeMeter;
    }

    /**
     * 根据行李类型(1-普通物件,2-易碎物件,3-贵重物件)构造 LuggageFeeBaseCalculationParamDTO 对象
     *
     * @param luggageTypeId
     * @return
     */
    private List<LuggageFeeBaseCalculationParamDTO>
    buildLuggageFeeCalculateParamDTOsByLuggageType(Long luggageTypeId) {

        // 根据行李类型查询其对应的计费细节
        List<FixedChargeCalculationDetail> calculationDetails = fixedChargeCalculationDetailManager
                .getCalculationDetailByLuggageTypeId(luggageTypeId);

        // 构造 LuggageFeeBaseCalculationParamDTO 对象
        return buildLuggageFeeBaseCalculationParamDTO(calculationDetails);
    }

    /**
     * 构造 LuggageFeeBaseCalculationParamDTO 对象
     *
     * @param calculationDetails
     * @return
     */
    private List<LuggageFeeBaseCalculationParamDTO>
    buildLuggageFeeBaseCalculationParamDTO(
            List<FixedChargeCalculationDetail> calculationDetails) {
        if (CollectionUtils.isEmpty(calculationDetails)) {
            return Collections.emptyList();
        }

        List<LuggageFeeBaseCalculationParamDTO> resultList = newArrayList();
        for (FixedChargeCalculationDetail fixedChargeCalculationDetail
                : calculationDetails) {
            LuggageFeeBaseCalculationParamDTO fixedCalculationParamDTO =
                    new LuggageFeeBaseCalculationParamDTO();
            fixedCalculationParamDTO.setCalculationUnitsId(
                    fixedChargeCalculationDetail.getCalculationUnitsId());
            fixedCalculationParamDTO.setFeePerUnit(
                    fixedChargeCalculationDetail.getFeePerUnit());

            resultList.add(fixedCalculationParamDTO);
        }

        return resultList;
    }
}
