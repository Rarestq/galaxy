package com.wuxiu.galaxy.service.core.biz.service.apiservice;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordQueryDTO;

/**
 * 行李取件相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/26 09:36
 */
public interface PickupLuggageService {

    /**
     * 行李取件
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    void pickupLuggage(Long luggageId,
                       AdminInfoDTO operateUserDTO);

    /**
     * 标记为遗失
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    void pickupOverdueLuggage(Long luggageId,
                              AdminInfoDTO operateUserDTO);

    /**
     * 逾期取件
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    void markLuggageAsLost(Long luggageId,
                           AdminInfoDTO operateUserDTO);


    /**
     * 查询行李取件记录列表
     *
     * @param queryDTO
     * @return
     */
    PageInfo<PickupLuggageRecordDTO> queryPickupLuggageRecordList(
            PickupLuggageRecordQueryDTO queryDTO);
}
