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
import com.wuxiu.galaxy.api.dto.LuggageStorageInfoDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageStorageRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.NewLuggageStorageRecordDTO;
import com.wuxiu.galaxy.dal.dao.LuggageStorageRecordDao;
import com.wuxiu.galaxy.dal.domain.LuggageCabinet;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.domain.TurnoverRecord;
import com.wuxiu.galaxy.dal.manager.LuggageCabinetManager;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.dal.manager.TurnoverRecordManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
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

    @Autowired
    private TurnoverRecordManager turnoverRecordManager;

    @Autowired
    private LuggageCabinetManager cabinetManager;

    /**
     * 新增行李寄存记录
     *
     * @param newLuggageStorageRecordDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Long insertLuggageStorageRecord(
            NewLuggageStorageRecordDTO newLuggageStorageRecordDTO) {

        // 构造 LuggageStorageRecord 对象
        LuggageStorageRecord storageRecord =
                buildLuggageStorageRecord(newLuggageStorageRecordDTO);

        // 新增行寄存记录
        insert(storageRecord);

        // 将寄存的费用及管理员相关信息添加到「营业额记录表中」
        TurnoverRecord turnoverRecord =
                buildTurnoverRecord(newLuggageStorageRecordDTO, storageRecord);
        turnoverRecordManager.insert(turnoverRecord);

        // 更新对应行李寄存柜的状态
        LuggageCabinet luggageCabinet = cabinetManager.getCabinetById(
                newLuggageStorageRecordDTO.getCabinetId());
        luggageCabinet.setStatus(LuggageCabinetStatusEnum.HAD_OCCUPIED.getCode());
        luggageCabinet.setGmtModified(LocalDateTime.now());

        cabinetManager.updateById(luggageCabinet);

        return storageRecord.getLuggageId();
    }

    /**
     * 构造 TurnoverRecord 对象
     *
     * @param newLuggageStorageRecordDTO
     * @param storageRecord
     * @return
     */
    private TurnoverRecord buildTurnoverRecord(
            NewLuggageStorageRecordDTO newLuggageStorageRecordDTO,
            LuggageStorageRecord storageRecord) {

        TurnoverRecord turnoverRecord = new TurnoverRecord();
        turnoverRecord.setAdminId(storageRecord.getAdminId());
        turnoverRecord.setAdminName(storageRecord.getAdminName());
        turnoverRecord.setCalculationRuleId(newLuggageStorageRecordDTO
                .getCalculateRuleId());
        turnoverRecord.setLuggageId(storageRecord.getLuggageId());
        turnoverRecord.setFee(newLuggageStorageRecordDTO.getFeeValue().toString());
        turnoverRecord.setRemark(newLuggageStorageRecordDTO
                .getFeeCalculationProcessDesc());

        return turnoverRecord;
    }

    /**
     * 构造 LuggageStorageRecord 对象
     *
     * @param newLuggageStorageRecordDTO
     * @return
     */
    private LuggageStorageRecord buildLuggageStorageRecord(
            NewLuggageStorageRecordDTO newLuggageStorageRecordDTO) {

        LuggageStorageRecord storageRecord = new LuggageStorageRecord();
        storageRecord.setAdminId(newLuggageStorageRecordDTO.getAdminId());
        storageRecord.setAdminName(newLuggageStorageRecordDTO.getAdminName());
        storageRecord.setAdminPhone(newLuggageStorageRecordDTO.getAdminPhone());

        storageRecord.setCabinetId(newLuggageStorageRecordDTO.getCabinetId());
        storageRecord.setCabinetNo(newLuggageStorageRecordDTO.getLuggageCabinetNo());

        storageRecord.setDepositorName(newLuggageStorageRecordDTO.getDepositorName());
        storageRecord.setDepositorPhone(newLuggageStorageRecordDTO.getDepositorPhone());

        storageRecord.setLuggageRecordNo(newLuggageStorageRecordDTO.getLuggageRecordNo());
        storageRecord.setLuggageTypeId(newLuggageStorageRecordDTO.getLuggageTypeId());
        storageRecord.setRemark(newLuggageStorageRecordDTO.getRemark());

        storageRecord.setStorageStartTime(newLuggageStorageRecordDTO
                .getStorageStartTime());
        storageRecord.setStorageEndTime(newLuggageStorageRecordDTO.getStorageEndTime());
        storageRecord.setGmtCreate(LocalDateTime.now());
        storageRecord.setGmtModified(LocalDateTime.now());

        return storageRecord;
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

        String queryCondition = recordQueryDTO.getQueryCondition();
        if (StringUtils.isNotBlank(queryCondition)) {
            wrapper.like("depositor_name", queryCondition)
                    .or().like("depositor_phone", queryCondition)
                    .or().like("luggage_record_no", queryCondition);
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("luggage_id", false);

        // 查询 LuggageStorageRecord 信息
        Page<LuggageStorageRecord> storageRecordPage =
                selectPage(recordQueryDTO.getPage(), wrapper);

        return buildLuggageStorageInfoDTO(storageRecordPage);
    }

    /**
     * 根据行李类型id查询行李寄存信息
     *
     * @param luggageTypeIds
     * @return
     */
    @Override
    public List<LuggageStorageRecord> selectRecordsByLuggageTypeId(
            List<Long> luggageTypeIds) {
        Wrapper<LuggageStorageRecord> wrapper = new EntityWrapper<>();
        wrapper.in("luggage_type_id", luggageTypeIds)
                .ne("deleted", 1);

        return selectList(wrapper);
    }

    /**
     * 通过行李寄存编号模糊查询行李寄存信息
     *
     * @param luggageRecordNo
     * @return
     */
    @Override
    public LuggageStorageRecord selectByLuggageRecordNo(String luggageRecordNo) {
        Wrapper<LuggageStorageRecord> wrapper = new EntityWrapper<LuggageStorageRecord>()
                .like("luggage_record_no", luggageRecordNo);

        return selectOne(wrapper);
    }

    /**
     * 查询所有的行李寄存记录
     *
     * @return
     */
    @Override
    public List<LuggageStorageRecord> selectAllStorageRecords() {
        Wrapper<LuggageStorageRecord> wrapper = new EntityWrapper<>();

        return selectList(wrapper);
    }

    /**
     * 根据id查询行李寄存记录
     *
     * @param luggageIds
     * @return
     */
    @Override
    public List<LuggageStorageRecord> getStorageRecordsByIds(List<Long> luggageIds) {
        if (CollectionUtils.isEmpty(luggageIds)) {
            return Collections.emptyList();
        }

        Wrapper<LuggageStorageRecord> wrapper =
                new EntityWrapper<LuggageStorageRecord>()
                        .in("luggage_id", luggageIds);

        return selectList(wrapper);
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

            Long luggageId = storageRecord.getLuggageId();
            storageInfoDTO.setLuggageId(luggageId);
            storageInfoDTO.setLuggageRecordNo(storageRecord.getLuggageRecordNo());
            storageInfoDTO.setLuggageTypeId(storageRecord.getLuggageTypeId());
            storageInfoDTO.setAdminId(storageRecord.getAdminId());
            storageInfoDTO.setAdminName(storageRecord.getAdminName());
            storageInfoDTO.setAdminPhone(storageRecord.getAdminPhone());
            storageInfoDTO.setDepositorName(storageRecord.getDepositorName());
            storageInfoDTO.setDepositorPhone(storageRecord.getDepositorPhone());

            storageInfoDTO.setCabinetId(storageRecord.getCabinetId());
            storageInfoDTO.setLuggageCabinetNo(storageRecord.getCabinetNo());

            storageInfoDTO.setRemark(storageRecord.getRemark());
            storageInfoDTO.setStatus(LuggageStorageStatusEnum.getDescByCode(
                    storageRecord.getStatus()));
            storageInfoDTO.setStorageStartTime(storageRecord
                    .getStorageStartTime().toString());
            storageInfoDTO.setStorageEndTime(storageRecord.getStorageEndTime()
                    .toString());

            storageInfoDTOS.add(storageInfoDTO);
        });

        Page<LuggageStorageInfoDTO> page = new Page<>(storageRecordPage.getCurrent(), storageRecordPage.getSize());
        page.setRecords(storageInfoDTOS);
        page.setTotal(storageRecordPage.getTotal());

        return page;
    }
}
