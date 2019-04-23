/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager;

import com.wuxiu.galaxy.api.common.base.BaseManager;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.domain.LuggageType;

import java.util.List;

/**  
 * LuggageTypeManager
 * 行李类型表
 * 
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-16
 */
public interface LuggageTypeManager extends BaseManager<LuggageType> {

    /**
     * 获取行李类型列表
     *
     * @return key-value 类型
     */
    List<PairDTO<Long, String>> getLuggageTypeList();

    /**
     * 根据行李类型id获取行李类型信息
     *
     * @param luggageTypeId
     * @return
     */
    LuggageType getLuggageTypeById(Long luggageTypeId);
}
