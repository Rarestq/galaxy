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
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.api.common.enums.LuggageOverdueStatusEnum;
import com.wuxiu.galaxy.api.common.enums.LuggageStorageStatusEnum;
import com.wuxiu.galaxy.dal.common.dto.MarkLuggageAsLostDTO;
import com.wuxiu.galaxy.dal.common.dto.PickupLuggageDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

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
     * @param pickupLuggageDTO
     * @return
     */
    @Override
    public void pickupLuggage(PickupLuggageDTO pickupLuggageDTO) {
        if (Objects.isNull(pickupLuggageDTO)) {
            return;
        }

        // 获取行李寄存记录
        LuggageStorageRecord storageRecord =
                storageRecordManager.selectById(pickupLuggageDTO.getLuggageId());
        LuggageStorageRecord luggageStorageRecord = new LuggageStorageRecord();

        // 正常取件（之前状态为「寄存中」），将其状态更新为「已取件」
        if (Objects.equals(LuggageStorageStatusEnum.DEPOSITING.getCode(),
                storageRecord.getStatus())) {
            luggageStorageRecord.setLuggageId(pickupLuggageDTO.getLuggageId());
            luggageStorageRecord.setStatus(pickupLuggageDTO.getStatus());
            luggageStorageRecord.setGmtModified(pickupLuggageDTO.getGmtModified());
        }

        // 更新该行李寄存记录状态信息
        storageRecordManager.updateById(luggageStorageRecord);

        // 创建取件记录
        PickupLuggageRecord pickupLuggageRecord = new PickupLuggageRecord();
        pickupLuggageRecord.setLuggageId(pickupLuggageDTO.getLuggageId());

        pickupLuggageRecord.setAdminId(pickupLuggageDTO.getAdminId());
        pickupLuggageRecord.setAdminName(pickupLuggageDTO.getAdminName());
        pickupLuggageRecord.setPickerName(pickupLuggageDTO.getPickerName());
        pickupLuggageRecord.setPickerPhone(pickupLuggageDTO.getPickerPhone());

        pickupLuggageRecord.setPickupType(pickupLuggageDTO.getPickupType());
        pickupLuggageRecord.setPickUpTime(pickupLuggageDTO.getPickUpTime());

        insert(pickupLuggageRecord);
    }

    /**
     * 逾期取件
     *
     * @param pickupOverdueLuggageDTO
     * @return
     */
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

            LuggageOverdueRecord overdueRecord = new LuggageOverdueRecord();

            overdueRecord.setLuggageOverdueRecordId(
                    luggageOverdueRecord.getLuggageOverdueRecordId());
            overdueRecord.setLuggageId(luggageId);
            overdueRecord.setLuggageRecordNo(pickupOverdueLuggageDTO.getLuggageRecordNo());

            overdueRecord.setAdminId(pickupOverdueLuggageDTO.getAdminId());
            overdueRecord.setAdminName(pickupOverdueLuggageDTO.getAdminName());

            overdueRecord.setDepositorName(pickupOverdueLuggageDTO.getDepositorName());
            overdueRecord.setDepositorPhone(pickupOverdueLuggageDTO.getDepositorPhone());

            overdueRecord.setStatus(LuggageOverdueStatusEnum.CLEARED_UP.getCode());
            overdueRecord.setGmtModified(LocalDateTime.now());

            long overdueHours = calculateOverdueHours(
                    luggageStorageRecord.getStorageEndTime(), LocalDateTime.now());
            //todo:设置逾期补收的费用
            overdueRecord.setRemark("此次寄存共逾期【" + overdueHours + "】小时" +
                    "，额外收取超时费用为【" + "】元");

            // 逾期取件后，更新逾期记录状态为「已清理作废」
            overdueRecordManager.updateById(overdueRecord);
        }

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
                    getLuggageLostRegistrationRecord(markLuggageAsLostDTO, luggageId);

            registrationRecordManager.insert(registrationRecord);
        }

    }

    /**
     * 构造 LuggageLostRegistrationRecord 对象
     *
     * @param markLuggageAsLostDTO
     * @param luggageId
     * @return
     */
    private LuggageLostRegistrationRecord getLuggageLostRegistrationRecord(
            MarkLuggageAsLostDTO markLuggageAsLostDTO, Long luggageId) {

        LuggageLostRegistrationRecord registrationRecord =
                new LuggageLostRegistrationRecord();

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

    /**
     * 计算逾期时长
     *
     * @param storageEndTime
     * @param now
     * @return
     */
    private long calculateOverdueHours(LocalDateTime storageEndTime, LocalDateTime now) {
        Duration between = Duration.between(storageEndTime, now);
        return between.toHours();
    }
}
