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
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordInfoDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageOverdueRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.SaveLuggageOverdueRecordDTO;
import com.wuxiu.galaxy.dal.dao.LuggageOverdueRecordDao;
import com.wuxiu.galaxy.dal.domain.LuggageOverdueRecord;
import com.wuxiu.galaxy.dal.manager.LuggageOverdueRecordManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;

/**
 * <p>LuggageOverdueRecordManager</p>
 * <p>
 * 行李逾期未取记录表
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-22
 */
@Component
public class LuggageOverdueRecordManagerImpl extends BaseManagerImpl<LuggageOverdueRecordDao, LuggageOverdueRecord> implements LuggageOverdueRecordManager {

    /**
     * 创建行李逾期记录
     *
     * @param saveLuggageOverdueRecordDTO
     * @return
     */
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
        if (StringUtils.isNotEmpty(recordQueryDTO.getLuggageRecordNo())) {
            wrapper.eq("luggage_record_no", recordQueryDTO.getLuggageRecordNo());
        }

        if (StringUtils.isNotEmpty(recordQueryDTO.getDepositorName())) {
            wrapper.eq("depositor_name", recordQueryDTO.getDepositorName());
        }

        if (Objects.nonNull(recordQueryDTO.getStatus())) {
            wrapper.eq("status", recordQueryDTO.getStatus());
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("luggage_overdue_record_id", false);

        // 查询 LuggageOverdueRecord 信息
        Page<LuggageOverdueRecord> overdueRecordPage =
                selectPage(recordQueryDTO.getPage(), wrapper);

        return buildLuggageStorageInfoDTO(overdueRecordPage);
    }

    /**
     * 构造 LuggageOverdueRecordInfoDTO 对象
     *
     * @param overdueRecordPage
     * @return
     */
    private Page<LuggageOverdueRecordInfoDTO> buildLuggageStorageInfoDTO(
            Page<LuggageOverdueRecord> overdueRecordPage) {

        List<LuggageOverdueRecordInfoDTO> overdueRecordDTOS = newArrayList();
        List<LuggageOverdueRecord> records = overdueRecordPage.getRecords();
        records.forEach(overdueRecord -> {
            LuggageOverdueRecordInfoDTO storageInfoDTO = new LuggageOverdueRecordInfoDTO();

            storageInfoDTO.setLuggageOverdueRecordId(
                    overdueRecord.getLuggageOverdueRecordId());
            storageInfoDTO.setLuggageId(overdueRecord.getLuggageId());
            storageInfoDTO.setLuggageRecordNo(overdueRecord.getLuggageRecordNo());

            storageInfoDTO.setAdminId(overdueRecord.getAdminId());
            storageInfoDTO.setAdminName(overdueRecord.getAdminName());
            storageInfoDTO.setDepositorName(overdueRecord.getDepositorName());
            storageInfoDTO.setDepositorPhone(overdueRecord.getDepositorPhone());

            storageInfoDTO.setRemark(overdueRecord.getRemark());
            storageInfoDTO.setStatus(overdueRecord.getStatus());

            overdueRecordDTOS.add(storageInfoDTO);
        });

        Page<LuggageOverdueRecordInfoDTO> page = new Page<>();
        page.setRecords(overdueRecordDTOS);
        page.setTotal(overdueRecordPage.getTotal());

        return page;
    }
}
