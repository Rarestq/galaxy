package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.ChargeTemplateDTO;
import com.wuxiu.galaxy.api.dto.ChargeTemplateQueryDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeTemplateDTO;
import com.wuxiu.galaxy.api.service.ChargeTemplateFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 计费模板相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/19 08:57
 */
@Service
public class ChargeTemplateClient {

    @Autowired
    private ChargeTemplateFacade templateFacade;

    /**
     * 新增/编辑计费模板
     *
     * @param templateDTO
     * @return
     */
    public APIResult<Long> saveChargeTemplate(SaveChargeTemplateDTO templateDTO) {
        return templateFacade.saveChargeTemplate(templateDTO);
    }

    /**
     * 获取计费模板列表
     *
     * @param queryDTO
     * @return
     */
    public APIResult<PageInfo<ChargeTemplateDTO>> getChargeTemplateList(
            ChargeTemplateQueryDTO queryDTO) {
        return templateFacade.getChargeTemplateList(queryDTO);
    }

    /**
     * 启用/禁用计费模板
     *
     * @param chargeTemplateId
     * @return
     */
    public APIResult<Void> enableOrDisableTemplate(Long chargeTemplateId) {
        return templateFacade.enableOrDisableTemplate(chargeTemplateId);
    }
}
