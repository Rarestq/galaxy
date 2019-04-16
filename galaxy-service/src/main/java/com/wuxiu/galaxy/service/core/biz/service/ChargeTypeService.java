package com.wuxiu.galaxy.service.core.biz.service;

import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.domain.ChargeType;

import java.util.List;

/**
 * 费用类型服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 22:51
 */
public interface ChargeTypeService {

    /**
     * 获取费用类型列表
     *
     * @return
     */
    List<PairDTO<Long, String>> getChargeTypeList();

    /**
     * 根据费用类型id获取费用类型
     *
     * @param chargeTypeId
     * @return ChargeType
     */
    ChargeType getChargeTypeById(Long chargeTypeId);
}
