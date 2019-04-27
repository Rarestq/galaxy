package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordDTO;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordQueryDTO;

/**
 * 行李遗失登记相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/27 16:35
 */
public interface LuggageLostRegisterFacade {

    /**
     * 查询行李遗失登记记录列表
     *
     * @param queryDTO
     * @return
     */
    APIResult<PageInfo<LuggageLostRegisterRecordDTO>> queryLostRegisterRecordList(
            LuggageLostRegisterRecordQueryDTO queryDTO);
}
