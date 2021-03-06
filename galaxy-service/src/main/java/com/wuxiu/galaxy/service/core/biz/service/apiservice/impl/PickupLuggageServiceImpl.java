package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.api.common.enums.LuggageCabinetStatusEnum;
import com.wuxiu.galaxy.api.common.enums.LuggageStorageStatusEnum;
import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import com.wuxiu.galaxy.api.common.enums.PickupLuggageTypeEnum;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.common.util.DateUtil;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageChargeCalculationResultDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.CommonPickupLuggageDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageFeeCalculationRuleDTO;
import com.wuxiu.galaxy.dal.common.dto.MarkLuggageAsLostDTO;
import com.wuxiu.galaxy.dal.common.dto.PickupOverdueLuggageDTO;
import com.wuxiu.galaxy.dal.common.utils.PageInfoUtil;
import com.wuxiu.galaxy.dal.domain.LuggageCabinet;
import com.wuxiu.galaxy.dal.domain.LuggageStorageRecord;
import com.wuxiu.galaxy.dal.domain.TurnoverRecord;
import com.wuxiu.galaxy.dal.manager.LuggageCabinetManager;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.dal.manager.PickupLuggageRecordManager;
import com.wuxiu.galaxy.dal.manager.TurnoverRecordManager;
import com.wuxiu.galaxy.service.core.base.utils.UUIDGenerateUtil;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.PickupLuggageService;
import com.wuxiu.galaxy.service.core.biz.service.smsservice.FinishPickupEventSmsService;
import com.wuxiu.galaxy.service.core.biz.strategy.LuggageFeeMeter;
import com.wuxiu.galaxy.service.core.biz.strategy.LuggageFeeMeterFactory;
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

    @Autowired
    private TurnoverRecordManager turnoverRecordManager;

    @Autowired
    private LuggageCabinetManager cabinetManager;

    @Autowired
    LuggageFeeMeterFactory meterFactory;

    @Autowired
    private FinishPickupEventSmsService finishPickupEventSmsService;

    /**
     * 行李取件
     *
     * @param luggageId
     * @param operateUserDTO
     * @return
     */
    @Override
    public void pickupLuggage(Long luggageId,
                              AdminInfoDTO operateUserDTO) {
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

            commonPickupLuggageDTO = buildPickupLuggageDTO(operateUserDTO,
                    storageRecord);
        }

        // 取件后，将对应的寄存柜的状态改为「空闲」
        LuggageCabinet luggageCabinet = new LuggageCabinet();
        luggageCabinet.setLuggageCabinetId(storageRecord.getCabinetId());
        luggageCabinet.setGmtModified(LocalDateTime.now());
        luggageCabinet.setStatus(LuggageCabinetStatusEnum.FREE.getCode());
        cabinetManager.updateById(luggageCabinet);

        pickupLuggageRecordManager.pickupLuggage(commonPickupLuggageDTO);

        // 取件成功后，发送短信
        finishPickupEventSmsService.sendFinishPickupSms(luggageId);

    }

    /**
     * 构造 CommonPickupLuggageDTO 对象
     *
     * @param operateUserDTO
     * @param storageRecord
     * @return
     */
    private CommonPickupLuggageDTO buildPickupLuggageDTO(AdminInfoDTO operateUserDTO,
                                                         LuggageStorageRecord storageRecord) {
        CommonPickupLuggageDTO commonPickupLuggageDTO = new CommonPickupLuggageDTO();

        commonPickupLuggageDTO.setPickupRecordNo(UUIDGenerateUtil.generateUniqueNo(
                CommonConstant.PICKUP_RECORD_NO_PREFIX));
        commonPickupLuggageDTO.setLuggageId(storageRecord.getLuggageId());
        commonPickupLuggageDTO.setAdminId(operateUserDTO.getAdminId());
        commonPickupLuggageDTO.setAdminName(operateUserDTO.getAdminName());

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
                                     AdminInfoDTO operateUserDTO) {
        log.info("逾期取件，行李寄存记录id：{}", luggageId);

        if (Objects.isNull(luggageId)) {
            log.info("参数错误，行李寄存记录id不能为空");
            throw new ParamException("参数错误，行李寄存记录id不能为空");
        }

        // 查询行李寄存记录信息
        LuggageStorageRecord luggageStorageRecord =
                storageRecordManager.selectById(luggageId);

        // 根据行李寄存记录id查询其对应的营业额记录信息
        TurnoverRecord turnoverRecord = turnoverRecordManager
                .getTurnoverRecordByLuggageId(luggageId);

        // 逾期时间 (因为通过 convertDateFormat() 方法转完后的格式是 12 小时制的)
        int overdueHours = 12 + (int) DateUtil.calculateDate2Hours(
                luggageStorageRecord.getStorageEndTime(), DateUtil
                        .convertDateFormat(LocalDateTime.now()));

        // 构造计费规则参数
        LuggageFeeCalculationRuleDTO ruleDTO = new LuggageFeeCalculationRuleDTO();
        ruleDTO.setCalculateRuleId(turnoverRecord.getCalculationRuleId());
        ruleDTO.setLuggageTypeId(luggageStorageRecord.getLuggageTypeId());
        ruleDTO.setGmtModified(luggageStorageRecord.getGmtModified());
        ruleDTO.setLuggageStorageHours(overdueHours);

        // 计算逾期费用（跟寄存时计算寄存所需费用的规则一样）
        LuggageFeeMeter luggageFeeMeter = meterFactory.getLuggageFeeMeter(ruleDTO);
        LuggageChargeCalculationResultDTO calculationResultDTO =
                luggageFeeMeter.calculate(overdueHours);

        if (Objects.equals(LuggageStorageStatusEnum.OVERDUE.getCode(),
                luggageStorageRecord.getStatus())) {

            PickupOverdueLuggageDTO pickupOverdueLuggageDTO =
                    buildPickupOverdueLuggageDTO(operateUserDTO,
                            luggageStorageRecord, calculationResultDTO,
                            turnoverRecord);

            pickupLuggageRecordManager.pickupOverdueLuggage(pickupOverdueLuggageDTO);

            // 取件成功后，发送短信
            finishPickupEventSmsService.sendFinishPickupSms(luggageId);

            // 取件后，将对应的寄存柜的状态改为「空闲」
            LuggageCabinet luggageCabinet = new LuggageCabinet();
            luggageCabinet.setLuggageCabinetId(luggageStorageRecord.getCabinetId());
            luggageCabinet.setGmtModified(LocalDateTime.now());
            luggageCabinet.setStatus(LuggageCabinetStatusEnum.FREE.getCode());
            cabinetManager.updateById(luggageCabinet);
        }

        log.warn("当前行李寄存状态有误，不能进行取件， status:{}",
                luggageStorageRecord.getStatus());
    }

    /**
     * 构造 PickupOverdueLuggageDTO 对象
     *
     * @param operateUserDTO
     * @param luggageStorageRecord
     * @return
     */
    private PickupOverdueLuggageDTO buildPickupOverdueLuggageDTO(
            AdminInfoDTO operateUserDTO,
            LuggageStorageRecord luggageStorageRecord,
            LuggageChargeCalculationResultDTO calculationResultDTO,
            TurnoverRecord turnoverRecord) {

        PickupOverdueLuggageDTO overdueLuggageDTO = new PickupOverdueLuggageDTO();
        overdueLuggageDTO.setPickupRecordNo(UUIDGenerateUtil.generateUniqueNo(
                CommonConstant.PICKUP_RECORD_NO_PREFIX));
        overdueLuggageDTO.setAdminId(operateUserDTO.getAdminId());
        overdueLuggageDTO.setAdminName(operateUserDTO.getAdminName());

        overdueLuggageDTO.setLuggageId(luggageStorageRecord.getLuggageId());
        overdueLuggageDTO.setLuggageRecordNo(luggageStorageRecord.getLuggageRecordNo());

        overdueLuggageDTO.setDepositorName(luggageStorageRecord.getDepositorName());
        overdueLuggageDTO.setDepositorPhone(luggageStorageRecord.getDepositorPhone());

        overdueLuggageDTO.setCalculateRuleId(turnoverRecord.getCalculationRuleId());
        // 设置逾期费用的计算结果
        overdueLuggageDTO.setCalculationUnitsId(calculationResultDTO
                .getCalculationUnitsId());
        overdueLuggageDTO.setFeeValue(calculationResultDTO.getFeeValue());
        overdueLuggageDTO.setFeeCalculationProcessDesc(calculationResultDTO
                .getFeeCalculationProcessDesc());

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
                                  AdminInfoDTO operateUserDTO) {
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

        recordQueryDTO.setPage(PageInfoUtil.convert(queryDTO));
        recordQueryDTO.setDepositorName(queryDTO.getDepositorName());
        if (StringUtils.isNotEmpty(queryDTO.getPickupTime())) {
            recordQueryDTO.setPickupTime(DateUtil.string2LocalDateTime(queryDTO
                    .getPickupTime()));
        }

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
            LuggageStorageRecord storageRecord, AdminInfoDTO operateUserDTO) {
        MarkLuggageAsLostDTO markLuggageAsLostDTO = new MarkLuggageAsLostDTO();

        markLuggageAsLostDTO.setRegisterRecordNo(UUIDGenerateUtil.generateUniqueNo(
                CommonConstant.REGISTER_RECORD_NO_PREFIX));
        markLuggageAsLostDTO.setLuggageId(storageRecord.getLuggageId());
        markLuggageAsLostDTO.setLuggageRecordNo(storageRecord.getLuggageRecordNo());

        markLuggageAsLostDTO.setAdminId(operateUserDTO.getAdminId());
        markLuggageAsLostDTO.setAdminName(operateUserDTO.getAdminName());

        markLuggageAsLostDTO.setDepositorName(storageRecord.getDepositorName());
        markLuggageAsLostDTO.setDepositorPhone(storageRecord.getDepositorPhone());

        markLuggageAsLostDTO.setLuggageTypeId(storageRecord.getLuggageTypeId());
        markLuggageAsLostDTO.setLuggageTypeDesc(
                LuggageTypeEnum.getDescByCode(storageRecord.getLuggageTypeId()));

        return markLuggageAsLostDTO;
    }
}
