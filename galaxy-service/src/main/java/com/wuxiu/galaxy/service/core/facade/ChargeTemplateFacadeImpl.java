package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.ChargeTemplateDTO;
import com.wuxiu.galaxy.api.dto.ChargeTemplateQueryDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeTemplateDTO;
import com.wuxiu.galaxy.api.service.ChargeTemplateFacade;
import com.wuxiu.galaxy.service.core.biz.service.ChargeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 计费模板相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/19 09:09
 */
@Service("chargeTemplateFacade")
public class ChargeTemplateFacadeImpl implements ChargeTemplateFacade {

    @Autowired
    private ChargeTemplateService templateService;

    /**
     * 新增/编辑计费模板
     *
     * @param templateDTO
     * @return
     */
    @Override
    public APIResult<Long> saveChargeTemplate(SaveChargeTemplateDTO templateDTO) {
        return APIResult.ok(templateService.saveChargeTemplate(templateDTO));
    }

    /**
     * 获取计费模板列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public APIResult<PageInfo<ChargeTemplateDTO>> getChargeTemplateList(
            ChargeTemplateQueryDTO queryDTO) {
        return APIResult.ok(templateService.getChargeTemplateList(queryDTO));
    }

    /**
     * 启用/禁用计费模板
     *
     * @param chargeTemplateId
     * @return
     */
    @Override
    public APIResult<Void> enableOrDisableTemplate(Long chargeTemplateId) {
        templateService.enableOrDisableTemplate(chargeTemplateId);
        return APIResult.ok();
    }
}
