package com.wuxiu.galaxy.service.core.biz.service.apiservice;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordDTO;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordQueryDTO;

/**
 * 行李遗失登记相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/27 16:38
 */
public interface LuggageLostRegisterService {

    /**
     * 查询行李遗失登记记录列表
     *
     * @param queryDTO
     * @return
     */
    PageInfo<LuggageLostRegisterRecordDTO> queryLostRegisterRecordList(
            LuggageLostRegisterRecordQueryDTO queryDTO);
}
