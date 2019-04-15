/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.controller.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager;

import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.dal.domain.ChargeType;
import com.wuxiu.galaxy.api.common.base.BaseManager;

import java.util.List;

/**  
 * ChargeTypeManager
 * 费用类型表
 * 
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-15
 */
public interface ChargeTypeManager extends BaseManager<ChargeType> {

    /**
     * 获取费用类型列表
     *
     * @return
     */
    List<PairDTO<Long, String>> getChargeTypeList();
}
