package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.ChargeRuleDeleteForm;
import com.wuxiu.galaxy.web.biz.form.ChargeRuleQueryForm;
import com.wuxiu.galaxy.web.biz.form.SaveChargeRuleForm;
import com.wuxiu.galaxy.web.biz.vo.ChargeRuleVO;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:30
 */
public interface GwChargeRuleService {

    /**
     * 新增/编辑计费规则
     *
     * @param form
     * @return
     */
    APIResult<ChargeRuleVO> saveChargeRule(SaveChargeRuleForm form);

    /**
     * 获取计费规则列表
     *
     * @param form
     * @return
     */
    APIResult<PageInfo<ChargeRuleVO>> getChargeRuleList(ChargeRuleQueryForm form);

    /**
     * 删除计费规则
     *
     * @param form
     * @return
     */
    APIResult<Void> deleteChargeRule(ChargeRuleDeleteForm form);
}
