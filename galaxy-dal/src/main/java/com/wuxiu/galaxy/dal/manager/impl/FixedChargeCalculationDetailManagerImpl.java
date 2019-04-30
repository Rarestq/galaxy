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
package com.wuxiu.galaxy.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.dal.dao.FixedChargeCalculationDetailDao;
import com.wuxiu.galaxy.dal.domain.FixedChargeCalculationDetail;
import com.wuxiu.galaxy.dal.manager.FixedChargeCalculationDetailManager;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>FixedChargeCalculationDetailManager</p>
 * <p>
 * 固定计费表
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-22
 */
@Component
public class FixedChargeCalculationDetailManagerImpl extends BaseManagerImpl<FixedChargeCalculationDetailDao, FixedChargeCalculationDetail> implements FixedChargeCalculationDetailManager {

    /**
     * 根据行李类型查询计费细节信息
     *
     * @param luggageTypeId
     * @return
     */
    @Override
    public List<FixedChargeCalculationDetail> getCalculationDetailByLuggageTypeId(
            Long luggageTypeId) {
        Wrapper<FixedChargeCalculationDetail> wrapper =
                new EntityWrapper<FixedChargeCalculationDetail>()
                        .eq("luggage_type_id", luggageTypeId);

        return selectList(wrapper);
    }
}
