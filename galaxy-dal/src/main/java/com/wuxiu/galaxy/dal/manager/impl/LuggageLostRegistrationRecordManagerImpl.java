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
import com.wuxiu.galaxy.api.common.enums.LostRegisterRecordStatusEnum;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageLostRegisterRecordQueryDTO;
import com.wuxiu.galaxy.dal.dao.LuggageLostRegistrationRecordDao;
import com.wuxiu.galaxy.dal.domain.LuggageLostRegistrationRecord;
import com.wuxiu.galaxy.dal.manager.LuggageLostRegistrationRecordManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.util.Lists.newArrayList;

/**
 * <p>LuggageLostRegistrationRecordManager</p>
 * <p>
 * 行李遗失登记记录表
 * </p>
 *
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-22
 */
@Component
public class LuggageLostRegistrationRecordManagerImpl extends BaseManagerImpl<LuggageLostRegistrationRecordDao, LuggageLostRegistrationRecord> implements LuggageLostRegistrationRecordManager {

    /**
     * 查询行李遗失登记记录列表
     *
     * @param recordQueryDTO
     * @return
     */
    @Override
    public Page<LuggageLostRegisterRecordDTO> queryLostRegisterRecordList(
            LuggageLostRegisterRecordQueryDTO recordQueryDTO) {

        // 构造查询条件
        Wrapper<LuggageLostRegistrationRecord> wrapper = new EntityWrapper<>();

        String queryCondition = recordQueryDTO.getQueryCondition();
        if (StringUtils.isNotBlank(queryCondition)) {
            wrapper.like("depositor_name", queryCondition)
                    .or().like("register_record_no", queryCondition);
        }

        if (Objects.nonNull(recordQueryDTO.getStatus())) {
            wrapper.eq("status", recordQueryDTO.getStatus());
        }

        if (Objects.nonNull(recordQueryDTO.getLostTime())) {
            wrapper.between("gmt_create", recordQueryDTO.getLostTime(),
                    LocalDateTime.now());
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("lost_registration_record_id", false);

        // 查询 LuggageStorageRecord 信息
        Page<LuggageLostRegistrationRecord> registrationRecordPage =
                selectPage(recordQueryDTO.getPage(), wrapper);

        return buildLostRegisterRecordDTO(registrationRecordPage);
    }

    /**
     * 获取所有行李遗失登记记录
     *
     * @return
     */
    @Override
    public List<LuggageLostRegistrationRecord> getAllRecords() {
        Wrapper<LuggageLostRegistrationRecord> wrapper = new EntityWrapper<>();

        return selectList(wrapper);
    }

    /**
     * 构造 LuggageLostRegisterRecordDTO 对象
     *
     * @param registrationRecordPage
     * @return
     */
    private Page<LuggageLostRegisterRecordDTO> buildLostRegisterRecordDTO(
            Page<LuggageLostRegistrationRecord> registrationRecordPage) {

        List<LuggageLostRegistrationRecord> records = registrationRecordPage.getRecords();
        List<LuggageLostRegisterRecordDTO> registrationRecords = newArrayList();

        records.forEach(record -> {
            LuggageLostRegisterRecordDTO registerRecordDTO =
                    new LuggageLostRegisterRecordDTO();

            registerRecordDTO.setLostRegistrationRecordId(
                    record.getLostRegistrationRecordId());
            registerRecordDTO.setRegisterRecordNo(record.getRegisterRecordNo());
            registerRecordDTO.setAdminId(record.getAdminId());
            registerRecordDTO.setAdminName(record.getAdminName());
            registerRecordDTO.setDepositorName(record.getDepositorName());
            registerRecordDTO.setDepositorPhone(record.getDepositorPhone());
            registerRecordDTO.setLuggageId(record.getLuggageId());
            registerRecordDTO.setLuggageRecordNo(record.getLuggageRecordNo());
            registerRecordDTO.setLuggageTypeId(record.getLuggageTypeId());
            registerRecordDTO.setStatus(LostRegisterRecordStatusEnum
                    .getDescByCode(record.getStatus()));
            registerRecordDTO.setRemark(record.getRemark());
            registerRecordDTO.setGmtCreate(record.getGmtCreate().toString());
            registerRecordDTO.setGmtModified(record.getGmtModified().toString());

            registrationRecords.add(registerRecordDTO);
        });

        Page<LuggageLostRegisterRecordDTO> page = new Page<>(registrationRecordPage.getCurrent(), registrationRecordPage.getSize());
        page.setRecords(registrationRecords);
        page.setTotal(registrationRecordPage.getTotal());

        return page;
    }
}
