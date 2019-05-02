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
import com.wuxiu.galaxy.api.common.enums.LuggageOverdueStatusEnum;
import com.wuxiu.galaxy.api.common.enums.LuggageStorageStatusEnum;
import com.wuxiu.galaxy.api.common.enums.PickupLuggageTypeEnum;
import com.wuxiu.galaxy.api.common.util.DateUtil;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordDTO;
import com.wuxiu.galaxy.dal.common.dto.CommonPickupLuggageDTO;
import com.wuxiu.galaxy.dal.common.dto.MarkLuggageAsLostDTO;
import com.wuxiu.galaxy.dal.common.dto.PickupLuggageRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.PickupOverdueLuggageDTO;
import com.wuxiu.galaxy.dal.dao.PickupLuggageRecordDao;
import com.wuxiu.galaxy.dal.domain.LuggageLostRegistrationRecord;
import com.wuxiu.galaxy.dal.domain.LuggageOverdueRecord;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.domain.PickupLuggageRecord;
import com.wuxiu.galaxy.dal.manager.LuggageLostRegistrationRecordManager;
import com.wuxiu.galaxy.dal.manager.LuggageOverdueRecordManager;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.dal.manager.PickupLuggageRecordManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.util.Lists.newArrayList;

/**
 * <p>PickupLuggageRecordManager</p>
 * <p>
 * 行李取件记录表
 * </p>
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-22
 */
@Component
public class PickupLuggageRecordManagerImpl extends BaseManagerImpl<PickupLuggageRecordDao, PickupLuggageRecord> implements PickupLuggageRecordManager {

    @Autowired
    private LuggageStorageRecordManager storageRecordManager;

    @Autowired
    private LuggageOverdueRecordManager overdueRecordManager;

    @Autowired
    private LuggageLostRegistrationRecordManager registrationRecordManager;

    /**
     * 行李取件
     *
     * @param commonPickupLuggageDTO
     * @return
     */
    @Override
    public void pickupLuggage(CommonPickupLuggageDTO commonPickupLuggageDTO) {
        if (Objects.isNull(commonPickupLuggageDTO)) {
            return;
        }

        // 获取行李寄存记录
        LuggageStorageRecord storageRecord =
                storageRecordManager.selectById(commonPickupLuggageDTO.getLuggageId());
        LuggageStorageRecord luggageStorageRecord = new LuggageStorageRecord();

        // 正常取件（之前状态为「寄存中」），将其状态更新为「已取件」
        if (Objects.equals(LuggageStorageStatusEnum.DEPOSITING.getCode(),
                storageRecord.getStatus())) {
            luggageStorageRecord.setLuggageId(commonPickupLuggageDTO.getLuggageId());
            luggageStorageRecord.setStatus(commonPickupLuggageDTO.getStatus());
            luggageStorageRecord.setGmtModified(commonPickupLuggageDTO.getGmtModified());
        }

        // 更新该行李寄存记录状态信息
        storageRecordManager.updateById(luggageStorageRecord);

        // 创建取件记录
        PickupLuggageRecord pickupLuggageRecord =
                buildPickupLuggageRecord(commonPickupLuggageDTO);
        insert(pickupLuggageRecord);
    }

    /**
     * 构造 PickupLuggageRecord 对象
     *
     * @param commonPickupLuggageDTO
     * @return
     */
    private PickupLuggageRecord buildPickupLuggageRecord(
            CommonPickupLuggageDTO commonPickupLuggageDTO) {

        PickupLuggageRecord pickupLuggageRecord = new PickupLuggageRecord();

        pickupLuggageRecord.setPickupRecordNo(commonPickupLuggageDTO.getPickupRecordNo());
        pickupLuggageRecord.setLuggageId(commonPickupLuggageDTO.getLuggageId());

        pickupLuggageRecord.setAdminId(commonPickupLuggageDTO.getAdminId());
        pickupLuggageRecord.setAdminName(commonPickupLuggageDTO.getAdminName());
        pickupLuggageRecord.setPickerName(commonPickupLuggageDTO.getPickerName());
        pickupLuggageRecord.setPickerPhone(commonPickupLuggageDTO.getPickerPhone());

        pickupLuggageRecord.setPickupType(commonPickupLuggageDTO.getPickupType());
        pickupLuggageRecord.setPickUpTime(commonPickupLuggageDTO.getPickUpTime());

        return pickupLuggageRecord;
    }

    /**
     * 逾期取件
     *
     * @param pickupOverdueLuggageDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void pickupOverdueLuggage(PickupOverdueLuggageDTO pickupOverdueLuggageDTO) {
        if (Objects.isNull(pickupOverdueLuggageDTO)) {
            return;
        }

        Long luggageId = pickupOverdueLuggageDTO.getLuggageId();
        LuggageStorageRecord luggageStorageRecord =
                storageRecordManager.selectById(luggageId);
        LuggageStorageRecord storageRecord = new LuggageStorageRecord();

        // 根据行李寄存记录主键id查询逾期记录信息
        Wrapper<LuggageOverdueRecord> wrapper = new EntityWrapper<>();
        wrapper.eq("luggage_id", luggageId);
        LuggageOverdueRecord luggageOverdueRecord =
                overdueRecordManager.selectOne(wrapper);

        if (Objects.equals(LuggageStorageStatusEnum.OVERDUE.getCode(),
                luggageStorageRecord.getStatus())) {

            storageRecord.setLuggageId(luggageId);
            storageRecord.setGmtModified(LocalDateTime.now());
            storageRecord.setStatus(LuggageStorageStatusEnum.OVERDUE_PICKUP.getCode());
            // 逻辑删除其对应的行李寄存记录
            storageRecordManager.updateById(storageRecord);

            // 构造 PickupLuggageRecord 对象
            PickupLuggageRecord pickupLuggageRecord =
                    buildPickupLuggageRecord(pickupOverdueLuggageDTO);

            // 新增「逾期取件记录」
            insert(pickupLuggageRecord);

            // 构造 LuggageOverdueRecord 对象
            LuggageOverdueRecord overdueRecord = buildLuggageOverdueRecord(
                    pickupOverdueLuggageDTO, luggageStorageRecord, luggageOverdueRecord);

            // 逾期取件后，更新逾期记录状态为「已清理作废」
            overdueRecordManager.updateById(overdueRecord);
        }

    }

    /**
     * 构造 LuggageOverdueRecord 对象
     *
     * @param pickupOverdueLuggageDTO
     * @param luggageStorageRecord
     * @param luggageOverdueRecord
     * @return
     */
    private LuggageOverdueRecord buildLuggageOverdueRecord(
            PickupOverdueLuggageDTO pickupOverdueLuggageDTO,
            LuggageStorageRecord luggageStorageRecord,
            LuggageOverdueRecord luggageOverdueRecord) {

        LuggageOverdueRecord overdueRecord = new LuggageOverdueRecord();

        overdueRecord.setLuggageOverdueRecordId(
                luggageOverdueRecord.getLuggageOverdueRecordId());
        overdueRecord.setLuggageId(pickupOverdueLuggageDTO.getLuggageId());
        overdueRecord.setLuggageRecordNo(pickupOverdueLuggageDTO.getLuggageRecordNo());

        overdueRecord.setAdminId(pickupOverdueLuggageDTO.getAdminId());
        overdueRecord.setAdminName(pickupOverdueLuggageDTO.getAdminName());

        overdueRecord.setDepositorName(pickupOverdueLuggageDTO.getDepositorName());
        overdueRecord.setDepositorPhone(pickupOverdueLuggageDTO.getDepositorPhone());

        overdueRecord.setStatus(LuggageOverdueStatusEnum.CLEARED_UP.getCode());
        overdueRecord.setGmtModified(LocalDateTime.now());

        long overdueHours = DateUtil.calculateDate2Hours(
                LocalDateTime.now(), luggageStorageRecord.getStorageEndTime());
        // 设置逾期补收的费用计算描述（不满一小时按一小时算）
        overdueRecord.setRemark(pickupOverdueLuggageDTO.getFeeCalculationProcessDesc());
//        overdueRecord.setRemark("此次寄存共逾期【" + overdueHours + "】小时" +
////                "，额外收取超时费用为【" + pickupOverdueLuggageDTO.getFeeValue() + "】元");

        return overdueRecord;
    }

    /**
     * 计算逾期应收费用
     *
     * @param overdueHours
     * @return
     */
    private String calculateOverdueFee(long overdueHours) {
        if (overdueHours < 1) {
            overdueHours = 1;
        }

        return null;
    }

    /**
     * 构造 PickupLuggageRecord 对象
     *
     * @param pickupOverdueLuggageDTO
     * @return
     */
    private PickupLuggageRecord buildPickupLuggageRecord(
            PickupOverdueLuggageDTO pickupOverdueLuggageDTO) {
        PickupLuggageRecord pickupLuggageRecord = new PickupLuggageRecord();

        pickupLuggageRecord.setPickupRecordNo(pickupOverdueLuggageDTO
                .getPickupRecordNo());
        pickupLuggageRecord.setPickupType(
                PickupLuggageTypeEnum.OVERDUE_PICK_UP.getCode());
        pickupLuggageRecord.setPickUpTime(LocalDateTime.now());

        pickupLuggageRecord.setAdminName(pickupOverdueLuggageDTO.getAdminName());
        pickupLuggageRecord.setAdminId(pickupOverdueLuggageDTO.getAdminId());

        pickupLuggageRecord.setPickerName(pickupOverdueLuggageDTO.getDepositorName());
        pickupLuggageRecord.setPickerPhone(pickupOverdueLuggageDTO
                .getDepositorPhone());
        pickupLuggageRecord.setLuggageId(pickupOverdueLuggageDTO.getLuggageId());

        return pickupLuggageRecord;
    }

    /**
     * 标记为遗失
     *
     * @param markLuggageAsLostDTO
     * @return
     */
    @Override
    public void markLuggageAsLost(MarkLuggageAsLostDTO markLuggageAsLostDTO) {
        if (Objects.isNull(markLuggageAsLostDTO)) {
            return;
        }

        Long luggageId = markLuggageAsLostDTO.getLuggageId();
        LuggageStorageRecord storageRecord = storageRecordManager.selectById(luggageId);
        LuggageStorageRecord luggageStorageRecord = new LuggageStorageRecord();
        if (Objects.equals(storageRecord.getStatus(),
                LuggageStorageStatusEnum.DEPOSITING.getCode())) {
            // 只有原先是「寄存中」状态才能被标记为「遗失」状态
            luggageStorageRecord.setLuggageId(luggageId);
            luggageStorageRecord.setStatus(LuggageStorageStatusEnum.HAD_LOST.getCode());
            luggageStorageRecord.setGmtModified(LocalDateTime.now());

            // 更新行李寄存记录的状态信息
            storageRecordManager.updateById(luggageStorageRecord);

            // 登记遗失记录
            LuggageLostRegistrationRecord registrationRecord =
                    buildLuggageLostRegistrationRecord(markLuggageAsLostDTO, luggageId);

            registrationRecordManager.insert(registrationRecord);
        }

    }

    /**
     * 查询行李取件记录列表信息
     *
     * @param recordQueryDTO
     * @return
     */
    @Override
    public Page<PickupLuggageRecordDTO> queryStorageRecordList(
            PickupLuggageRecordQueryDTO recordQueryDTO) {
        Wrapper<PickupLuggageRecord> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotEmpty(recordQueryDTO.getDepositorName())) {
            wrapper.like("picker_name", recordQueryDTO.getDepositorName());
        }

        if (Objects.nonNull(recordQueryDTO.getLuggageId())) {
            wrapper.eq("luggage_id", recordQueryDTO.getLuggageId());
        }

        if (Objects.nonNull(recordQueryDTO.getPickupTime())) {
            wrapper.eq("pick_up_time", recordQueryDTO.getPickupTime());
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("pickup_luggage_record_id", false);

        // 查询取件记录
        Page<PickupLuggageRecord> pickupLuggageRecordPage =
                selectPage(recordQueryDTO.getPage(), wrapper);

        return buildPickupLuggageRecordDTO(pickupLuggageRecordPage);
    }

    /**
     * 构造 PickupLuggageRecordDTO 对象
     *
     * @param pickupLuggageRecordPage
     * @return
     */
    private Page<PickupLuggageRecordDTO> buildPickupLuggageRecordDTO(
            Page<PickupLuggageRecord> pickupLuggageRecordPage) {

        List<PickupLuggageRecordDTO> recordDTOS = newArrayList();
        // 获取行李取件的记录列表
        List<PickupLuggageRecord> pickupLuggageRecords =
                pickupLuggageRecordPage.getRecords();
        pickupLuggageRecords.forEach(pickupLuggageRecord -> {
            PickupLuggageRecordDTO pickupLuggageRecordDTO = new PickupLuggageRecordDTO();

            pickupLuggageRecordDTO.setPickupLuggageRecordId(pickupLuggageRecord
                    .getPickupLuggageRecordId());
            pickupLuggageRecordDTO.setLuggageId(pickupLuggageRecord.getLuggageId());
            pickupLuggageRecordDTO.setAdminId(pickupLuggageRecord.getAdminId());
            pickupLuggageRecordDTO.setAdminName(pickupLuggageRecord.getAdminName());

            pickupLuggageRecordDTO.setPickerName(pickupLuggageRecord.getPickerName());
            pickupLuggageRecordDTO.setPickerPhone(pickupLuggageRecord.getPickerPhone());

            pickupLuggageRecordDTO.setPickupType(PickupLuggageTypeEnum.getDescByCode(
                    pickupLuggageRecord.getPickupType()));
            pickupLuggageRecordDTO.setPickUpTime(pickupLuggageRecord.getPickUpTime());
            recordDTOS.add(pickupLuggageRecordDTO);
        });

        Page<PickupLuggageRecordDTO> page = new Page<>();
        page.setRecords(recordDTOS);
        page.setTotal(pickupLuggageRecordPage.getTotal());

        return page;
    }

    /**
     * 构造 LuggageLostRegistrationRecord 对象
     *
     * @param markLuggageAsLostDTO
     * @param luggageId
     * @return
     */
    private LuggageLostRegistrationRecord buildLuggageLostRegistrationRecord(
            MarkLuggageAsLostDTO markLuggageAsLostDTO, Long luggageId) {

        LuggageLostRegistrationRecord registrationRecord =
                new LuggageLostRegistrationRecord();

        registrationRecord.setRegisterRecordNo(markLuggageAsLostDTO.getRegisterRecordNo());
        registrationRecord.setAdminId(markLuggageAsLostDTO.getAdminId());
        registrationRecord.setAdminName(markLuggageAsLostDTO.getAdminName());
        registrationRecord.setDepositorName(markLuggageAsLostDTO.getDepositorName());
        registrationRecord.setDepositorPhone(markLuggageAsLostDTO.getDepositorPhone());

        registrationRecord.setLuggageId(luggageId);
        registrationRecord.setLuggageRecordNo(
                markLuggageAsLostDTO.getLuggageRecordNo());
        registrationRecord.setLuggageTypeId(markLuggageAsLostDTO.getLuggageTypeId());

        registrationRecord.setRemark(markLuggageAsLostDTO.getLuggageTypeDesc());

        return registrationRecord;
    }

}
