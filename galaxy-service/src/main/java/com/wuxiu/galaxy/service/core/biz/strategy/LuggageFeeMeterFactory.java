package com.wuxiu.galaxy.service.core.biz.strategy;

import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import com.wuxiu.galaxy.api.dto.CommonLuggageFeeCalculateParamDTO;
import com.wuxiu.galaxy.api.dto.FragileLuggageFeeCalculateParamDTO;
import com.wuxiu.galaxy.api.dto.LuggageFeeBaseCalculationParamDTO;
import com.wuxiu.galaxy.api.dto.ValuableLuggageFeeCalculateParamDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageFeeCalculationRuleDTO;
import com.wuxiu.galaxy.dal.domain.CommonCalculateRuleDetail;
import com.wuxiu.galaxy.dal.domain.FragileCalculateRuleDetail;
import com.wuxiu.galaxy.dal.domain.ValuableCalculateRuleDetail;
import com.wuxiu.galaxy.dal.manager.CommonCalculateRuleDetailManager;
import com.wuxiu.galaxy.dal.manager.FragileCalculateRuleDetailManager;
import com.wuxiu.galaxy.dal.manager.ValuableCalculateRuleDetailManager;
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
    private CommonCalculateRuleDetailManager commonCalculateRuleDetailManager;

    @Autowired
    private FragileCalculateRuleDetailManager fragileCalculateRuleDetailManager;

    @Autowired
    private ValuableCalculateRuleDetailManager valuableCalculateRuleDetailManager;

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
        return getLuggageFeeMeter(feeCalculationRuleDTO.getCalculateRuleId(),
                feeCalculationRuleDTO.getLuggageTypeId(),
                feeCalculationRuleDTO.getGmtModified()
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    public LuggageFeeMeter getLuggageFeeMeter(
            Long calculateRuleId, Long luggageTypeId, Long lastUpdateTime) {
        String cacheKey = calculateRuleId + "_" + luggageTypeId + "_" + lastUpdateTime;
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
            // 根据不同的行李类型，创建不同的计价参数对象
            switch (luggageTypeEnum) {
                case COMMON_LUGGAGE_TYPE:
                    calculationParamDTO =
                            createCommonLuggageFeeCalculateParamDTOs(calculateRuleId);
                    strategy = new CommonLuggageFeeCalculateStrategy();
                    break;
                case FRAGILE_LUGGAGE_TYPE:
                    calculationParamDTO =
                            createFragileLuggageFeeCalculateParamDTOs(calculateRuleId);
                    strategy = new FragileLuggageFeeCalculateStrategy();
                    break;
                case VALUABLE_LUGGAGE_TYPE:
                    calculationParamDTO =
                            createValuableCommonLuggageFeeCalculateParamDTOs(
                                    calculateRuleId);
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
     * 构造 ValuableLuggageFeeCalculateParamDTO(3-贵重物件) 对象
     *
     * @param calculateRuleId
     * @return
     */
    private List<LuggageFeeBaseCalculationParamDTO>
    createValuableCommonLuggageFeeCalculateParamDTOs(Long calculateRuleId) {
        List<ValuableCalculateRuleDetail> valuableCalculateRuleDetails =
                valuableCalculateRuleDetailManager
                        .getValuableCalculateRuleDetails(calculateRuleId);

        return buildLuggageFeeBaseCalculationParamDTOsByValuable(
                valuableCalculateRuleDetails);
    }

    private List<LuggageFeeBaseCalculationParamDTO> buildLuggageFeeBaseCalculationParamDTOsByValuable(List<ValuableCalculateRuleDetail> valuableCalculateRuleDetails) {

        if (CollectionUtils.isEmpty(valuableCalculateRuleDetails)) {
            return Collections.emptyList();
        }

        List<LuggageFeeBaseCalculationParamDTO> resultList = newArrayList();
        for (ValuableCalculateRuleDetail fragileCalculateRuleDetail
                : valuableCalculateRuleDetails) {
            ValuableLuggageFeeCalculateParamDTO valuableLuggageFeeCalculateParamDTO =
                    new ValuableLuggageFeeCalculateParamDTO();
            valuableLuggageFeeCalculateParamDTO.setCalculationUnitsId(
                    fragileCalculateRuleDetail.getCalculationUnitsId());
            valuableLuggageFeeCalculateParamDTO.setFeePerUnit(
                    fragileCalculateRuleDetail.getFeePerUnit());

            resultList.add(valuableLuggageFeeCalculateParamDTO);
        }

        return resultList;
    }

    /**
     * 构造 FragileLuggageFeeCalculateParamDTO(2-易碎物件) 对象
     *
     * @param calculateRuleId
     * @return
     */
    private List<LuggageFeeBaseCalculationParamDTO>
    createFragileLuggageFeeCalculateParamDTOs(Long calculateRuleId) {
        List<FragileCalculateRuleDetail> fragileCalculateRuleDetails =
                fragileCalculateRuleDetailManager
                        .getFragileCalculateRuleDetails(calculateRuleId);

        return buildLuggageFeeBaseCalculationParamDTOsByFragile(
                fragileCalculateRuleDetails);
    }

    private List<LuggageFeeBaseCalculationParamDTO> buildLuggageFeeBaseCalculationParamDTOsByFragile(List<FragileCalculateRuleDetail> fragileCalculateRuleDetails) {

        if (CollectionUtils.isEmpty(fragileCalculateRuleDetails)) {
            return Collections.emptyList();
        }

        List<LuggageFeeBaseCalculationParamDTO> resultList = newArrayList();
        for (FragileCalculateRuleDetail fragileCalculateRuleDetail
                : fragileCalculateRuleDetails) {
            FragileLuggageFeeCalculateParamDTO fragileLuggageFeeCalculateParamDTO =
                    new FragileLuggageFeeCalculateParamDTO();
            fragileLuggageFeeCalculateParamDTO.setCalculationUnitsId(
                    fragileCalculateRuleDetail.getCalculationUnitsId());
            fragileLuggageFeeCalculateParamDTO.setFeePerUnit(
                    fragileCalculateRuleDetail.getFeePerUnit());

            resultList.add(fragileLuggageFeeCalculateParamDTO);
        }

        return resultList;
    }

    /**
     * 构造 CommonLuggageFeeCalculateParamDTO(1-普通物件) 对象
     *
     * @param calculateRuleId
     * @return
     */
    private List<LuggageFeeBaseCalculationParamDTO>
    createCommonLuggageFeeCalculateParamDTOs(Long calculateRuleId) {

        // 根据行李类型查询其对应的计费细节
        List<CommonCalculateRuleDetail> calculationDetails =
                commonCalculateRuleDetailManager
                        .getCommonCalculateRuleDetails(calculateRuleId);

        // 构造 CommonLuggageFeeCalculateParamDTO 对象
        return buildLuggageFeeBaseCalculationParamDTOsByCommon(calculationDetails);
    }

    /**
     * 构造 LuggageFeeBaseCalculationParamDTO 对象
     *
     * @param commonCalculateRuleDetails
     * @return
     */
    private List<LuggageFeeBaseCalculationParamDTO>
    buildLuggageFeeBaseCalculationParamDTOsByCommon(
            List<CommonCalculateRuleDetail> commonCalculateRuleDetails) {
        if (CollectionUtils.isEmpty(commonCalculateRuleDetails)) {
            return Collections.emptyList();
        }

        List<LuggageFeeBaseCalculationParamDTO> resultList = newArrayList();
        for (CommonCalculateRuleDetail commonCalculateRuleDetail
                : commonCalculateRuleDetails) {
            CommonLuggageFeeCalculateParamDTO commonLuggageFeeCalculateParamDTO =
                    new CommonLuggageFeeCalculateParamDTO();
            commonLuggageFeeCalculateParamDTO.setCalculationUnitsId(
                    commonCalculateRuleDetail.getCalculationUnitsId());
            commonLuggageFeeCalculateParamDTO.setFeePerUnit(
                    commonCalculateRuleDetail.getFeePerUnit());

            resultList.add(commonLuggageFeeCalculateParamDTO);
        }

        return resultList;
    }
}
