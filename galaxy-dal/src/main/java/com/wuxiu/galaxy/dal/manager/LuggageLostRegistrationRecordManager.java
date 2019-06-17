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
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageLostRegisterRecordQueryDTO;
import com.wuxiu.galaxy.dal.domain.LuggageLostRegistrationRecord;

import java.util.List;

/**
 *   
 * LuggageLostRegistrationRecordManager
 *  * 行李遗失登记记录表
 *  
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *
 * @since 2019-04-22
 *  
 */
public interface LuggageLostRegistrationRecordManager extends BaseManager<LuggageLostRegistrationRecord> {

    /**
     * 查询行李遗失登记记录列表
     *
     * @param recordQueryDTO
     * @return
     */
    Page<LuggageLostRegisterRecordDTO> queryLostRegisterRecordList(
            LuggageLostRegisterRecordQueryDTO recordQueryDTO);

    /**
     * 获取所有行李遗失登记记录
     *
     * @return
     */
    List<LuggageLostRegistrationRecord> getAllRecords();
}
