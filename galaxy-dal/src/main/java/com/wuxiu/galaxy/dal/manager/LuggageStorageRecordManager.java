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
import com.wuxiu.galaxy.api.dto.LuggageStorageInfoDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageStorageRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.NewLuggageStorageRecordDTO;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.api.common.base.BaseManager;

import java.util.List;

/**
 *   
 * LuggageStorageRecordManager
 *  * 行李寄存-取件表
 *  
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *
 * @since 2019-04-22
 *  
 */
public interface LuggageStorageRecordManager extends BaseManager<LuggageStorageRecord> {

    /**
     * 新增行李寄存记录
     *
     * @param newLuggageStorageRecordDTO
     * @return
     */
    Long insertLuggageStorageRecord(NewLuggageStorageRecordDTO newLuggageStorageRecordDTO);

    /**
     * 查询行李寄存列表信息
     *
     * @param recordQueryDTO
     * @return
     */
    Page<LuggageStorageInfoDTO> queryStorageRecordList(
            LuggageStorageRecordQueryDTO recordQueryDTO);

    /**
     * 根据行李类型id查询行李寄存信息
     *
     * @param luggageTypeIds
     * @return
     */
    List<LuggageStorageRecord> selectRecordsByLuggageTypeId(List<Long> luggageTypeIds);

    /**
     * 通过行李寄存编号查询行李寄存信息
     *
     * @param luggageRecordNo
     * @return
     */
    LuggageStorageRecord selectByLuggageRecordNo(String luggageRecordNo);
}
