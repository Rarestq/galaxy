package com.wuxiu.galaxy.service.core.biz.service.smsservice;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageStorageInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageStorageRecordQueryDTO;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LuggageStorageRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 完成寄存时，发送短信服务（自动执行）
 *
 * @author: wuxiu
 * @date: 2019/4/25 22:09
 */
@Slf4j
@Component
public class FinishStorageEventSmsService {

    @Autowired
    private LuggageStorageRecordService recordService;

    /**
     * todo:行李寄存结束时间结束前 15 min 短信通知寄存人，
     */
    private void notifyDepositorBySMS() {
        LuggageStorageRecordQueryDTO queryDTO = new LuggageStorageRecordQueryDTO();

        // 获取所有的寄存记录信息
        PageInfo<LuggageStorageInfoDTO> storageInfoDTOPageInfo =
                recordService.queryStorageRecordList(queryDTO);
        List<LuggageStorageInfoDTO> storageInfoDTOS = storageInfoDTOPageInfo.getRecords();

    }


}
