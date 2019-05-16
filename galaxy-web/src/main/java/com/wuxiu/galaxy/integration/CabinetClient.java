package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.CabinetInfoDTO;
import com.wuxiu.galaxy.api.dto.CabinetQueryDTO;
import com.wuxiu.galaxy.api.service.CabinetFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行李寄存柜相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:51
 */
@Service
public class CabinetClient {

    @Autowired
    private CabinetFacade cabinetFacade;

    /**
     * 获取行李寄存柜信息列表
     *
     * @param queryDTO
     * @return
     */
    public APIResult<PageInfo<CabinetInfoDTO>> queryCabinetInfoList(
            CabinetQueryDTO queryDTO) {
        return cabinetFacade.queryCabinetInfoList(queryDTO);
    }

    /**
     * 维修寄存柜
     *
     * @param cabinetIds
     * @return
     */
    public APIResult<Void> repairCabinets(List<Long> cabinetIds) {
        return cabinetFacade.repairCabinets(cabinetIds);
    }
}
