package com.wuxiu.galaxy.service.core.biz.service.impl;

import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.manager.ChargeTypeManager;
import com.wuxiu.galaxy.service.core.biz.service.ChargeTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 费用类型服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 22:53
 */
@Slf4j
@Service
public class ChargeTypeServiceImpl implements ChargeTypeService {

    @Autowired
    private ChargeTypeManager chargeTypeManager;

    /**
     * 获取费用类型列表
     *
     * @return
     */
    @Override
    public List<PairDTO<Long, String>> getChargeTypeList() {
        List<PairDTO<Long, String>> chargeTypesPairDTO = chargeTypeManager.getChargeTypeList();
        if (CollectionUtils.isEmpty(chargeTypesPairDTO)) {
            return Collections.emptyList();
        }

        return chargeTypesPairDTO;
    }
}
