package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.integration.ChargeRuleClient;
import com.wuxiu.galaxy.web.biz.form.ChargeRuleDeleteForm;
import com.wuxiu.galaxy.web.biz.form.ChargeRuleQueryForm;
import com.wuxiu.galaxy.web.biz.form.SaveChargeRuleForm;
import com.wuxiu.galaxy.web.biz.service.GwChargeRuleService;
import com.wuxiu.galaxy.web.biz.vo.ChargeRuleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:30
 */
@Slf4j
@Service
public class GwChargeRuleServiceImpl implements GwChargeRuleService {

    @Autowired
    private ChargeRuleClient chargeRuleClient;

    /**
     * 新增/编辑计费规则
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<ChargeRuleVO> saveChargeRule(SaveChargeRuleForm form) {
        return null;
    }

    /**
     * 获取计费规则列表
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<ChargeRuleVO>> getChargeRuleList(ChargeRuleQueryForm form) {
        return null;
    }

    /**
     * 删除计费规则
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<ChargeRuleVO> deleteChargeRule(ChargeRuleDeleteForm form) {
        return null;
    }
}
