package com.wuxiu.galaxy.service.core.biz.service;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.ChargeTemplateDTO;
import com.wuxiu.galaxy.api.dto.ChargeTemplateQueryDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeTemplateDTO;

/**
 * 计费模板相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/19 09:13
 */
public interface ChargeTemplateService {

    /**
     * 新增/编辑计费模板
     *
     * @param templateDTO
     * @return
     */
    Long saveChargeTemplate(SaveChargeTemplateDTO templateDTO);

    /**
     * 获取计费模板列表
     *
     * @param queryDTO
     * @return
     */
    PageInfo<ChargeTemplateDTO> getChargeTemplateList(ChargeTemplateQueryDTO queryDTO);

    /**
     * 启用/禁用计费模板
     *
     * @param chargeTemplateId
     * @return
     */
    void enableOrDisableTemplate(Long chargeTemplateId);
}
