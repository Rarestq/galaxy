package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordInfoDTO;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordQueryDTO;

/**
 * 行李遗失赔偿相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/28 10:42
 */
public interface LuggageLostCompensateFacade {

    /**
     * 查询行李遗失赔偿登记记录列表
     *
     * @param queryDTO
     * @return
     */
    APIResult<PageInfo<LostCompensateRecordInfoDTO>> queryLostCompensateRecordList(
            LostCompensateRecordQueryDTO queryDTO);

    /**
     * 遗失行李赔偿
     *
     * @param lostRegistrationRecordId
     * @param operateUser
     * @return
     */
    APIResult<Long> compensateByLuggageType(Long lostRegistrationRecordId,
                                            AdminInfoDTO operateUser);
}
