package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.wuxiu.galaxy.api.dto.LuggageTypeDTO;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.domain.LuggageType;
import com.wuxiu.galaxy.dal.manager.LuggageTypeManager;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LuggageTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

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

    /**
     * 获取行李类型列表
     *
     * @return
     */
    @Override
    public List<LuggageTypeDTO> getLuggageTypes() {
        List<LuggageType> luggageTypes = luggageTypeManager.getLuggageTypes();

        return buildLuggageTypeDTO(luggageTypes);
    }

    /**
     * 构造 LuggageTypeDTO 对象
     *
     * @param luggageTypes
     * @return
     */
    private List<LuggageTypeDTO> buildLuggageTypeDTO(List<LuggageType> luggageTypes) {

        List<LuggageTypeDTO> luggageTypeDTOS = newArrayList();
        luggageTypes.forEach(luggageType -> {
            LuggageTypeDTO luggageTypeDTO = new LuggageTypeDTO();
            luggageTypeDTO.setLuggageTypeId(luggageType.getLuggageTypeId());
            luggageTypeDTO.setLuggageType(luggageType.getLuggageType());
            luggageTypeDTO.setGmtCreate(luggageType.getGmtCreate().toString());
            luggageTypeDTO.setGmtModified(luggageType.getGmtModified().toString());
            luggageTypeDTOS.add(luggageTypeDTO);
        });

        return luggageTypeDTOS;
    }
}
