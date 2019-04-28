package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordInfoDTO;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordQueryDTO;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.api.service.LuggageLostCompensateFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行李遗失赔偿相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/28 09:23
 */
@Service
public class LuggageLostCompensateClient {

    @Autowired
    private LuggageLostCompensateFacade lostCompensateFacade;

    /**
     * 查询行李遗失赔偿登记记录列表
     *
     * @param queryDTO
     * @return
     */
    public APIResult<PageInfo<LostCompensateRecordInfoDTO>> queryLostCompensateRecordList(
            LostCompensateRecordQueryDTO queryDTO) {
        return lostCompensateFacade.queryLostCompensateRecordList(queryDTO);
    }

    /**
     * 遗失行李赔偿
     *
     * @param lostRegistrationRecordId
     * @param operateUser
     * @return
     */
    public APIResult<Long> compensateByLuggageType(Long lostRegistrationRecordId,
                                                   OperateUserDTO operateUser) {
        return lostCompensateFacade.compensateByLuggageType(
                lostRegistrationRecordId, operateUser);
    }
}
