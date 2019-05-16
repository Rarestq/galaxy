package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.CabinetInfoDTO;
import com.wuxiu.galaxy.api.dto.CabinetQueryDTO;

import java.util.List;

/**
 * 行李寄存柜相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:52
 */
public interface CabinetFacade {

    /**
     * 获取行李寄存柜信息列表
     *
     * @param queryDTO
     * @return
     */
    APIResult<PageInfo<CabinetInfoDTO>> queryCabinetInfoList(
            CabinetQueryDTO queryDTO);

    /**
     * 维修寄存柜
     *
     * @param cabinetIds
     * @return
     */
    APIResult<Void> repairCabinets(List<Long> cabinetIds);
}
