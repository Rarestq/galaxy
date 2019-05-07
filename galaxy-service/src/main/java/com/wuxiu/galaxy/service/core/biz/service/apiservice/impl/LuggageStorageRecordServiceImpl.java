package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.common.util.DateUtil;
import com.wuxiu.galaxy.api.dto.*;
import com.wuxiu.galaxy.dal.common.dto.LuggageFeeCalculationRuleDTO;
import com.wuxiu.galaxy.dal.manager.LuggageStorageRecordManager;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.UUIDGenerateUtil;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LuggageStorageRecordService;
import com.wuxiu.galaxy.service.core.biz.strategy.LuggageFeeMeter;
import com.wuxiu.galaxy.service.core.biz.strategy.LuggageFeeMeterFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 行李寄存相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/22 17:20
 */
@Slf4j
@Service
public class LuggageStorageRecordServiceImpl implements LuggageStorageRecordService {

    @Autowired
    private LuggageStorageRecordManager storageRecordManager;

    @Autowired
    private LuggageFeeMeterFactory feeMeterFactory;

    /**
     * 新增行李寄存记录
     *
     * @param storageRecordDTO
     * @return
     */
    @Override
    public Long insertLuggageStorageRecord(NewLuggageStorageRecordDTO storageRecordDTO,
                                           OperateUserDTO operateUserDTO) {
        log.info("新增行李寄存记录, storageRecordDTO:{}", storageRecordDTO);

        // 参数校验
        validateStorageRecordParams(storageRecordDTO);

        // 构造 NewLuggageStorageRecordDTO 对象
        com.wuxiu.galaxy.dal.common.dto.NewLuggageStorageRecordDTO
                newLuggageStorageRecordDTO =
                buildNewLuggageStorageRecordDTO(storageRecordDTO, operateUserDTO);

        // 获取寄存行李的类型
        Long luggageTypeId = storageRecordDTO.getLuggageTypeId();

        // 构造计费规则参数
        LuggageFeeCalculationRuleDTO calculationRuleDTO = new LuggageFeeCalculationRuleDTO();
        calculationRuleDTO.setCalculateRuleId(storageRecordDTO.getCalculateRuleId());
        calculationRuleDTO.setLuggageTypeId(luggageTypeId);
        int calculateHours = (int) DateUtil.calculateDate2Hours(
                LocalDateTime.now(), storageRecordDTO.getStorageEndTime());
        calculationRuleDTO.setLuggageStorageHours(calculateHours);
        calculationRuleDTO.setGmtModified(LocalDateTime.now());
        // 获取计价器
        LuggageFeeMeter luggageFeeMeter =
                feeMeterFactory.getLuggageFeeMeter(calculationRuleDTO);
        // 计算寄存所需费用
        LuggageChargeCalculationResultDTO resultDTO =
                luggageFeeMeter.calculate(calculateHours);
        newLuggageStorageRecordDTO.setFeeValue(resultDTO.getFeeValue());
        newLuggageStorageRecordDTO.setFeeCalculationProcessDesc(
                resultDTO.getFeeCalculationProcessDesc());

        return storageRecordManager.insertLuggageStorageRecord(newLuggageStorageRecordDTO);
    }

    /**
     * 构造 NewLuggageStorageRecordDTO 对象
     *
     * @param storageRecordDTO
     * @return
     */
    private com.wuxiu.galaxy.dal.common.dto.NewLuggageStorageRecordDTO
    buildNewLuggageStorageRecordDTO(NewLuggageStorageRecordDTO storageRecordDTO,
                                    OperateUserDTO operateUserDTO) {

        com.wuxiu.galaxy.dal.common.dto.NewLuggageStorageRecordDTO recordDTO =
                new com.wuxiu.galaxy.dal.common.dto.NewLuggageStorageRecordDTO();

        recordDTO.setLuggageRecordNo(UUIDGenerateUtil.generateUniqueNo(
                CommonConstant.LUGGAGE_STORAGE_RECORD_NO_PREFIX));
        recordDTO.setCalculateRuleId(storageRecordDTO.getCalculateRuleId());
        recordDTO.setLuggageTypeId(storageRecordDTO.getLuggageTypeId());

        recordDTO.setAdminId(operateUserDTO.getOperateUserId());
        recordDTO.setAdminName(operateUserDTO.getName());
        recordDTO.setAdminPhone(operateUserDTO.getOperateUserPhone());

        recordDTO.setDepositorName(storageRecordDTO.getDepositorName());
        recordDTO.setDepositorPhone(storageRecordDTO.getDepositorPhone());

        recordDTO.setStorageStartTime(LocalDateTime.now());
        recordDTO.setStorageEndTime(storageRecordDTO.getStorageEndTime());
        if (StringUtils.isNotEmpty(storageRecordDTO.getRemark())) {
            recordDTO.setRemark(storageRecordDTO.getRemark());
        }

        return recordDTO;
    }


    /**
     * 校验新增行李寄存记录的参数
     *
     * @param storageRecordDTO
     */
    private void validateStorageRecordParams(NewLuggageStorageRecordDTO storageRecordDTO) {

        String storageRecordCheck = ValidatorUtil.returnAnyMessageIfError(storageRecordDTO);
        if (StringUtils.isNotEmpty(storageRecordCheck)) {
            log.info("新增行李寄存记录,参数错误：{}", storageRecordCheck);
            throw new ParamException(storageRecordCheck);
        }

        // 寄存人姓名不能为空字符串
        String depositorName = storageRecordDTO.getDepositorName().trim();
        if (StringUtils.isEmpty(depositorName)) {
            log.warn("寄存人姓名不能为空");
            throw new ParamException("寄存人姓名不能为空");
        }

        // 校验电话号码
        boolean isCorrectPhone = ValidatorUtil.validatePhone(
                storageRecordDTO.getDepositorPhone());
        if (!isCorrectPhone) {
            log.warn("手机号码格式不正确，请重新输入");
            throw new ParamException("手机号码格式不正确，请重新输入");
        }

    }

    /**
     * 查询行李寄存记录
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageInfo<LuggageStorageInfoDTO> queryStorageRecordList(
            LuggageStorageRecordQueryDTO queryDTO) {
        log.info("查询行李寄存记录, queryDTO:{}", queryDTO);

        // 参数校验
        String storageRecordCheck = ValidatorUtil.returnAnyMessageIfError(queryDTO);
        if (StringUtils.isNotEmpty(storageRecordCheck)) {
            log.info("查询行李寄存记录，参数错误，{}", storageRecordCheck);
            throw new ParamException(storageRecordCheck);
        }

        // 构造查询参数
        com.wuxiu.galaxy.dal.common.dto.LuggageStorageRecordQueryDTO recordQueryDTO =
                new com.wuxiu.galaxy.dal.common.dto.LuggageStorageRecordQueryDTO();
        recordQueryDTO.setPage(PageInfoUtil.convert(queryDTO));

        recordQueryDTO.setLuggageId(queryDTO.getLuggageId());
        recordQueryDTO.setDepositorName(queryDTO.getDepositorName());
        recordQueryDTO.setDepositorPhone(queryDTO.getDepositorPhone());
        recordQueryDTO.setLuggageRecordNo(queryDTO.getLuggageRecordNo());
//        if (StringUtils.isNotEmpty(queryDTO.getStorageTimeRange().trim())) {
//            String storageTimeRange = queryDTO.getStorageTimeRange();
//            String[] timeRange = storageTimeRange.split(CommonConstant.COMMA);
//            recordQueryDTO.setStorageEndTimeFrom(LocalDateTime.parse(timeRange[0],
//                    DateTimeFormatter.ofPattern(CommonConstant.TIME_PATTERN)));
//            recordQueryDTO.setStorageEndTimeTo(LocalDateTime.parse(timeRange[0],
//                    DateTimeFormatter.ofPattern(CommonConstant.TIME_PATTERN)));
//        }

        // 查询行李寄存列表信息
        Page<LuggageStorageInfoDTO> storageRecordInfoPage =
                storageRecordManager.queryStorageRecordList(recordQueryDTO);
        if (PageInfoUtil.isEmpty(storageRecordInfoPage)) {
            return PageInfoUtil.ofEmptyPage(queryDTO);
        }

        // 在寄存结束时间前 15 min 发送短信，判断是否已到寄存结束时间，是就发送自动创建逾期记录事件
        //notifyDepositorBySMS();

        List<LuggageStorageInfoDTO> records = storageRecordInfoPage.getRecords();

        return PageInfoUtil.of(storageRecordInfoPage, records);
    }

}
