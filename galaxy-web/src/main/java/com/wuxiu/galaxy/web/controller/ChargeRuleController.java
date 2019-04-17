package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.enums.ChargeRuleTypeEnum;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.web.biz.form.ChargeRuleDeleteForm;
import com.wuxiu.galaxy.web.biz.form.ChargeRuleQueryForm;
import com.wuxiu.galaxy.web.biz.form.SaveChargeRuleForm;
import com.wuxiu.galaxy.web.biz.service.GwChargeRuleService;
import com.wuxiu.galaxy.web.biz.vo.ChargeRuleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 计费规则相关接口
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:28
 */
@Slf4j
@Api(tags = "计费规则相关接口")
@RequestMapping("/charge_rule/rule")
@RestController
public class ChargeRuleController {

    @Autowired
    private GwChargeRuleService chargeRuleService;

    /**
     * 新增/编辑计费规则
     *
     * @param form
     * @return
     */
    @ApiOperation(value = "新增/编辑应收计费规则", notes = "新增/编辑应收计费规则")
    @PostMapping(value = "/save_rule")
    public APIResult<ChargeRuleVO> saveChargeRule(
            @RequestBody @Valid SaveChargeRuleForm form) {

        // 参数校验
        String saveChargeRuleCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(saveChargeRuleCheck)) {
            return APIResult.error(saveChargeRuleCheck);
        }

        form.setChargeRuleType(form.getChargeRuleType() == null ?
                ChargeRuleTypeEnum.RECEIVABLE_RULE.getCode() : form.getChargeRuleType());

        return chargeRuleService.saveChargeRule(form);
    }

    /**
     * 获取计费规则列表
     *
     * @param form
     * @return
     */
    @ApiOperation(value = "获取计费规则列表", notes = "获取计费规则列表")
    @GetMapping(value = "")
    public APIResult<PageInfo<ChargeRuleVO>> getChargeRuleList(
            @RequestBody @Valid ChargeRuleQueryForm form) {

        // 参数校验
        String chargeRulesCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(chargeRulesCheck)) {
            return APIResult.error(chargeRulesCheck);
        }

        return chargeRuleService.getChargeRuleList(form);
    }

    /**
     * 删除计费规则
     *
     * @param form
     * @return
     */
    @ApiOperation(value = "删除计费规则", notes = "删除计费规则")
    @PostMapping(value = "/delete_rule")
    public APIResult<Void> deleteChargeRule(
            @RequestBody @Valid ChargeRuleDeleteForm form) {

        // 参数校验
        String saveChargeRuleCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(saveChargeRuleCheck)) {
            return APIResult.error(saveChargeRuleCheck);
        }

        return chargeRuleService.deleteChargeRule(form);
    }
}
