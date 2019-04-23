package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordQueryDTO;

/**
 * 行李逾期未取清理相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 21:38
 */
public interface LuggageOverdueRecordFacade {

    /**
     * 查询行李逾期未取清理记录列表
     *
     * @param queryDTO
     * @return
     */
    APIResult<PageInfo<LuggageOverdueRecordInfoDTO>> queryOverdueRecordList(
            LuggageOverdueRecordQueryDTO queryDTO);
}
