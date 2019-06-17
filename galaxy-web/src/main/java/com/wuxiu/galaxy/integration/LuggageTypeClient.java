package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.LuggageTypeDTO;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.api.service.LuggageTypeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行李类型相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 09:23
 */
@Service
public class LuggageTypeClient {

    @Autowired
    private LuggageTypeFacade luggageTypeFacade;

    /**
     * 获取费用类型列表
     *
     * @return
     */
    public APIResult<List<PairDTO<Long, String>>> getLuggageTypeList() {
        return luggageTypeFacade.getLuggageTypeList();
    }

    /**
     * 获取行李类型列表
     *
     * @return
     */
    public APIResult<List<LuggageTypeDTO>> getLuggageTypes() {
        return luggageTypeFacade.getLuggageTypes();
    }
}
