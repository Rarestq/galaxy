package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.ChargeTemplateQueryForm;
import com.wuxiu.galaxy.web.biz.form.SaveChargeTemplateForm;
import com.wuxiu.galaxy.web.biz.vo.ChargeTemplateVO;

/**
 * 计费模板相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/17 08:50
 */
public interface GwChargeTemplateService {

    /**
     * 新增/编辑计费模板
     *
     * @param form
     * @return
     */
    APIResult<ChargeTemplateVO> saveChargeTemplate(SaveChargeTemplateForm form);

    /**
     * 获取计费模板列表
     *
     * @param form
     * @return
     */
    APIResult<PageInfo<ChargeTemplateVO>> getChargeTemplateList(ChargeTemplateQueryForm form);

    /**
     * 启用/禁用计费模板
     *
     * @param chargeTemplateId
     * @return
     */
    APIResult<Void> enableOrDisableTemplate(Long chargeTemplateId);
}
