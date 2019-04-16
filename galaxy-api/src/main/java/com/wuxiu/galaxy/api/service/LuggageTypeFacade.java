package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.PairDTO;

import java.util.List;

/**
 * 行李类型相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 09:27
 */
public interface LuggageTypeFacade {

    /**
     * 获取行李类型列表
     *
     * @return
     */
    APIResult<List<PairDTO<Long, String>>> getLuggageTypeList();
}
