package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.web.biz.vo.LuggageTypeVO;
import com.wuxiu.galaxy.web.biz.vo.Pair;

import java.util.List;

/**
 * 行李类型相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 09:21
 */
public interface GwLuggageTypeService {

    /**
     * 获取行李类型列表（key-value）
     *
     * @return
     */
    APIResult<List<Pair<Long, String>>> getLuggageTypeList();

    /**
     * 获取行李类型列表
     *
     * @return
     */
    APIResult<List<LuggageTypeVO>> getLuggageTypes();
}
