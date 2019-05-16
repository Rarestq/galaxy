package com.wuxiu.galaxy.service.core.biz.service.apiservice;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.CabinetInfoDTO;
import com.wuxiu.galaxy.api.dto.CabinetQueryDTO;
import com.wuxiu.galaxy.dal.domain.LuggageCabinet;

import java.util.List;

/**
 * 行李寄存相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:54
 */
public interface CabinetService {

    /**
     * 获取行李寄存柜信息列表
     *
     * @param queryDTO
     * @return
     */
    PageInfo<CabinetInfoDTO> queryCabinetInfoList(CabinetQueryDTO queryDTO);

    /**
     * 获取所有「空闲」状态的寄存柜信息
     *
     * @return
     */
    List<LuggageCabinet> getAllCabinets();

    /**
     * 新增行李寄存柜信息
     *
     * @param luggageCabinet
     * @return
     */
    Long addLuggageCabinet(LuggageCabinet luggageCabinet);

    /**
     * 维修寄存柜
     *
     * @param cabinetIds
     * @return
     */
    void repairCabinets(List<Long> cabinetIds);
}
