/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.dao.LuggageTypeDao;
import com.wuxiu.galaxy.dal.domain.LuggageType;
import com.wuxiu.galaxy.dal.manager.LuggageTypeManager;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>LuggageTypeManager</p>
 * <p>
 * 行李类型表
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-16
 */
@Component
public class LuggageTypeManagerImpl extends BaseManagerImpl<LuggageTypeDao, LuggageType> implements LuggageTypeManager{

    /**
     * 获取行李类型列表
     *
     * @return key-value 类型
     */
    @Override
    public List<PairDTO<Long, String>> getLuggageTypeList() {
        // 构造查询条件（因为逻辑删除字段加了 @TableLogic 注解，所以构造条件中不需要加此条件）
        Wrapper<LuggageType> wrapper = new EntityWrapper<>();

        List<LuggageType> luggageTypes = baseDao.selectList(wrapper);

        if (CollectionUtils.isEmpty(luggageTypes)) {
            return Collections.emptyList();
        }

        // 将查询出来的 luggageTypes 转化为 List<PairDTO<Long, String>> 形式
        List<PairDTO<Long, String>> pairDTOList = Lists.newArrayList();
        luggageTypes.forEach(luggageType -> {
            PairDTO<Long, String> pairDTO = new PairDTO<>();
            pairDTO.setKey(luggageType.getLuggageTypeId());
            pairDTO.setValue(LuggageTypeEnum.getDescByCode(luggageType.getLuggageTypeId()));
            pairDTOList.add(pairDTO);
        });

        return pairDTOList;
    }

    /**
     * 根据行李类型id获取行李类型信息
     *
     * @param luggageTypeId
     * @return
     */
    @Override
    public LuggageType getLuggageTypeById(Long luggageTypeId) {
        return selectById(luggageTypeId);
    }

    /**
     * 根据行李类型名称查询行李类型信息
     *
     * @param luggageTypeDesc
     * @return
     */
    @Override
    public LuggageType selectByDesc(String luggageTypeDesc) {
        Wrapper<LuggageType> wrapper = new EntityWrapper<LuggageType>()
                .eq("luggage_type", luggageTypeDesc);
        return selectOne(wrapper);
    }

    /**
     * 获取行李类型列表
     *
     * @return
     */
    @Override
    public List<LuggageType> getLuggageTypes() {
        Wrapper<LuggageType> wrapper = new EntityWrapper<>();
        wrapper.ne("deleted", 1);

        return selectList(wrapper);
    }
}
