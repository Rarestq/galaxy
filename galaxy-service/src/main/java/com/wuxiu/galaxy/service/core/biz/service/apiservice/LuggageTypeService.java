package com.wuxiu.galaxy.service.core.biz.service.apiservice;

import com.wuxiu.galaxy.api.dto.LuggageTypeDTO;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.domain.LuggageType;

import java.util.List;

/**
 * 行李类型相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 09:28
 */
public interface LuggageTypeService {

    /**
     * 获取行李类型列表
     *
     * @return
     */
    List<PairDTO<Long, String>> getLuggageTypeList();

    /**
     * 根据行李类型id获取行李类型
     *
     * @param luggageTypeId
     * @return ChargeType
     */
    LuggageType getLuggageTypeById(Long luggageTypeId);

    /**
     * 获取行李类型列表
     *
     * @return
     */
    List<LuggageTypeDTO> getLuggageTypes();
}
