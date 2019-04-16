package com.wuxiu.galaxy.service.core.biz.service.impl;

import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.domain.LuggageType;
import com.wuxiu.galaxy.dal.manager.LuggageTypeManager;
import com.wuxiu.galaxy.service.core.biz.service.LuggageTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 行李类型相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 09:30
 */
@Slf4j
@Service
public class LuggageTypeServiceImpl implements LuggageTypeService {

    @Autowired
    private LuggageTypeManager luggageTypeManager;

    /**
     * 获取行李类型列表
     *
     * @return
     */
    @Override
    public List<PairDTO<Long, String>> getLuggageTypeList() {
        List<PairDTO<Long, String>> luggageTypesPairDTO =
                luggageTypeManager.getLuggageTypeList();

        if (CollectionUtils.isEmpty(luggageTypesPairDTO)) {
            return Collections.emptyList();
        }

        return luggageTypesPairDTO;
    }

    /**
     * 根据行李类型id获取行李类型
     *
     * @param luggageTypeId
     * @return ChargeType
     */
    @Override
    public LuggageType getLuggageTypeById(Long luggageTypeId) {
        return luggageTypeManager.selectById(luggageTypeId);
    }
}
