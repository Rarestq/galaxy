package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.ChargeRuleDTO;
import com.wuxiu.galaxy.api.dto.ChargeRuleDeleteDTO;
import com.wuxiu.galaxy.api.dto.ChargeRuleQueryDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeRuleDTO;
import com.wuxiu.galaxy.api.service.ChargeRuleFacade;
import com.wuxiu.galaxy.service.core.biz.service.ChargeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:32
 */
@Service("chargeRuleFacade")
public class ChargeRuleFacadeImpl implements ChargeRuleFacade {

    @Autowired
    private ChargeRuleService chargeRuleService;

    /**
     * 新增/编辑计费规则
     *
     * @param chargeRuleDTO
     * @return
     */
    @Override
    public APIResult<Long> saveChargeRule(SaveChargeRuleDTO chargeRuleDTO) {
        return APIResult.ok(chargeRuleService.saveChargeRule(chargeRuleDTO));
    }

    /**
     * 获取计费规则列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public APIResult<PageInfo<ChargeRuleDTO>> getChargeRuleList(ChargeRuleQueryDTO queryDTO) {
        return APIResult.ok(chargeRuleService.getChargeRuleList(queryDTO));
    }

    /**
     * 删除计费规则
     *
     * @return
     */
    @Override
    public APIResult<Void> deleteChargeRule(ChargeRuleDeleteDTO deleteDTO) {
        chargeRuleService.deleteChargeRule(deleteDTO);
        return APIResult.ok();
    }
}
