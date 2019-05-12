package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordInfoDTO;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.LuggageLostCompensateDTO;
import com.wuxiu.galaxy.dal.common.utils.PageInfoUtil;
import com.wuxiu.galaxy.dal.domain.LuggageLostRegistrationRecord;
import com.wuxiu.galaxy.dal.domain.TurnoverRecord;
import com.wuxiu.galaxy.dal.manager.LuggageLostCompensationRecordManager;
import com.wuxiu.galaxy.dal.manager.LuggageLostRegistrationRecordManager;
import com.wuxiu.galaxy.dal.manager.TurnoverRecordManager;
import com.wuxiu.galaxy.service.core.base.utils.UUIDGenerateUtil;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LuggageLostCompensateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 行李遗失赔偿相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/28 10:45
 */
@Slf4j
@Service
public class LuggageLostCompensateServiceImpl implements LuggageLostCompensateService {

    @Autowired
    private LuggageLostCompensationRecordManager compensationRecordManager;

    @Autowired
    private LuggageLostRegistrationRecordManager registrationRecordManager;

    @Autowired
    private TurnoverRecordManager turnoverRecordManager;

    /**
     * 查询行李遗失赔偿登记记录列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageInfo<LostCompensateRecordInfoDTO> queryLostCompensateRecordList(
            LostCompensateRecordQueryDTO queryDTO) {
        log.info("查询行李遗失赔偿登记记录, queryDTO:{}", queryDTO);

        // 参数校验
        String compensateRecordCheck = ValidatorUtil.returnAnyMessageIfError(queryDTO);
        if (StringUtils.isNotEmpty(compensateRecordCheck)) {
            log.info("查询行李遗失赔偿登记记录，参数错误，{}", compensateRecordCheck);
            throw new ParamException(compensateRecordCheck);
        }

        // 构造查询参数
        com.wuxiu.galaxy.dal.common.dto.LostCompensateRecordQueryDTO recordQueryDTO =
                new com.wuxiu.galaxy.dal.common.dto.LostCompensateRecordQueryDTO();
        recordQueryDTO.setPage(PageInfoUtil.convert(queryDTO));

        recordQueryDTO.setLuggageLostCompensationRecordId(
                queryDTO.getLuggageLostCompensationRecordId());
        recordQueryDTO.setDepositorName(queryDTO.getDepositorName());
        recordQueryDTO.setAdminName(queryDTO.getAdminName());
        recordQueryDTO.setLuggageType(queryDTO.getLuggageType());

        // 查询行李遗失赔偿登记记录
        Page<LostCompensateRecordInfoDTO> compensateRecordDTOPage =
                compensationRecordManager.queryLostCompensateRecordList(recordQueryDTO);
        if (PageInfoUtil.isEmpty(compensateRecordDTOPage)) {
            return PageInfoUtil.ofEmptyPage(queryDTO);
        }

        List<LostCompensateRecordInfoDTO> records = compensateRecordDTOPage.getRecords();

        return PageInfoUtil.of(compensateRecordDTOPage, records);
    }

    /**
     * 遗失行李赔偿
     *
     * @param lostRegistrationRecordId
     * @param operateUser
     * @return
     */
    @Override
    public Long compensateByLuggageType(Long lostRegistrationRecordId,
                                        AdminInfoDTO operateUser) {
        log.info("遗失行李赔偿, lostRegistrationRecordId:{}, operateUser:{}",
                lostRegistrationRecordId, operateUser);
        // 参数校验
        if (Objects.isNull(lostRegistrationRecordId)) {
            log.info("遗失行李赔偿, lostRegistrationRecordId 不能为空");
            throw new ParamException("遗失行李赔偿, lostRegistrationRecordId 不能为空");
        }

        String userCheck = ValidatorUtil.returnAnyMessageIfError(operateUser);
        if (StringUtils.isNotEmpty(userCheck)) {
            log.info("遗失行李赔偿,参数错误：{}", userCheck);
            throw new ParamException(userCheck);
        }

        // 查询行李遗失登记记录
        LuggageLostRegistrationRecord registrationRecord = registrationRecordManager
                .selectById(lostRegistrationRecordId);

        // 计算赔偿所需费用
        String compensateFee = calculateCompensateFee(registrationRecord
                .getLuggageTypeId(), registrationRecord.getLuggageId());

        // 构造 LuggageLostCompensateDTO 对象
        LuggageLostCompensateDTO lostCompensateDTO =
                buildLuggageLostCompensateDTO(registrationRecord, operateUser,
                        compensateFee);

        return compensationRecordManager.compensateByLuggageType(lostCompensateDTO);
    }

    /**
     * 构造 LuggageLostCompensateDTO 对象
     *
     * @param registrationRecord
     * @param operateUser
     * @return
     */
    private LuggageLostCompensateDTO buildLuggageLostCompensateDTO(
            LuggageLostRegistrationRecord registrationRecord,
            AdminInfoDTO operateUser,
            String compensateFee) {

        Long luggageTypeId = registrationRecord.getLuggageTypeId();
        LuggageLostCompensateDTO lostCompensateDTO = new LuggageLostCompensateDTO();

        lostCompensateDTO.setAdminId(operateUser.getAdminId());
        lostCompensateDTO.setAdminName(operateUser.getAdminName());
        lostCompensateDTO.setDepositorName(registrationRecord.getDepositorName());
        lostCompensateDTO.setDepositorPhone(registrationRecord.getDepositorPhone());
        lostCompensateDTO.setLuggageId(registrationRecord.getLuggageId());
        lostCompensateDTO.setLuggageType(luggageTypeId);
        lostCompensateDTO.setLostRegistrationRecordId(registrationRecord
                .getLostRegistrationRecordId());
        lostCompensateDTO.setLostCompensateRecordNo(UUIDGenerateUtil.generateUniqueNo(
                CommonConstant.LUGGAGE_LOST_COMPENSATE_NO_PREFIX));

        lostCompensateDTO.setCompensationFee(compensateFee);

        String luggageTypeDesc = LuggageTypeEnum.getDescByCode(luggageTypeId);
        // 备注，什么行李赔偿了多少钱
        lostCompensateDTO.setRemark("赔偿的行李类型为：" + luggageTypeDesc + "，赔偿金额："
                + compensateFee + "元");

        return lostCompensateDTO;
    }

    /**
     * 计算赔偿遗失的行李所需的费用
     *
     * @param luggageTypeId 行李类型id
     * @return 赔偿的费用
     */
    private String calculateCompensateFee(long luggageTypeId, Long luggageId) {
        // compensateFee = storageFee X multiple(depends on luggageType)
        // 根据行李寄存记录id查询其对应的营业额记录信息
        TurnoverRecord turnoverRecord = turnoverRecordManager
                .getTurnoverRecordByLuggageId(luggageId);
        BigDecimal storageFee = new BigDecimal(turnoverRecord.getFee());

        String compensateFee = StringUtils.EMPTY;
        if (Objects.equals(LuggageTypeEnum.COMMON_LUGGAGE_TYPE.getCode(), luggageTypeId)) {
            compensateFee = storageFee.multiply(new BigDecimal(2)).toString();
        } else if (Objects.equals(LuggageTypeEnum.FRAGILE_LUGGAGE_TYPE.getCode(),
                luggageTypeId)) {
            compensateFee = storageFee.multiply(new BigDecimal(3)).toString();
        } else if (Objects.equals(LuggageTypeEnum.VALUABLE_LUGGAGE_TYPE.getCode(),
                luggageTypeId)) {
            compensateFee = storageFee.multiply(new BigDecimal(5)).toString();
        }

        return compensateFee;
    }
}
