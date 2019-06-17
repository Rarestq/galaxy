package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordInfoDTO;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordQueryDTO;
import com.wuxiu.galaxy.api.service.LuggageLostCompensateFacade;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LuggageLostCompensateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行李遗失赔偿相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/28 10:43
 */
@Service("luggageLostCompensateFacade")
public class LuggageLostCompensateFacadeImpl implements LuggageLostCompensateFacade {

    @Autowired
    private LuggageLostCompensateService luggageLostCompensateService;

    /**
     * 查询行李遗失赔偿登记记录列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public APIResult<PageInfo<LostCompensateRecordInfoDTO>> queryLostCompensateRecordList(
            LostCompensateRecordQueryDTO queryDTO) {
        return APIResult.ok(luggageLostCompensateService
                .queryLostCompensateRecordList(queryDTO));
    }

    /**
     * 遗失行李赔偿
     *
     * @param lostRegistrationRecordId
     * @param operateUser
     * @return
     */
    @Override
    public APIResult<Long> compensateByLuggageType(Long lostRegistrationRecordId,
                                                   AdminInfoDTO operateUser) {
        return APIResult.ok(luggageLostCompensateService.compensateByLuggageType(
                lostRegistrationRecordId, operateUser));
    }
}
