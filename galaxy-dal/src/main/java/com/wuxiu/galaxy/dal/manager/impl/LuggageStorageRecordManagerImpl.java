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
import com.wuxiu.galaxy.api.common.enums.LuggageStorageStatusEnum;
import com.wuxiu.galaxy.api.dto.LuggageStorageInfoDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageStorageRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.NewLuggageStorageRecordDTO;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.dao.LuggageStorageRecordDao;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;

/**
 * <p>LuggageStorageRecordManager</p>
 * <p>
 * 行李寄存-取件表
 * </p>
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-22
 */
@Component
public class LuggageStorageRecordManagerImpl extends BaseManagerImpl<LuggageStorageRecordDao, LuggageStorageRecord> implements LuggageStorageRecordManager {

    /**
     * 新增行李寄存记录
     *
     * @param newLuggageStorageRecordDTO
     * @return
     */
    @Override
    public Long insertLuggageStorageRecord(
            NewLuggageStorageRecordDTO newLuggageStorageRecordDTO) {
        return null;
    }

    /**
     * 查询行李寄存列表信息
     *
     * @param recordQueryDTO
     * @return
     */
    @Override
    public Page<LuggageStorageInfoDTO> queryStorageRecordList(
            LuggageStorageRecordQueryDTO recordQueryDTO) {

        // 构造查询参数
        Wrapper<LuggageStorageRecord> wrapper = new EntityWrapper<>();
        if (Objects.nonNull(recordQueryDTO.getLuggageId())) {
            wrapper.eq("luggage_id", recordQueryDTO.getLuggageId());
        }

        if (StringUtils.isNotEmpty(recordQueryDTO.getDepositorName())) {
            wrapper.eq("depositor_name", recordQueryDTO.getDepositorName());
        }

        if (StringUtils.isNotEmpty(recordQueryDTO.getDepositorPhone())) {
            wrapper.eq("depositor_phone", recordQueryDTO.getDepositorPhone());
        }

        if (StringUtils.isNotEmpty(recordQueryDTO.getLuggageRecordNo())) {
            wrapper.eq("luggage_record_no", recordQueryDTO.getLuggageRecordNo());
        }

        if (Objects.nonNull(recordQueryDTO.getStorageStartTime())) {
            wrapper.between("gmt_create", recordQueryDTO.getStorageStartTime(),
                    LocalDateTime.now());
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("luggage_id", false);

        // 查询 LuggageStorageRecord 信息
        Page<LuggageStorageRecord> storageRecordPage =
                selectPage(recordQueryDTO.getPage(), wrapper);

        return buildLuggageStorageInfoDTO(storageRecordPage);
    }

    /**
     * 构建 LuggageStorageInfoDTO 对象
     *
     * @param storageRecordPage
     * @return
     */
    private Page<LuggageStorageInfoDTO> buildLuggageStorageInfoDTO(
            Page<LuggageStorageRecord> storageRecordPage) {

        List<LuggageStorageInfoDTO> storageInfoDTOS = newArrayList();
        List<LuggageStorageRecord> storageRecords = storageRecordPage.getRecords();
        storageRecords.forEach(storageRecord -> {
            LuggageStorageInfoDTO storageInfoDTO = new LuggageStorageInfoDTO();
            storageInfoDTO.setLuggageId(storageRecord.getLuggageId());
            storageInfoDTO.setLuggageRecordNo(storageRecord.getLuggageRecordNo());
            storageInfoDTO.setLuggageTypeId(storageRecord.getLuggageTypeId());
            storageInfoDTO.setAdminId(storageRecord.getAdminId());
            storageInfoDTO.setAdminName(storageRecord.getAdminName());
            storageInfoDTO.setAdminPhone(storageRecord.getAdminPhone());
            storageInfoDTO.setDepositorName(storageRecord.getDepositorName());
            storageInfoDTO.setDepositorPhone(storageRecord.getDepositorPhone());
            storageInfoDTO.setRemark(storageRecord.getRemark());
            storageInfoDTO.setStatus(
                    LuggageStorageStatusEnum.getDescByCode(storageRecord.getStatus()));
            storageInfoDTO.setStorageStartTime(storageRecord.getStorageStartTime());
            storageInfoDTO.setStorageEndTime(storageRecord.getStorageEndTime());

            storageInfoDTOS.add(storageInfoDTO);
        });

        Page<LuggageStorageInfoDTO> page = new Page<>();
        page.setRecords(storageInfoDTOS);
        page.setTotal(storageRecordPage.getTotal());
        return page;
    }
}
