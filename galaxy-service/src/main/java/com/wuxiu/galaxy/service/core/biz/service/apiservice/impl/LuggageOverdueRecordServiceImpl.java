package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordQueryDTO;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.api.dto.SaveLuggageOverdueRecordDTO;
import com.wuxiu.galaxy.dal.common.utils.PageInfoUtil;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.manager.LuggageOverdueRecordManager;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.service.core.base.utils.UUIDGenerateUtil;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LuggageOverdueRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行李逾期未取清理相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 20:30
 */
@Slf4j
@Service
public class LuggageOverdueRecordServiceImpl implements LuggageOverdueRecordService {

    @Autowired
    private LuggageOverdueRecordManager overdueRecordManager;

    @Autowired
    private LuggageStorageRecordManager storageRecordManager;

    /**
     * 创建行李逾期记录
     *
     * @param overdueRecordDTO
     * @param operateUserDTO
     * @return
     */
    @Override
    public Long createLuggageOverdueRecord(SaveLuggageOverdueRecordDTO overdueRecordDTO,
                                           OperateUserDTO operateUserDTO) {
        log.info("创建行李逾期记录, overdueRecordDTO:{}", overdueRecordDTO);

        // 参数校验
        String overdueRecordCheck = ValidatorUtil.returnAnyMessageIfError(overdueRecordDTO);
        if (StringUtils.isNotEmpty(overdueRecordCheck)) {
            log.info("创建行李逾期记录,参数错误：{}", overdueRecordCheck);
            throw new ParamException(overdueRecordCheck);
        }

        String userCheck = ValidatorUtil.returnAnyMessageIfError(operateUserDTO);
        if (StringUtils.isNotEmpty(userCheck)) {
            log.info("创建行李逾期记录,参数错误：{}", userCheck);
            throw new ParamException(userCheck);
        }

        // 查询行李寄存记录
        LuggageStorageRecord luggageStorageRecord =
                storageRecordManager.selectById(overdueRecordDTO.getLuggageId());

        // 构造 NewLuggageStorageRecordDTO 对象
        com.wuxiu.galaxy.dal.common.dto.SaveLuggageOverdueRecordDTO
                saveLuggageOverdueRecordDTO =
                buildLuggageOverdueRecordDTO(overdueRecordDTO, operateUserDTO,
                        luggageStorageRecord);

        return overdueRecordManager.createLuggageOverdueRecord
                (saveLuggageOverdueRecordDTO);
    }

    /**
     * 查询行李逾期未取清理记录列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageInfo<LuggageOverdueRecordInfoDTO> queryOverdueRecordList(
            LuggageOverdueRecordQueryDTO queryDTO) {

        log.info("查询行李逾期未取清理记录列表, queryDTO:{}", queryDTO);

        // 参数校验
        String overdueRecordCheck = ValidatorUtil.returnAnyMessageIfError(queryDTO);
        if (StringUtils.isNotEmpty(overdueRecordCheck)) {
            log.info("查询行李逾期未取清理记录列表，参数错误，{}", overdueRecordCheck);
            throw new ParamException(overdueRecordCheck);
        }

        // 构造查询参数
        com.wuxiu.galaxy.dal.common.dto.LuggageOverdueRecordQueryDTO recordQueryDTO =
                new com.wuxiu.galaxy.dal.common.dto.LuggageOverdueRecordQueryDTO();
        recordQueryDTO.setPage(PageInfoUtil.convert(queryDTO));

        recordQueryDTO.setLuggageRecordNo(queryDTO.getLuggageRecordNo());
        recordQueryDTO.setDepositorName(queryDTO.getDepositorName());
        recordQueryDTO.setLuggageRecordNo(queryDTO.getLuggageRecordNo());
        recordQueryDTO.setStatus(queryDTO.getStatus());

        // 查询行李逾期未取清理记录列表
        Page<LuggageOverdueRecordInfoDTO> overdueRecordInfoDTOPage =
                overdueRecordManager.queryOverdueRecordList(recordQueryDTO);

        if (PageInfoUtil.isEmpty(overdueRecordInfoDTOPage)) {
            return PageInfoUtil.ofEmptyPage(queryDTO);
        }

        List<LuggageOverdueRecordInfoDTO> records =
                overdueRecordInfoDTOPage.getRecords();

        return PageInfoUtil.of(overdueRecordInfoDTOPage, records);
    }

    /**
     * 构造 dal 层的 SaveLuggageOverdueRecordDTO 对象
     *
     * @param overdueRecordDTO
     * @param operateUserDTO
     * @return
     */
    private com.wuxiu.galaxy.dal.common.dto.SaveLuggageOverdueRecordDTO
    buildLuggageOverdueRecordDTO(SaveLuggageOverdueRecordDTO overdueRecordDTO,
                                 OperateUserDTO operateUserDTO,
                                 LuggageStorageRecord luggageStorageRecord) {

        com.wuxiu.galaxy.dal.common.dto.SaveLuggageOverdueRecordDTO recordDTO =
                new com.wuxiu.galaxy.dal.common.dto.SaveLuggageOverdueRecordDTO();

        recordDTO.setOverdueRecordNo(UUIDGenerateUtil.generateUniqueNo(
                CommonConstant.OVERDUE_RECORD_NO_PREFIX));
        recordDTO.setLuggageId(overdueRecordDTO.getLuggageId());
        recordDTO.setLuggageRecordNo(luggageStorageRecord.getLuggageRecordNo());
        recordDTO.setRemark(overdueRecordDTO.getRemark());
        recordDTO.setStatus(overdueRecordDTO.getStatus());

        recordDTO.setAdminId(operateUserDTO.getOperateUserId());
        recordDTO.setAdminName(operateUserDTO.getName());

        recordDTO.setDepositorName(luggageStorageRecord.getDepositorName());
        recordDTO.setDepositorPhone(luggageStorageRecord.getDepositorPhone());

        return recordDTO;
    }

}
