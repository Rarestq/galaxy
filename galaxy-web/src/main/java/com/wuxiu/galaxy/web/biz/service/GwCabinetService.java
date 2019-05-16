package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.CabinetInfoQueryForm;
import com.wuxiu.galaxy.web.biz.vo.CabinetInfoVO;

import java.util.List;

/**
 * 行李寄存柜相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:40
 */
public interface GwCabinetService {

    /**
     * 查询行李寄存柜信息
     *
     * @param form
     * @return
     */
    APIResult<PageInfo<CabinetInfoVO>> queryCabinetInfoList(
            CabinetInfoQueryForm form);

    /**
     * 维修寄存柜
     *
     * @param cabinetIds
     * @return
     */
    APIResult<Void> repairCabinets(List<Long> cabinetIds);
}
