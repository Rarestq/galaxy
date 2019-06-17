package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.CabinetInfoDTO;
import com.wuxiu.galaxy.api.dto.CabinetQueryDTO;
import com.wuxiu.galaxy.api.service.CabinetFacade;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行李寄存柜相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:53
 */
@Service("cabinetFacade")
public class CabinetFacadeImpl implements CabinetFacade {

    @Autowired
    private CabinetService cabinetService;

    /**
     * 获取行李寄存柜信息列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public APIResult<PageInfo<CabinetInfoDTO>> queryCabinetInfoList(
            CabinetQueryDTO queryDTO) {
        return APIResult.ok(cabinetService.queryCabinetInfoList(queryDTO));
    }

    /**
     * 维修寄存柜
     *
     * @param cabinetIds
     * @return
     */
    @Override
    public APIResult<Void> repairCabinets(List<Long> cabinetIds) {
        cabinetService.repairCabinets(cabinetIds);
        return APIResult.ok();
    }
}
