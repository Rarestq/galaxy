/**
 *  
 *  * All rights Reserved, Designed By wuxiu
 * <p>
 *  * @Package com.wuxiu.galaxy.dal.dao
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @date: 2018-04-16 20:35:12
 *  * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 *  
 */
package com.wuxiu.galaxy.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.api.common.enums.LuggageCabinetStatusEnum;
import com.wuxiu.galaxy.api.common.enums.LuggageStorageStatusEnum;
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordInfoDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageOverdueRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.SaveLuggageOverdueRecordDTO;
import com.wuxiu.galaxy.dal.common.utils.StreamUtil;
import com.wuxiu.galaxy.dal.dao.LuggageOverdueRecordDao;
import com.wuxiu.galaxy.dal.domain.LuggageCabinet;
import com.wuxiu.galaxy.dal.domain.LuggageOverdueRecord;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.manager.LuggageCabinetManager;
import com.wuxiu.galaxy.dal.manager.LuggageOverdueRecordManager;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;

/**
 * <p>LuggageOverdueRecordManager</p>
 * <p>
 * 行李逾期未取记录表
 * </p>
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-22
 */
@Component
public class LuggageOverdueRecordManagerImpl extends BaseManagerImpl<LuggageOverdueRecordDao, LuggageOverdueRecord> implements LuggageOverdueRecordManager {

    @Autowired
    private LuggageStorageRecordManager storageRecordManager;

    @Autowired
    private LuggageCabinetManager cabinetManager;

    /**
     * 创建行李逾期记录
     *
     * @param saveLuggageOverdueRecordDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Long createLuggageOverdueRecord(
            SaveLuggageOverdueRecordDTO saveLuggageOverdueRecordDTO) {

        LuggageOverdueRecord overdueRecord = new LuggageOverdueRecord();

        overdueRecord.setOverdueRecordNo(saveLuggageOverdueRecordDTO.getOverdueRecordNo());
        overdueRecord.setAdminId(saveLuggageOverdueRecordDTO.getAdminId());
        overdueRecord.setAdminName(saveLuggageOverdueRecordDTO.getAdminName());

        overdueRecord.setDepositorName(saveLuggageOverdueRecordDTO.getDepositorName());
        overdueRecord.setDepositorPhone(saveLuggageOverdueRecordDTO.getDepositorPhone());

        overdueRecord.setLuggageId(saveLuggageOverdueRecordDTO.getLuggageId());
        overdueRecord.setLuggageRecordNo(saveLuggageOverdueRecordDTO.getLuggageRecordNo());

        overdueRecord.setRemark(saveLuggageOverdueRecordDTO.getRemark());
        overdueRecord.setStatus(saveLuggageOverdueRecordDTO.getStatus());

        // 创建行李逾期记录
        insert(overdueRecord);

        // 查询行李寄存记录信息
        LuggageStorageRecord storageRecord = storageRecordManager.selectById(
                saveLuggageOverdueRecordDTO.getLuggageId());

        // 更新行李寄存记录的状态
        LuggageStorageRecord record = new LuggageStorageRecord();
        record.setLuggageId(storageRecord.getLuggageId());
        record.setStatus(LuggageStorageStatusEnum.OVERDUE.getCode());
        record.setGmtModified(LocalDateTime.now());
        storageRecordManager.updateById(record);

        // 行李逾期时，更新寄存柜的状态
        LuggageCabinet luggageCabinet = new LuggageCabinet();
        luggageCabinet.setLuggageCabinetId(storageRecord.getCabinetId());
        luggageCabinet.setStatus(LuggageCabinetStatusEnum.OVERDUE_OCCUPIED.getCode());
        luggageCabinet.setGmtModified(LocalDateTime.now());

        cabinetManager.updateById(luggageCabinet);

        return overdueRecord.getLuggageOverdueRecordId();
    }

    /**
     * 查询行李逾期未取清理记录列表
     *
     * @param recordQueryDTO
     * @return
     */
    @Override
    public Page<LuggageOverdueRecordInfoDTO> queryOverdueRecordList(
            LuggageOverdueRecordQueryDTO recordQueryDTO) {

        // 构造查询参数
        Wrapper<LuggageOverdueRecord> wrapper = new EntityWrapper<>();

        String queryCondition = recordQueryDTO.getQueryCondition();
        if (StringUtils.isNotBlank(queryCondition)) {
            wrapper.like("luggage_record_no", queryCondition)
                    .or().like("depositor_name", queryCondition);
        }

        if (Objects.nonNull(recordQueryDTO.getStatus())) {
            wrapper.eq("status", recordQueryDTO.getStatus());
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("luggage_overdue_record_id", false);

        // 查询 LuggageOverdueRecord 信息
        Page<LuggageOverdueRecord> overdueRecordPage =
                selectPage(recordQueryDTO.getPage(), wrapper);

        List<LuggageOverdueRecord> records = overdueRecordPage.getRecords();

        // 查询行李寄存记录信息
        List<Long> luggageIds = StreamUtil.collectDistinctKeyProperty(records,
                LuggageOverdueRecord::getLuggageId);

        List<LuggageStorageRecord> storageRecords =
                storageRecordManager.getStorageRecordsByIds(luggageIds);

        return buildLuggageStorageInfoDTO(overdueRecordPage, storageRecords);
    }

    /**
     * 构造 LuggageOverdueRecordInfoDTO 对象
     *
     * @param overdueRecordPage
     * @return
     */
    private Page<LuggageOverdueRecordInfoDTO> buildLuggageStorageInfoDTO(
            Page<LuggageOverdueRecord> overdueRecordPage,
            List<LuggageStorageRecord> storageRecords) {

        List<LuggageOverdueRecordInfoDTO> overdueRecordDTOS = newArrayList();
        List<LuggageOverdueRecord> records = overdueRecordPage.getRecords();

        // 将行李寄存记录按照行李寄存记录主键id进行分组
        Map<Long, LuggageStorageRecord> storageRecordMap = StreamUtil.toMap(
                storageRecords, LuggageStorageRecord::getLuggageId);

        records.forEach(overdueRecord -> {
            LuggageOverdueRecordInfoDTO overdueRecordInfoDTO =
                    new LuggageOverdueRecordInfoDTO();

            overdueRecordInfoDTO.setLuggageOverdueRecordId(
                    overdueRecord.getLuggageOverdueRecordId());
            overdueRecordInfoDTO.setOverdueRecordNo(overdueRecord.getOverdueRecordNo());
            overdueRecordInfoDTO.setLuggageId(overdueRecord.getLuggageId());
            overdueRecordInfoDTO.setLuggageRecordNo(overdueRecord.getLuggageRecordNo());
            overdueRecordInfoDTO.setLuggageTypeId(storageRecordMap.get(overdueRecord
                    .getLuggageId()).getLuggageTypeId());

            overdueRecordInfoDTO.setDepositorName(overdueRecord.getDepositorName());
            overdueRecordInfoDTO.setDepositorPhone(overdueRecord.getDepositorPhone());

            overdueRecordInfoDTO.setRemark(overdueRecord.getRemark());
            overdueRecordInfoDTO.setStatus(overdueRecord.getStatus());
            overdueRecordInfoDTO.setGmtCreate(overdueRecord.getGmtCreate().toString());
            overdueRecordInfoDTO.setGmtModified(overdueRecord.getGmtModified().toString());

            overdueRecordDTOS.add(overdueRecordInfoDTO);
        });

        Page<LuggageOverdueRecordInfoDTO> page = new Page<>(overdueRecordPage.getCurrent(), overdueRecordPage.getSize());
        page.setRecords(overdueRecordDTOS);
        page.setTotal(overdueRecordPage.getTotal());

        return page;
    }
}
