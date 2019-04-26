package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordQueryDTO;

/**
 * 行李取件相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/26 09:35
 */
public interface PickupLuggageFacade {

    /**
     * 行李取件
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    APIResult<Void> pickupLuggage(Long luggageId,
                                  OperateUserDTO operateUserDTO);

    /**
     * 标记为遗失
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    APIResult<Void> markLuggageAsLost(Long luggageId,
                                      OperateUserDTO operateUserDTO);

    /**
     * 逾期取件
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    APIResult<Void> pickupOverdueLuggage(Long luggageId,
                                         OperateUserDTO operateUserDTO);

    /**
     * 查询行李取件记录列表
     *
     * @param queryDTO
     * @return
     */
    APIResult<PageInfo<PickupLuggageRecordDTO>> queryPickupLuggageRecordList(
            PickupLuggageRecordQueryDTO queryDTO);
}
