package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordQueryDTO;
import com.wuxiu.galaxy.api.service.LuggageOverdueRecordFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行李逾期未取清理相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 21:32
 */
@Service
public class LuggageOverdueRecordClient {

    @Autowired
    private LuggageOverdueRecordFacade overdueRecordFacade;

    /**
     * 查询行李逾期未取清理记录列表
     *
     * @param queryDTO
     * @return
     */
    public APIResult<PageInfo<LuggageOverdueRecordInfoDTO>> queryOverdueRecordList(
            LuggageOverdueRecordQueryDTO queryDTO) {
        return overdueRecordFacade.queryOverdueRecordList(queryDTO);
    }
}
