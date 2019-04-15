package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.PairDTO;

import java.util.List;

/**
 * 费用类型服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 22:42
 */
public interface ChargeTypeFacade {

    /**
     * 获取费用类型列表
     *
     * @return
     */
    APIResult<List<PairDTO<Long, String>>> getChargeTypeList();
}
