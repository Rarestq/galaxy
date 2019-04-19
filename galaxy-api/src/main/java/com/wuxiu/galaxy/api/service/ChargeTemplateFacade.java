package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.ChargeTemplateDTO;
import com.wuxiu.galaxy.api.dto.ChargeTemplateQueryDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeTemplateDTO;

/**
 * 计费模板相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/19 09:09
 */
public interface ChargeTemplateFacade {

    /**
     * 新增/编辑计费模板
     *
     * @param templateDTO
     * @return
     */
    APIResult<Long> saveChargeTemplate(SaveChargeTemplateDTO templateDTO);

    /**
     * 获取计费模板列表
     *
     * @param queryDTO
     * @return
     */
    APIResult<PageInfo<ChargeTemplateDTO>> getChargeTemplateList(
            ChargeTemplateQueryDTO queryDTO);

    /**
     * 启用/禁用计费模板
     *
     * @param chargeTemplateId
     * @return
     */
    APIResult<Void> enableOrDisableTemplate(Long chargeTemplateId);
}
