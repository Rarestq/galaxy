package com.wuxiu.galaxy.service.core.biz.service.smsservice;

import com.google.common.eventbus.AsyncEventBus;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageStorageInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageStorageRecordQueryDTO;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LuggageStorageRecordService;
import com.wuxiu.galaxy.service.core.bus.event.CreateOverdueRecordEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
    private AsyncEventBus asyncEventBus;

    @Autowired
    private LuggageStorageRecordService recordService;

    /**
     * todo:校验行李寄存结束时间是否逾期，结束前 15 min 短信通知寄存人，
     * 逾期后，发送事件给「逾期未取清理服务」，自动创建逾期记录
     */
    private void notifyDepositorBySMS() {
        LuggageStorageRecordQueryDTO queryDTO = new LuggageStorageRecordQueryDTO();

        // 获取所有的寄存记录信息
        PageInfo<LuggageStorageInfoDTO> storageInfoDTOPageInfo =
                recordService.queryStorageRecordList(queryDTO);
        List<LuggageStorageInfoDTO> storageInfoDTOS = storageInfoDTOPageInfo.getRecords();

        for (LuggageStorageInfoDTO storageInfoDTO : storageInfoDTOS) {
            LocalDateTime storageEndTime = storageInfoDTO.getStorageEndTime();
            long storageEndTimeMilli =
                    storageEndTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();

            // 已逾期，发送事件
            if (isTimeExpired(storageEndTimeMilli)) {
                asyncEventBus.post(CreateOverdueRecordEvent.builder()
                        .luggageId(storageInfoDTO.getLuggageId())
                        .status(storageInfoDTO.getStatus())
                        .remark(storageInfoDTO.getRemark())
                        .build());
            }
        }
    }

    /**
     * 判断行李寄存结束时间相对系统当前时间是否已逾期
     *
     * @param timeValue
     * @return
     */
    private boolean isTimeExpired(long timeValue) {
        return timeValue > System.currentTimeMillis();
    }
}
