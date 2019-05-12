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
import com.wuxiu.galaxy.api.common.enums.FeeTypeEnum;
import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import com.wuxiu.galaxy.api.dto.StatisticsResultDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordDTO;
import com.wuxiu.galaxy.dal.common.dto.TurnoverRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.utils.StreamUtil;
import com.wuxiu.galaxy.dal.dao.TurnoverRecordDao;
import com.wuxiu.galaxy.dal.domain.Admin;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.domain.TurnoverRecord;
import com.wuxiu.galaxy.dal.manager.AdminManager;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.dal.manager.TurnoverRecordManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;

/**
 * <p>TurnoverRecordManager</p>
 * <p>
 * 营业额记录表
 * </p>
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-22
 */
@Component
public class TurnoverRecordManagerImpl extends BaseManagerImpl<TurnoverRecordDao, TurnoverRecord> implements TurnoverRecordManager {

    @Autowired
    private LuggageStorageRecordManager storageRecordManager;

    @Autowired
    private AdminManager adminManager;

    /**
     * 查询营业额记录列表信息
     *
     * @param recordQueryDTO
     * @return
     */
    @Override
    public Page<TurnoverRecordDTO> queryTurnoverRecordList(
            TurnoverRecordQueryDTO recordQueryDTO) {
        // 构造查询参数
        Wrapper<TurnoverRecord> wrapper = new EntityWrapper<>();

        // 根据行李类型id查询其相关的行李寄存记录
        List<LuggageStorageRecord> storageRecords =
                storageRecordManager.selectRecordsByLuggageTypeId(
                        Collections.singletonList(recordQueryDTO.getLuggageTypeId()));
        List<Long> luggageStorageIds = StreamUtil.collectDistinctKeyProperty(
                storageRecords, LuggageStorageRecord::getLuggageId);
        if (Objects.nonNull(luggageStorageIds)) {
            wrapper.in("luggage_id", luggageStorageIds);
        }

        if (StringUtils.isNotEmpty(recordQueryDTO.getAdminName())) {
            // 根据管理员姓名查询管理员信息
            Admin admin = adminManager.selectAdminByName(recordQueryDTO.getAdminName());
            wrapper.eq("admin_id", admin.getAdminId());
        }

        if (Objects.nonNull(recordQueryDTO.getGmtCreate())) {
            wrapper.between("gmt_create", recordQueryDTO.getGmtCreate(),
                    LocalDateTime.now());
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("turnover_record_id", false);

        Page<TurnoverRecord> turnoverRecordPage =
                selectPage(recordQueryDTO.getPage(), wrapper);
        List<TurnoverRecord> turnoverRecords = turnoverRecordPage.getRecords();

        List<Long> luggageIds = StreamUtil.convert(turnoverRecords,
                TurnoverRecord::getLuggageId);
        // 查询行李寄存信息
        List<LuggageStorageRecord> luggageStorageRecords =
                storageRecordManager.getStorageRecordsByIds(luggageIds);

        // 将查询到的 LuggageStorageRecord 信息按照行李寄存主键id进行分组
        Map<Long, LuggageStorageRecord> storageRecordMap =
                StreamUtil.toMap(luggageStorageRecords, LuggageStorageRecord::getLuggageId);

        return buildTurnoverRecordDTO(turnoverRecordPage, storageRecordMap);
    }

    /**
     * 根据行李寄存id查询对应的营业额记录
     *
     * @param luggageId
     * @return
     */
    @Override
    public TurnoverRecord getTurnoverRecordByLuggageId(Long luggageId) {
        Wrapper<TurnoverRecord> wrapper = new EntityWrapper<TurnoverRecord>()
                .eq("luggage_id", luggageId);

        return selectOne(wrapper);
    }

    @Override
    public List<StatisticsResultDTO> statisticsTurnoverByAdmin() {

        return baseDao.statisticsTurnoverByAdmin();
    }

    @Override
    public List<StatisticsResultDTO> statisticsTurnoverByFeeType() {
        return baseDao.statisticsTurnoverByFeeType();
    }

    /**
     * 构造 TurnoverRecordDTO 对象
     *
     * @param turnoverRecordPage
     * @return
     */
    private Page<TurnoverRecordDTO> buildTurnoverRecordDTO(
            Page<TurnoverRecord> turnoverRecordPage,
            Map<Long, LuggageStorageRecord> storageRecordMap) {

        List<TurnoverRecordDTO> recordDTOS = newArrayList();
        List<TurnoverRecord> turnoverRecords = turnoverRecordPage.getRecords();

        turnoverRecords.forEach(turnoverRecord -> {
            TurnoverRecordDTO turnoverRecordDTO = new TurnoverRecordDTO();
            LuggageStorageRecord luggageStorageRecord = storageRecordMap.get(
                    turnoverRecord.getLuggageId());

            turnoverRecordDTO.setTurnoverRecordId(turnoverRecord.getTurnoverRecordId());
            turnoverRecordDTO.setAdminId(turnoverRecord.getAdminId());
            turnoverRecordDTO.setAdminName(luggageStorageRecord.getAdminName());
            turnoverRecordDTO.setLuggageId(turnoverRecord.getLuggageId());
            turnoverRecordDTO.setLuggageStorageRecordNo(luggageStorageRecord
                    .getLuggageRecordNo());
            turnoverRecordDTO.setLuggageType(LuggageTypeEnum.getDescByCode(
                    luggageStorageRecord.getLuggageTypeId()));
            turnoverRecordDTO.setCalculationRuleId(
                    turnoverRecord.getCalculationRuleId());
            turnoverRecordDTO.setFee(turnoverRecord.getFee());
            turnoverRecordDTO.setFeeType(FeeTypeEnum.getDescByCode(turnoverRecord
                    .getFeeType()));
            turnoverRecordDTO.setRemark(turnoverRecord.getRemark());
            turnoverRecordDTO.setGmtCreate(turnoverRecord.getGmtCreate().toString());
            turnoverRecordDTO.setGmtModified(turnoverRecord.getGmtModified().toString());
            recordDTOS.add(turnoverRecordDTO);
        });

        Page<TurnoverRecordDTO> page = new Page<>();
        page.setRecords(recordDTOS);
        page.setTotal(turnoverRecordPage.getTotal());

        return page;
    }

}
