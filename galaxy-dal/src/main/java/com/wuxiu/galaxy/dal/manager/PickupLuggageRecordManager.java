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
import com.wuxiu.galaxy.api.common.base.BaseManager;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordDTO;
import com.wuxiu.galaxy.dal.common.dto.CommonPickupLuggageDTO;
import com.wuxiu.galaxy.dal.common.dto.MarkLuggageAsLostDTO;
import com.wuxiu.galaxy.dal.common.dto.PickupLuggageRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.PickupOverdueLuggageDTO;
import com.wuxiu.galaxy.dal.domain.PickupLuggageRecord;

/**
 *   
 * PickupLuggageRecordManager
 *  * 行李取件记录表
 *  
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *
 * @since 2019-04-22
 *  
 */
public interface PickupLuggageRecordManager extends BaseManager<PickupLuggageRecord> {


    /**
     * 行李取件
     *
     * @param commonPickupLuggageDTO
     * @return
     */
    void pickupLuggage(CommonPickupLuggageDTO commonPickupLuggageDTO);

    /**
     * 逾期取件
     *
     * @param pickupOverdueLuggageDTO
     * @return
     */
    void pickupOverdueLuggage(PickupOverdueLuggageDTO pickupOverdueLuggageDTO);

    /**
     * 标记为遗失
     *
     * @param markLuggageAsLostDTO
     * @return
     */
    void markLuggageAsLost(MarkLuggageAsLostDTO markLuggageAsLostDTO);

    /**
     * 查询行李取件记录列表信息
     *
     * @param recordQueryDTO
     * @return
     */
    Page<PickupLuggageRecordDTO> queryStorageRecordList(
            PickupLuggageRecordQueryDTO recordQueryDTO);
}
