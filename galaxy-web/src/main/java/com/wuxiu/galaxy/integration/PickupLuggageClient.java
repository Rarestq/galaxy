package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordQueryDTO;
import com.wuxiu.galaxy.api.service.PickupLuggageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行李取件相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/26 09:33
 */
@Service
public class PickupLuggageClient {

    @Autowired
    private PickupLuggageFacade pickupLuggageFacade;

    /**
     * 行李取件
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    public APIResult<Void> pickupLuggage(Long luggageId,
                                         OperateUserDTO operateUserDTO) {
        return pickupLuggageFacade.pickupLuggage(luggageId, operateUserDTO);
    }

    /**
     * 标记为遗失
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    public APIResult<Void> markLuggageAsLost(Long luggageId,
                                             OperateUserDTO operateUserDTO) {
        return pickupLuggageFacade.markLuggageAsLost(luggageId, operateUserDTO);
    }

    /**
     * 逾期取件
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    public APIResult<Void> pickupOverdueLuggage(Long luggageId,
                                                OperateUserDTO operateUserDTO) {
        return pickupLuggageFacade.pickupOverdueLuggage(luggageId, operateUserDTO);
    }

    /**
     * 查询行李取件记录列表
     *
     * @param queryDTO
     * @return
     */
    public APIResult<PageInfo<PickupLuggageRecordDTO>> queryPickupLuggageRecordList(
            PickupLuggageRecordQueryDTO queryDTO) {
        return pickupLuggageFacade.queryPickupLuggageRecordList(queryDTO);
    }
}
