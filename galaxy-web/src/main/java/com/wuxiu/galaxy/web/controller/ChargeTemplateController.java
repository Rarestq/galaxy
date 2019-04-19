package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.web.biz.form.ChargeTemplateQueryForm;
import com.wuxiu.galaxy.web.biz.form.SaveChargeTemplateForm;
import com.wuxiu.galaxy.web.biz.service.GwChargeTemplateService;
import com.wuxiu.galaxy.web.biz.vo.ChargeTemplateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * 计费模板相关接口
 *
 * @author: wuxiu
 * @date: 2019/4/17 08:49
 */
@Slf4j
@Api(tags = "计费模板相关接口")
@RequestMapping("/charge_template/template")
@RestController
public class ChargeTemplateController {

    @Autowired
    private GwChargeTemplateService chargeTemplateService;

    /**
     * 新增/编辑计费模板
     *
     * @param form
     * @return
     */
    @ApiOperation(value = "新增/编辑计费模板", notes = "新增/编辑计费模板")
    @PostMapping(value = "/save_rule")
    public APIResult<ChargeTemplateVO> saveChargeTemplate(
            @RequestBody @Valid SaveChargeTemplateForm form) {

        // 参数校验
        String saveChargeTemplateCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(saveChargeTemplateCheck)) {
            return APIResult.error(saveChargeTemplateCheck);
        }

        return chargeTemplateService.saveChargeTemplate(form);
    }

    /**
     * 获取计费模板列表
     *
     * @param form
     * @return
     */
    @ApiOperation(value = "获取计费模板列表", notes = "获取计费模板列表")
    @GetMapping(value = "")
    public APIResult<PageInfo<ChargeTemplateVO>> getChargeTemplateList(
            @RequestBody @Valid ChargeTemplateQueryForm form) {

        // 参数校验
        String chargeTemplatesCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(chargeTemplatesCheck)) {
            return APIResult.error(chargeTemplatesCheck);
        }

        return chargeTemplateService.getChargeTemplateList(form);
    }

    /**
     * 启用/禁用计费模板
     *
     * @param chargeTemplateId
     * @return
     */
    @ApiOperation(value = "启用/禁用计费模板", notes = "启用/禁用计费模板")
    @ApiImplicitParam(name = "chargeTemplateId", value = "计费模板id", required = true)
    @PostMapping("/enable")
    public APIResult<Void> enableOrDisableTemplate(Long chargeTemplateId) {
        if (Objects.isNull(chargeTemplateId)) {
            return APIResult.error("参数错误，计费模板id不能为空");
        }
        return chargeTemplateService.enableOrDisableTemplate(chargeTemplateId);
    }
}
