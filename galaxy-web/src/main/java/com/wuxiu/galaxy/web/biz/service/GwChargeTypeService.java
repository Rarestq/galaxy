package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.web.biz.vo.Pair;

import java.util.List;

/**
 * 费用类型相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 22:37
 */
public interface GwChargeTypeService {

    /**
     * 获取费用类型列表
     *
     * @return
     */
    APIResult<List<Pair<Long, String>>> getChargeTypeList();

}
