package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordDTO;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordQueryDTO;
import com.wuxiu.galaxy.api.service.LuggageLostRegisterFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行李遗失登记相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/27 16:34
 */
@Service
public class LuggageLostRegisterClient {

    @Autowired
    private LuggageLostRegisterFacade lostRegisterFacade;

    /**
     * 查询行李遗失登记记录列表
     *
     * @param queryDTO
     * @return
     */
    public APIResult<PageInfo<LuggageLostRegisterRecordDTO>> queryLostRegisterRecordList(
            LuggageLostRegisterRecordQueryDTO queryDTO) {
        return lostRegisterFacade.queryLostRegisterRecordList(queryDTO);
    }
}
