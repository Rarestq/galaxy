package com.wuxiu.galaxy.service.core.biz.strategy;

import com.wuxiu.galaxy.dal.manager.FixedChargeCalculationDetailManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 行李寄存计价器工厂类
 *
 * @author: wuxiu
 * @date: 2019/4/22 10:46
 */
@Slf4j
@Component
public class LuggageFeeTaximeterFactory {

    /**
     * 计价器的缓存
     */
    private static final Map<String, LuggageFeeTaximeter> TAXIMETER_CACHE =
            new ConcurrentHashMap<>();

    @Autowired
    private FixedChargeCalculationDetailManager fixedChargeCalculationDetailManager;


}
