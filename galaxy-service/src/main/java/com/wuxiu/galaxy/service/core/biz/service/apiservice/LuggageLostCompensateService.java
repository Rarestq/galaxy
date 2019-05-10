package com.wuxiu.galaxy.service.core.biz.service.apiservice;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordInfoDTO;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordQueryDTO;

/**
 * 行李遗失赔偿相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/28 10:44
 */
public interface LuggageLostCompensateService {

    /**
     * 查询行李遗失赔偿登记记录列表
     *
     * @param queryDTO
     * @return
     */
    PageInfo<LostCompensateRecordInfoDTO> queryLostCompensateRecordList(
            LostCompensateRecordQueryDTO queryDTO);

    /**
     * 遗失行李赔偿
     *
     * @param lostRegistrationRecordId
     * @param operateUser
     * @return
     */
    Long compensateByLuggageType(Long lostRegistrationRecordId,
                                 AdminInfoDTO operateUser);
}
