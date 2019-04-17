package com.wuxiu.galaxy.service.core.biz.service.impl;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.ChargeRuleDTO;
import com.wuxiu.galaxy.api.dto.ChargeRuleDeleteDTO;
import com.wuxiu.galaxy.api.dto.ChargeRuleQueryDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeRuleDTO;
import com.wuxiu.galaxy.dal.manager.ChargeRulesManager;
import com.wuxiu.galaxy.service.core.biz.service.ChargeRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ChargeRuleFacadeImpl
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:33
 */
@Slf4j
@Service
public class ChargeRuleServiceImpl implements ChargeRuleService {

    @Autowired
    private ChargeRulesManager chargeRulesManager;

    /**
     * 新增/编辑计费规则
     *
     * @param chargeRuleDTO
     * @return
     */
    @Override
    public Long saveChargeRule(SaveChargeRuleDTO chargeRuleDTO) {
        log.info("新增/编辑计费规则， chargeRuleDTO:{}", chargeRuleDTO);

        //todo:参数校验

        //todo:构造 SaveChargeRuleDTO 对象

        return chargeRulesManager.saveChargeRule(chargeRuleDTO);
    }

    /**
     * 获取计费规则列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageInfo<ChargeRuleDTO> getChargeRuleList(ChargeRuleQueryDTO queryDTO) {
        log.info("获取计费规则列表, queryDTO:{}", queryDTO);

        //todo:参数校验

        //todo:构造查询参数

        //todo: 查询计费规则列表
        return null;
    }

    /**
     * 删除计费规则
     *
     * @return
     */
    @Override
    public void deleteChargeRule(ChargeRuleDeleteDTO deleteDTO) {
        log.info("删除计费规则, deleteDTO:{}", deleteDTO);

    }
}
