package com.wuxiu.galaxy.service.core.schedules;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageStorageInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageStorageRecordQueryDTO;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LuggageStorageRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 行李寄存日期监控任务
 *
 * @author: wuxiu
 * @date: 2019/4/23 16:34
 */
@Slf4j
@Component
public class LuggageStorageStatusMonitorTask {

    @Autowired
    private LuggageStorageRecordService storageRecordService;

    /**
     * 短信通知寄存者行李寄存即将逾期(寄存结束时间前 15 min 通知)，若已逾期，则更新状态
     *
     * 每隔半小时更新一次行李寄存记录的状态
     */
    // @Scheduled(fixedDelay = 30 * 60000L )
    @Scheduled(cron = "* 30 * * * ?")
    public void refreshLuggageStorageStatus() {
        //todo:定时检查行李寄存记录的寄存结束时间
        LuggageStorageRecordQueryDTO queryDTO = new LuggageStorageRecordQueryDTO();

        PageInfo<LuggageStorageInfoDTO> storageInfoDTOPageInfo =
                storageRecordService.queryStorageRecordList(queryDTO);

    }
}
