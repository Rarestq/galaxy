package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.CabinetInfoDTO;
import com.wuxiu.galaxy.api.dto.CabinetQueryDTO;
import com.wuxiu.galaxy.dal.common.utils.BeanCopierUtil;
import com.wuxiu.galaxy.dal.common.utils.PageInfoUtil;
import com.wuxiu.galaxy.integration.CabinetClient;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.service.core.base.utils.StreamUtil;
import com.wuxiu.galaxy.web.biz.form.CabinetInfoQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwCabinetService;
import com.wuxiu.galaxy.web.biz.vo.CabinetInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行李寄存柜相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:44
 */
@Slf4j
@Service
public class GwCabinetServiceImpl implements GwCabinetService {

    @Autowired
    private CabinetClient cabinetClient;

    /**
     * 查询行李寄存柜信息
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<CabinetInfoVO>> queryCabinetInfoList(
            CabinetInfoQueryForm form) {
        CabinetQueryDTO queryDTO = BeanCopierUtil.convert(form,
                CabinetQueryDTO.class);
        PageInfoUtil.copy(form, queryDTO);

        // 获取行李寄存柜信息列表
        APIResult<PageInfo<CabinetInfoDTO>> cabinetInfoAPIResult =
                cabinetClient.queryCabinetInfoList(queryDTO);

        if (!cabinetInfoAPIResult.isSuccess()) {
            log.warn("获取行李寄存柜信息列表失败，result:{}, form:{}",
                    cabinetInfoAPIResult, form);
            return CommonUtil.errorAPIResult(cabinetInfoAPIResult);
        }

        PageInfo<CabinetInfoDTO> cabinetDTOPageInfo = cabinetInfoAPIResult.getData();
        List<CabinetInfoDTO> cabinetInfoDTOS = cabinetDTOPageInfo.getRecords();

        // 封装成 CabinetInfoVO 对象返回
        List<CabinetInfoVO> cabinetInfoVOS =
                StreamUtil.convertBeanCopy(cabinetInfoDTOS, CabinetInfoVO.class);

        PageInfo<CabinetInfoVO> pageInfo = new PageInfo<>(form.getCurrent(),
                form.getSize());
        pageInfo.setRecords(cabinetInfoVOS);
        pageInfo.setTotal(cabinetDTOPageInfo.getTotal());
        pageInfo.setPages(cabinetDTOPageInfo.getPages());

        return APIResult.ok(pageInfo);
    }

    /**
     * 维修寄存柜
     *
     * @param cabinetIds
     * @return
     */
    @Override
    public APIResult<Void> repairCabinets(List<Long> cabinetIds) {
        return cabinetClient.repairCabinets(cabinetIds);
    }
}
