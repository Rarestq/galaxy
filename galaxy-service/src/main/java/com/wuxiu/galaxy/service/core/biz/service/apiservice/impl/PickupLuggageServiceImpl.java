package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.api.common.enums.LuggageStorageStatusEnum;
import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import com.wuxiu.galaxy.api.common.enums.PickupLuggageTypeEnum;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.CommonPickupLuggageDTO;
import com.wuxiu.galaxy.dal.common.dto.MarkLuggageAsLostDTO;
import com.wuxiu.galaxy.dal.common.dto.PickupOverdueLuggageDTO;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.dal.manager.PickupLuggageRecordManager;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.UUIDGenerateUtil;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.PickupLuggageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 行李取件相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/26 09:37
 */
@Slf4j
@Service
public class PickupLuggageServiceImpl implements PickupLuggageService {

    @Autowired
    private PickupLuggageRecordManager pickupLuggageRecordManager;

    @Autowired
    private LuggageStorageRecordManager storageRecordManager;

    /**
     * 行李取件
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    @Override
    public void pickupLuggage(Long luggageId,
                              OperateUserDTO operateUserDTO) {
        log.info("行李取件，行李寄存记录id：{}", luggageId);

        if (Objects.isNull(luggageId)) {
            log.info("参数错误，行李寄存记录id不能为空");
            throw new ParamException("参数错误，行李寄存记录id不能为空");
        }
        // 获取行李寄存记录
        LuggageStorageRecord storageRecord = storageRecordManager.selectById(luggageId);
        CommonPickupLuggageDTO commonPickupLuggageDTO = new CommonPickupLuggageDTO();
        // 正常取件（之前状态为「寄存中」），将其状态更新为「已取件」
        if (Objects.equals(LuggageStorageStatusEnum.DEPOSITING.getCode(),
                storageRecord.getStatus())) {

            commonPickupLuggageDTO = buildPickupLuggageDTO(operateUserDTO, storageRecord);
        }

        pickupLuggageRecordManager.pickupLuggage(commonPickupLuggageDTO);
    }

    /**
     * 构造 CommonPickupLuggageDTO 对象
     *
     * @param operateUserDTO
     * @param storageRecord
     * @return
     */
    private CommonPickupLuggageDTO buildPickupLuggageDTO(OperateUserDTO operateUserDTO,
                                                         LuggageStorageRecord storageRecord) {
        CommonPickupLuggageDTO commonPickupLuggageDTO = new CommonPickupLuggageDTO();

        commonPickupLuggageDTO.setPickupRecordNo(UUIDGenerateUtil.generateUniqueNo(
                CommonConstant.PICKUP_RECORD_NO_PREFIX));
        commonPickupLuggageDTO.setLuggageId(storageRecord.getLuggageId());
        commonPickupLuggageDTO.setAdminId(operateUserDTO.getOperateUserId());
        commonPickupLuggageDTO.setAdminName(operateUserDTO.getName());

        commonPickupLuggageDTO.setPickerName(storageRecord.getDepositorName());
        commonPickupLuggageDTO.setPickerPhone(storageRecord.getDepositorPhone());
        commonPickupLuggageDTO.setPickupType(PickupLuggageTypeEnum.NORMAL.getCode());
        commonPickupLuggageDTO.setPickUpTime(LocalDateTime.now());
        commonPickupLuggageDTO.setStatus(LuggageStorageStatusEnum.PICKED_UP.getCode());

        commonPickupLuggageDTO.setGmtModified(LocalDateTime.now());

        return commonPickupLuggageDTO;
    }


    /**
     * 逾期取件
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    @Override
    public void pickupOverdueLuggage(Long luggageId,
                                     OperateUserDTO operateUserDTO) {
        log.info("逾期取件，行李寄存记录id：{}", luggageId);

        if (Objects.isNull(luggageId)) {
            log.info("参数错误，行李寄存记录id不能为空");
            throw new ParamException("参数错误，行李寄存记录id不能为空");
        }

        LuggageStorageRecord luggageStorageRecord =
                storageRecordManager.selectById(luggageId);
        PickupOverdueLuggageDTO pickupOverdueLuggageDTO = new PickupOverdueLuggageDTO();
        if (Objects.equals(LuggageStorageStatusEnum.OVERDUE.getCode(),
                luggageStorageRecord.getStatus())) {

            pickupOverdueLuggageDTO = buildPickupOverdueLuggageDTO(operateUserDTO,
                    luggageStorageRecord);
        }

        //todo:按照逾期的时长补收费用,并将记录存到 turnover_record 表中

        pickupLuggageRecordManager.pickupOverdueLuggage(pickupOverdueLuggageDTO);
    }

    /**
     * 构造 PickupOverdueLuggageDTO 对象
     *
     * @param operateUserDTO
     * @param luggageStorageRecord
     * @return
     */
    private PickupOverdueLuggageDTO buildPickupOverdueLuggageDTO(
            OperateUserDTO operateUserDTO,
            LuggageStorageRecord luggageStorageRecord) {

        PickupOverdueLuggageDTO overdueLuggageDTO = new PickupOverdueLuggageDTO();
        overdueLuggageDTO.setAdminId(operateUserDTO.getOperateUserId());
        overdueLuggageDTO.setAdminName(operateUserDTO.getName());

        overdueLuggageDTO.setLuggageId(luggageStorageRecord.getLuggageId());
        overdueLuggageDTO.setLuggageRecordNo(luggageStorageRecord.getLuggageRecordNo());

        overdueLuggageDTO.setDepositorName(luggageStorageRecord.getDepositorName());
        overdueLuggageDTO.setDepositorPhone(luggageStorageRecord.getDepositorPhone());

        return overdueLuggageDTO;
    }

    /**
     * 标记为遗失
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    @Override
    public void markLuggageAsLost(Long luggageId,
                                  OperateUserDTO operateUserDTO) {
        log.info("标记为遗失，行李寄存记录id：{}", luggageId);

        if (Objects.isNull(luggageId)) {
            log.info("参数错误，行李寄存记录id不能为空");
            throw new ParamException("参数错误，行李寄存记录id不能为空");
        }

        LuggageStorageRecord storageRecord = storageRecordManager.selectById(luggageId);
        MarkLuggageAsLostDTO markLuggageAsLostDTO = new MarkLuggageAsLostDTO();
        if (Objects.equals(storageRecord.getStatus(),
                LuggageStorageStatusEnum.DEPOSITING.getCode())) {

            markLuggageAsLostDTO = buildMarkLuggageAsLostDTO(storageRecord,
                    operateUserDTO);
        }

        pickupLuggageRecordManager.markLuggageAsLost(markLuggageAsLostDTO);
    }

    /**
     * 查询行李取件记录列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageInfo<PickupLuggageRecordDTO> queryPickupLuggageRecordList(
            PickupLuggageRecordQueryDTO queryDTO) {
        log.info("查询行李取件记录列表, queryDTO:{}", queryDTO);

        // 参数校验
        String pickupLuggageRecordCheck = ValidatorUtil.returnAnyMessageIfError(queryDTO);
        if (StringUtils.isNotEmpty(pickupLuggageRecordCheck)) {
            log.info("查询行李取件记录列表，参数错误，{}", pickupLuggageRecordCheck);
            throw new ParamException(pickupLuggageRecordCheck);
        }

        // 构造查询参数
        com.wuxiu.galaxy.dal.common.dto.PickupLuggageRecordQueryDTO recordQueryDTO =
                new com.wuxiu.galaxy.dal.common.dto.PickupLuggageRecordQueryDTO();
        // 通过行李寄存编号查询行李寄存信息
        LuggageStorageRecord luggageStorageRecord = storageRecordManager
                .selectByLuggageRecordNo(queryDTO.getLuggageRecordNo());

        recordQueryDTO.setPage(PageInfoUtil.convert(queryDTO));
        recordQueryDTO.setLuggageId(luggageStorageRecord.getLuggageId());
        recordQueryDTO.setDepositorName(queryDTO.getDepositorName());
        recordQueryDTO.setPickupTime(queryDTO.getPickupTime());

        // 查询行李取件记录列表信息
        Page<PickupLuggageRecordDTO> recordDTOPage =
                pickupLuggageRecordManager.queryStorageRecordList(recordQueryDTO);
        if (PageInfoUtil.isEmpty(recordDTOPage)) {
            return PageInfoUtil.ofEmptyPage(queryDTO);
        }

        List<PickupLuggageRecordDTO> records = recordDTOPage.getRecords();

        return PageInfoUtil.of(recordDTOPage, records);
    }

    /**
     * 构造 MarkLuggageAsLostDTO 对象
     *
     * @param storageRecord
     * @param operateUserDTO
     * @return
     */
    private MarkLuggageAsLostDTO buildMarkLuggageAsLostDTO(
            LuggageStorageRecord storageRecord, OperateUserDTO operateUserDTO) {
        MarkLuggageAsLostDTO markLuggageAsLostDTO = new MarkLuggageAsLostDTO();

        markLuggageAsLostDTO.setRegisterRecordNo(UUIDGenerateUtil.generateUniqueNo(
                CommonConstant.REGISTER_RECORD_NO_PREFIX));
        markLuggageAsLostDTO.setLuggageId(storageRecord.getLuggageId());
        markLuggageAsLostDTO.setLuggageRecordNo(storageRecord.getLuggageRecordNo());

        markLuggageAsLostDTO.setAdminId(operateUserDTO.getOperateUserId());
        markLuggageAsLostDTO.setAdminName(operateUserDTO.getName());

        markLuggageAsLostDTO.setDepositorName(storageRecord.getDepositorName());
        markLuggageAsLostDTO.setDepositorPhone(storageRecord.getDepositorPhone());

        markLuggageAsLostDTO.setLuggageTypeId(storageRecord.getLuggageTypeId());
        markLuggageAsLostDTO.setLuggageTypeDesc(
                LuggageTypeEnum.getDescByCode(storageRecord.getLuggageTypeId()));

        return markLuggageAsLostDTO;
    }
}