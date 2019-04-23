package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageStorageInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageStorageRecordQueryDTO;
import com.wuxiu.galaxy.api.dto.NewLuggageStorageRecordDTO;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.api.service.LuggageStorageRecordFacade;
import com.wuxiu.galaxy.service.core.biz.service.LuggageStorageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行李寄存相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/22 17:16
 */
@Service("luggageStorageRecordFacade")
public class LuggageStorageRecordFacadeImpl implements LuggageStorageRecordFacade {

    @Autowired
    private LuggageStorageRecordService storageRecordService;

    /**
     * 新增行李寄存记录
     *
     * @param storageRecordDTO
     * @param operateUserDTO
     * @return
     */
    @Override
    public APIResult<Long> insertLuggageStorageRecord(
            NewLuggageStorageRecordDTO storageRecordDTO, OperateUserDTO operateUserDTO) {
        return APIResult.ok(
                storageRecordService.insertLuggageStorageRecord(storageRecordDTO,
                        operateUserDTO));
    }

    /**
     * 查询行李寄存记录
     *
     * @param queryDTO
     * @return
     */
    @Override
    public APIResult<PageInfo<LuggageStorageInfoDTO>> queryStorageRecordList(
            LuggageStorageRecordQueryDTO queryDTO) {

        return APIResult.ok(storageRecordService.queryStorageRecordList(queryDTO));
    }

    /**
     * 行李取件
     *
     * @param luggageId
     * @return
     */
    @Override
    public APIResult<Void> pickupLuggage(Long luggageId) {
        storageRecordService.pickupLuggage(luggageId);
        return APIResult.ok();
    }
}
