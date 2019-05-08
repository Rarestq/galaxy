package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordQueryDTO;
import com.wuxiu.galaxy.api.service.PickupLuggageFacade;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.PickupLuggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行李取件相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/26 09:35
 */
@Service("pickupLuggageFacade")
public class PickupLuggageFacadeImpl implements PickupLuggageFacade {

    @Autowired
    private PickupLuggageService pickupLuggageService;

    /**
     * 行李取件
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    @Override
    public APIResult<Void> pickupLuggage(Long luggageId,
                                         OperateUserDTO operateUserDTO) {
        pickupLuggageService.pickupLuggage(luggageId, operateUserDTO);
        return APIResult.ok();
    }

    /**
     * 标记为遗失
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    @Override
    public APIResult<Void> markLuggageAsLost(Long luggageId,
                                             OperateUserDTO operateUserDTO) {
        pickupLuggageService.markLuggageAsLost(luggageId, operateUserDTO);
        return APIResult.ok();
    }

    /**
     * 逾期取件
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    @Override
    public APIResult<Void> pickupOverdueLuggage(Long luggageId,
                                                OperateUserDTO operateUserDTO) {
        pickupLuggageService.pickupOverdueLuggage(luggageId, operateUserDTO);
        return APIResult.ok();
    }

    /**
     * 查询行李取件记录列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public APIResult<PageInfo<PickupLuggageRecordDTO>> queryPickupLuggageRecordList(
            PickupLuggageRecordQueryDTO queryDTO) {
        return APIResult.ok(pickupLuggageService.queryPickupLuggageRecordList(queryDTO));
    }
}
