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

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordInfoDTO;
import com.wuxiu.galaxy.dal.common.dto.LostCompensateRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageLostCompensateDTO;
import com.wuxiu.galaxy.dal.domain.LuggageLostCompensationRecord;
import com.wuxiu.galaxy.api.common.base.BaseManager;

/**
 *   
 * LuggageLostCompensationRecordManager
 *  * 行李遗失赔偿记录表
 *  
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *
 * @since 2019-04-22
 *  
 */
public interface LuggageLostCompensationRecordManager extends BaseManager<LuggageLostCompensationRecord> {

    /**
     * 查询行李遗失赔偿登记记录
     *
     * @param recordQueryDTO
     * @return
     */
    Page<LostCompensateRecordInfoDTO> queryLostCompensateRecordList(
            LostCompensateRecordQueryDTO recordQueryDTO);

    /**
     * 遗失行李赔偿
     *
     * @param lostCompensateDTO
     * @return
     */
    Long compensateByLuggageType(LuggageLostCompensateDTO lostCompensateDTO);
}
