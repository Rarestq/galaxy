package com.wuxiu.galaxy.service.core.biz.service.apiservice;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageStorageInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageStorageRecordQueryDTO;
import com.wuxiu.galaxy.api.dto.NewLuggageStorageRecordDTO;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;

/**
 * 行李寄存相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 17:08
 */
public interface LuggageStorageRecordService {

    /**
     * 新增行李寄存记录
     *
     * @param storageRecordDTO
     * @param operateUserDTO
     * @return
     */
    Long insertLuggageStorageRecord(NewLuggageStorageRecordDTO storageRecordDTO,
                                    OperateUserDTO operateUserDTO);

    /**
     * 查询行李寄存记录
     *
     * @param queryDTO
     * @return
     */
    PageInfo<LuggageStorageInfoDTO> queryStorageRecordList(
            LuggageStorageRecordQueryDTO queryDTO);
}
