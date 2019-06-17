package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.api.common.enums.LuggageCabinetStatusEnum;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.CabinetInfoDTO;
import com.wuxiu.galaxy.api.dto.CabinetQueryDTO;
import com.wuxiu.galaxy.dal.common.utils.PageInfoUtil;
import com.wuxiu.galaxy.dal.domain.LuggageCabinet;
import com.wuxiu.galaxy.dal.manager.LuggageCabinetManager;
import com.wuxiu.galaxy.service.core.base.utils.StreamUtil;
import com.wuxiu.galaxy.service.core.base.utils.UUIDGenerateUtil;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.CabinetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 行李寄存相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:55
 */
@Slf4j
@Service
public class CabinetServiceImpl implements CabinetService {

    @Autowired
    private LuggageCabinetManager cabinetManager;

    /**
     * 获取行李寄存柜信息列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageInfo<CabinetInfoDTO> queryCabinetInfoList(CabinetQueryDTO queryDTO) {
        log.info("获取行李寄存柜信息列表，queryDTO:{}", queryDTO);
        // 参数校验
        String cabinetInfoCheck = ValidatorUtil.returnAnyMessageIfError(queryDTO);
        if (StringUtils.isNotEmpty(cabinetInfoCheck)) {
            log.info("获取行李寄存柜信息列表，参数错误：{}", cabinetInfoCheck);
            throw new ParamException(cabinetInfoCheck);
        }

        com.wuxiu.galaxy.dal.common.dto.CabinetQueryDTO cabinetQueryDTO =
                new com.wuxiu.galaxy.dal.common.dto.CabinetQueryDTO();
        cabinetQueryDTO.setPage(PageInfoUtil.convert(queryDTO));
        cabinetQueryDTO.setLuggageCabinetId(queryDTO.getLuggageCabinetId());
        cabinetQueryDTO.setLuggageCabinetNo(queryDTO.getLuggageCabinetNo());
        cabinetQueryDTO.setStatus(queryDTO.getStatus());

        // 查询行李寄存柜信息列表
        Page<LuggageCabinet> cabinetPage = cabinetManager
                .queryCabinetInfoList(cabinetQueryDTO);
        if (PageInfoUtil.isEmpty(cabinetPage)) {
            return PageInfoUtil.ofEmptyPage(queryDTO);
        }

        List<LuggageCabinet> cabinets = cabinetPage.getRecords();

        List<CabinetInfoDTO> cabinetInfoDTOS =
                StreamUtil.convert(cabinets, this::buildCabinetInfoDTO);

        return PageInfoUtil.of(cabinetPage, cabinetInfoDTOS);
    }

    /**
     * 获取所有「空闲」状态的寄存柜信息
     *
     * @return
     */
    @Override
    public List<LuggageCabinet> getAllCabinets() {
        return cabinetManager.getAllCabinets();
    }

    /**
     * 新增寄存柜信息
     *
     * @param luggageCabinet
     * @return
     */
    @Override
    public Long addLuggageCabinet(LuggageCabinet luggageCabinet) {
        log.info("新增寄存柜信息，luggageCabinet:{}", luggageCabinet);

        LuggageCabinet cabinet = new LuggageCabinet();
        cabinet.setLuggageCabinetNo(UUIDGenerateUtil
                .generateCabinetNo(CommonConstant.LUGGAGE_CABINET_NO_PREFIX));
        cabinet.setStatus(LuggageCabinetStatusEnum.FREE.getCode());
        cabinet.setGmtCreate(LocalDateTime.now());
        cabinet.setGmtModified(LocalDateTime.now());

        return cabinetManager.addLuggageCabinet(cabinet);
    }

    /**
     * 维修寄存柜
     *
     * @param cabinetIds
     * @return
     */
    @Override
    public void repairCabinets(List<Long> cabinetIds) {
        if (CollectionUtils.isEmpty(cabinetIds)) {
            log.info("维修寄存柜失败，cabinetIds 不能为空");
            return;
        }
        cabinetManager.repairCabinets(cabinetIds);
    }

    /**
     * 构造 CabinetInfoDTO 对象
     *
     * @param luggageCabinet
     * @return
     */
    private CabinetInfoDTO buildCabinetInfoDTO(
            LuggageCabinet luggageCabinet) {

        CabinetInfoDTO cabinetInfoDTO = new CabinetInfoDTO();

        cabinetInfoDTO.setLuggageCabinetId(luggageCabinet.getLuggageCabinetId());
        cabinetInfoDTO.setLuggageCabinetNo(luggageCabinet.getLuggageCabinetNo());
        cabinetInfoDTO.setStatus(LuggageCabinetStatusEnum.getDescByCode(
                luggageCabinet.getStatus()));

        return cabinetInfoDTO;
    }
}
