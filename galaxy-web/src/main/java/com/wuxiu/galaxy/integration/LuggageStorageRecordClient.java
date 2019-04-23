package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageStorageInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageStorageRecordQueryDTO;
import com.wuxiu.galaxy.api.dto.NewLuggageStorageRecordDTO;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.api.service.LuggageStorageRecordFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行李寄存相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 17:03
 */
@Service
public class LuggageStorageRecordClient {

    @Autowired
    private LuggageStorageRecordFacade storageRecordFacade;

    /**
     * 新增行李寄存记录
     *
     * @param storageRecordDTO
     * @param operateUserDTO
     * @return
     */
    public APIResult<Long> insertLuggageStorageRecord(
            NewLuggageStorageRecordDTO storageRecordDTO, OperateUserDTO operateUserDTO) {
        return storageRecordFacade.insertLuggageStorageRecord(storageRecordDTO,
                operateUserDTO);
    }

    /**
     * 查询行李寄存记录
     *
     * @param queryDTO
     * @return
     */
    public APIResult<PageInfo<LuggageStorageInfoDTO>> queryStorageRecordList(
            LuggageStorageRecordQueryDTO queryDTO) {

        return storageRecordFacade.queryStorageRecordList(queryDTO);
    }

    /**
     * 行李取件
     *
     * @param luggageId
     * @return
     */
    public APIResult<Void> pickupLuggage(Long luggageId) {
        return storageRecordFacade.pickupLuggage(luggageId);
    }
}
