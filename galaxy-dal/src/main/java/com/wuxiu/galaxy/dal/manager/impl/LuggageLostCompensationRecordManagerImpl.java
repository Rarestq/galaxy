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
import com.wuxiu.galaxy.api.dto.LostCompensateRecordInfoDTO;
import com.wuxiu.galaxy.dal.common.dto.LostCompensateRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageLostCompensateDTO;
import com.wuxiu.galaxy.dal.dao.LuggageLostCompensationRecordDao;
import com.wuxiu.galaxy.dal.domain.LuggageLostCompensationRecord;
import com.wuxiu.galaxy.dal.domain.LuggageType;
import com.wuxiu.galaxy.dal.manager.LuggageLostCompensationRecordManager;
import com.wuxiu.galaxy.dal.manager.LuggageTypeManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.util.Lists.newArrayList;

/**
 * <p>LuggageLostCompensationRecordManager</p>
 * <p>
 * 行李遗失赔偿记录表
 * </p>
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-22
 */
@Component
public class LuggageLostCompensationRecordManagerImpl extends BaseManagerImpl<LuggageLostCompensationRecordDao, LuggageLostCompensationRecord> implements LuggageLostCompensationRecordManager {

    @Autowired
    private LuggageTypeManager luggageTypeManager;

    /**
     * 查询行李遗失赔偿登记记录
     *
     * @param recordQueryDTO
     * @return
     */
    @Override
    public Page<LostCompensateRecordInfoDTO> queryLostCompensateRecordList(
            LostCompensateRecordQueryDTO recordQueryDTO) {

        // 构造查询条件
        Wrapper<LuggageLostCompensationRecord> wrapper = new EntityWrapper<>();
        if (Objects.nonNull(recordQueryDTO.getLuggageLostCompensationRecordId())) {
            wrapper.eq("luggage_lost_compensation_record_id",
                    recordQueryDTO.getLuggageLostCompensationRecordId());
        }

        if (StringUtils.isNotEmpty(recordQueryDTO.getAdminName())) {
            wrapper.eq("admin_name", recordQueryDTO.getAdminName());
        }

        if (StringUtils.isNotEmpty(recordQueryDTO.getDepositorName())) {
            wrapper.eq("depositor_name", recordQueryDTO.getDepositorName());
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("luggage_lost_compensation_record_id", false);

        // 根据行李类型名称查询行李类型信息
        LuggageType luggageType = luggageTypeManager.selectByDesc(
                recordQueryDTO.getLuggageTypeDesc());

        // 查询 LuggageLostCompensationRecord 信息
        Page<LuggageLostCompensationRecord> recordPage =
                selectPage(recordQueryDTO.getPage(), wrapper);

        return buildLostCompensateRecordInfoDTO(recordPage, luggageType);
    }

    /**
     * 遗失行李赔偿
     *
     * @param lostCompensateDTO
     */
    @Override
    public Long compensateByLuggageType(LuggageLostCompensateDTO lostCompensateDTO) {
        LuggageLostCompensationRecord compensationRecord =
                new LuggageLostCompensationRecord();

        compensationRecord.setAdminId(lostCompensateDTO.getAdminId());
        compensationRecord.setAdminName(lostCompensateDTO.getAdminName());
        compensationRecord.setDepositorName(lostCompensateDTO.getDepositorName());
        compensationRecord.setDepositorPhone(lostCompensateDTO.getDepositorPhone());
        compensationRecord.setLostCompensateRecordNo(lostCompensateDTO
                .getLostCompensateRecordNo());
        compensationRecord.setLostRegistrationRecordId(lostCompensateDTO
                .getLostRegistrationRecordId());
        compensationRecord.setCompensationFee(lostCompensateDTO.getCompensationFee());
        compensationRecord.setLuggageTypeId(lostCompensateDTO.getLuggageType());
        compensationRecord.setRemark(lostCompensateDTO.getRemark());

        // 创建赔偿记录
        insert(compensationRecord);

        return compensationRecord.getLuggageLostCompensationRecordId();
    }

    /**
     * 构造 LostCompensateRecordInfoDTO 对象
     *
     * @param recordPage
     * @return
     */
    private Page<LostCompensateRecordInfoDTO> buildLostCompensateRecordInfoDTO(
            Page<LuggageLostCompensationRecord> recordPage,
            LuggageType luggageType) {
        List<LostCompensateRecordInfoDTO> compensateRecordInfoDTOS = newArrayList();
        List<LuggageLostCompensationRecord> records = recordPage.getRecords();

        records.forEach(record -> {
            LostCompensateRecordInfoDTO recordInfoDTO = new LostCompensateRecordInfoDTO();
            recordInfoDTO.setLuggageLostCompensationRecordId(
                    record.getLuggageLostCompensationRecordId());
            recordInfoDTO.setLostCompensateRecordNo(record.getLostCompensateRecordNo());
            recordInfoDTO.setDepositorName(record.getDepositorName());
            recordInfoDTO.setDepositorPhone(record.getDepositorPhone());
            recordInfoDTO.setAdminId(record.getAdminId());
            recordInfoDTO.setAdminName(record.getAdminName());
            recordInfoDTO.setLuggageTypeDesc(luggageType.getLuggageType());
            recordInfoDTO.setCompensateTime(record.getGmtCreate());
            recordInfoDTO.setRemark(record.getRemark());
            recordInfoDTO.setCompensationFee(record.getCompensationFee());

            compensateRecordInfoDTOS.add(recordInfoDTO);
        });

        Page<LostCompensateRecordInfoDTO> page = new Page<>();
        page.setRecords(compensateRecordInfoDTOS);
        page.setTotal(recordPage.getTotal());

        return page;
    }
}
