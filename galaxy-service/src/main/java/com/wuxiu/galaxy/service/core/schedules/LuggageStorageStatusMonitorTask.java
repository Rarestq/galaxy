package com.wuxiu.galaxy.service.core.schedules;

import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 行李寄存结束日期监控任务
 *
 * @author: wuxiu
 * @date: 2019/4/23 16:34
 */
@Slf4j
@Component
public class LuggageStorageStatusMonitorTask {

    @Autowired
    private LuggageStorageRecordManager storageRecordManager;

    /**
     * 短信通知寄存者行李寄存即将逾期(寄存结束时间前 15 min 通知)，若已逾期，则更新状态
     * <p>
     * 每隔五秒更新一次行李寄存记录的状态
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void refreshLuggageStorageStatus() {
        //todo:定时检查行李寄存记录的寄存结束时间

        // 查询所有有效的行李寄存记录信息
        List<LuggageStorageRecord> storageRecords =
                storageRecordManager.selectAllStorageRecords();
        log.info("start scheduling....");

    }
}
