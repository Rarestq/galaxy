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
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.dao.ChargeTypeDao;
import com.wuxiu.galaxy.dal.domain.ChargeType;
import com.wuxiu.galaxy.dal.manager.ChargeTypeManager;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>ChargeTypeManager</p>
 * <p>
 * 费用类型表
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-16
 */
@Component
public class ChargeTypeManagerImpl extends BaseManagerImpl<ChargeTypeDao, ChargeType> implements ChargeTypeManager{

    /**
     * 获取费用类型列表
     *
     * @return key-value 形式的列表
     */
    @Override
    public List<PairDTO<Long, String>> getChargeTypeList() {
        // 构造查询条件（因为逻辑删除字段加了 @TableLogic 注解，所以构造条件中不需要加此条件）
        Wrapper<ChargeType> wrapper = new EntityWrapper<>();

        List<ChargeType> chargeTypes = baseDao.selectList(wrapper);

        if (CollectionUtils.isEmpty(chargeTypes)) {
            return Collections.emptyList();
        }

        // 将查询出来的 chargeTypes 转化为 List<PairDTO<Long, String>> 形式
        List<PairDTO<Long, String>> pairDTOList = Lists.newArrayList();
        chargeTypes.forEach(chargeType -> {
            PairDTO<Long, String> pairDTO = new PairDTO<>();
            pairDTO.setKey(chargeType.getChargeTypeId());
            pairDTO.setValue(chargeType.getChargeTypeName());
            pairDTOList.add(pairDTO);
        });

        return pairDTOList;
    }

}
