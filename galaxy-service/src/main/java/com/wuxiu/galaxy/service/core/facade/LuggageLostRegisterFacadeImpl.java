package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordDTO;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordQueryDTO;
import com.wuxiu.galaxy.api.service.LuggageLostRegisterFacade;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LuggageLostRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行李遗失登记相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/27 16:36
 */
@Service("luggageLostRegisterFacade")
public class LuggageLostRegisterFacadeImpl implements LuggageLostRegisterFacade {

    @Autowired
    private LuggageLostRegisterService luggageLostRegisterService;

    /**
     * 查询行李遗失登记记录列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public APIResult<PageInfo<LuggageLostRegisterRecordDTO>> queryLostRegisterRecordList(
            LuggageLostRegisterRecordQueryDTO queryDTO) {
        return APIResult.ok(luggageLostRegisterService
                .queryLostRegisterRecordList(queryDTO));
    }
}
