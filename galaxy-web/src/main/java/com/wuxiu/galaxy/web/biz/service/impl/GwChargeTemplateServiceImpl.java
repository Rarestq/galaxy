package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.integration.ChargeTemplateClient;
import com.wuxiu.galaxy.web.biz.form.ChargeTemplateQueryForm;
import com.wuxiu.galaxy.web.biz.form.SaveChargeTemplateForm;
import com.wuxiu.galaxy.web.biz.service.GwChargeTemplateService;
import com.wuxiu.galaxy.web.biz.vo.ChargeTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 计费模板相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/17 08:51
 */
@Service
public class GwChargeTemplateServiceImpl implements GwChargeTemplateService {

    @Autowired
    private ChargeTemplateClient templateClient;

    /**
     * 新增/编辑计费模板
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<ChargeTemplateVO> saveChargeTemplate(SaveChargeTemplateForm form) {
        return null;
    }

    /**
     * 获取计费模板列表
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<ChargeTemplateVO>> getChargeTemplateList(
            ChargeTemplateQueryForm form) {
        return null;
    }

    /**
     * 启用/禁用计费模板
     *
     * @param chargeTemplateId
     * @return
     */
    @Override
    public APIResult<Void> enableOrDisableTemplate(Long chargeTemplateId) {
        return null;
    }
}
