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
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordInfoDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageOverdueRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.SaveLuggageOverdueRecordDTO;
import com.wuxiu.galaxy.dal.domain.LuggageOverdueRecord;
import com.wuxiu.galaxy.api.common.base.BaseManager;

/**
 *   
 * LuggageOverdueRecordManager
 *  * 行李逾期未取记录表
 *  
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *
 * @since 2019-04-22
 *  
 */
public interface LuggageOverdueRecordManager extends BaseManager<LuggageOverdueRecord> {

    /**
     * 创建行李逾期记录
     *
     * @param saveLuggageOverdueRecordDTO
     * @return
     */
    Long createLuggageOverdueRecord(
            SaveLuggageOverdueRecordDTO saveLuggageOverdueRecordDTO);

    /**
     * 查询行李逾期未取清理记录列表
     *
     * @param recordQueryDTO
     * @return
     */
    Page<LuggageOverdueRecordInfoDTO> queryOverdueRecordList(
            LuggageOverdueRecordQueryDTO recordQueryDTO);
}
