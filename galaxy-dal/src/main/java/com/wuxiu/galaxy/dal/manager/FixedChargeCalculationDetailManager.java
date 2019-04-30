/**
 *  
 *  * All rights Reserved, Designed By wuxiu
 * <p>
 *  * @Package com.wuxiu.galaxy.dal.dao
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @date: 2018-04-16 20:35:12
 *  * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 *  
 */
package com.wuxiu.galaxy.dal.manager;

import com.wuxiu.galaxy.api.common.base.BaseManager;
import com.wuxiu.galaxy.dal.domain.FixedChargeCalculationDetail;

import java.util.List;

/**
 *   
 * FixedChargeCalculationDetailManager
 *  * 固定计费表
 *  
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *
 * @since 2019-04-22
 *  
 */
public interface FixedChargeCalculationDetailManager extends BaseManager<FixedChargeCalculationDetail> {

    /**
     * 根据行李类型查询计费细节信息
     *
     * @param luggageTypeId
     * @return
     */
    List<FixedChargeCalculationDetail> getCalculationDetailByLuggageTypeId(
            Long luggageTypeId);

}
