package com.wuxiu.galaxy.service.core.bus.subscriber;

import com.google.common.eventbus.Subscribe;
import com.wuxiu.galaxy.api.common.enums.LuggageOverdueStatusEnum;
import com.wuxiu.galaxy.api.common.enums.UserTypeEnum;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.api.dto.SaveLuggageOverdueRecordDTO;
import com.wuxiu.galaxy.service.core.biz.service.LuggageOverdueRecordService;
import com.wuxiu.galaxy.service.core.biz.service.LuggageStorageRecordService;
import com.wuxiu.galaxy.service.core.bus.event.SyncOverdueRecordEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 行李逾期记录订阅者
 *
 * @author: wuxiu
 * @date: 2019/4/23 17:11
 */
@Slf4j
@Component
public class LuggageOverdueRecordSubscriber {

    @Autowired
    private LuggageOverdueRecordService luggageOverdueRecordService;

    @Autowired
    private LuggageStorageRecordService storageRecordService;

    /**
     * 自动创建行李逾期记录
     *
     * @param event
     */
    @Subscribe
    public void autoCreateLugaggeOverdueRecord(SyncOverdueRecordEvent event) {
        log.info("自动创建行李逾期记录开始，event:{}", event);

        SaveLuggageOverdueRecordDTO overdueRecordDTO = new SaveLuggageOverdueRecordDTO();

        overdueRecordDTO.setLuggageId(event.getLuggageId());
        overdueRecordDTO.setRemark(event.getRemark());
        Integer status = LuggageOverdueStatusEnum.valueOf(event.getStatus()).getCode();
        overdueRecordDTO.setStatus(status);

        OperateUserDTO operateUserDTO = new OperateUserDTO();
        operateUserDTO.setOperateUserId(0L);
        operateUserDTO.setName(UserTypeEnum.SYSTEM.getDesc());
        operateUserDTO.setOperateUserPhone("-");
        operateUserDTO.setUserTypeEnum(UserTypeEnum.SYSTEM);

        luggageOverdueRecordService.createLuggageOverdueRecord(overdueRecordDTO,
                operateUserDTO);
        log.info("自动创建行李逾期记录结束");
    }
}
