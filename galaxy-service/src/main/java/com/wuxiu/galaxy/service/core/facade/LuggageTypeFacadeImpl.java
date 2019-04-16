package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.api.service.LuggageTypeFacade;
import com.wuxiu.galaxy.service.core.biz.service.LuggageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行李类型相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 09:28
 */
@Service("luggageTypeFacade")
public class LuggageTypeFacadeImpl implements LuggageTypeFacade {

    @Autowired
    private LuggageTypeService luggageTypeService;

    /**
     * 获取行李类型列表
     *
     * @return
     */
    @Override
    public APIResult<List<PairDTO<Long, String>>> getLuggageTypeList() {
        return APIResult.ok(luggageTypeService.getLuggageTypeList());
    }
}
