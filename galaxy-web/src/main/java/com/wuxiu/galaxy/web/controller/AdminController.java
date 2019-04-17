package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.web.biz.form.AdminInfoForm;
import com.wuxiu.galaxy.web.biz.form.AdminInfoQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwAdminService;
import com.wuxiu.galaxy.web.biz.vo.AdminInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 管理员相关接口
 *
 * @author: wuxiu
 * @date: 2019/4/15 10:39
 */
@Slf4j
@Api(tags = "管理员相关接口")
@RequestMapping("/station_luggage_storage/admin")
@RestController
public class AdminController {

    @Autowired
    private GwAdminService adminService;

    @ApiOperation(value = "新增/编辑管理员信息", notes = "新增/编辑管理员信息，根据是否有管理员id来判断")
    @PostMapping(value = "/save")
    public APIResult<AdminInfoVO> saveTaskTemplate(@RequestBody @Valid AdminInfoForm form) {
        // 参数校验
        String adminInfoCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(adminInfoCheck)) {
            return APIResult.error(adminInfoCheck);
        }
        return adminService.saveAdminInfo(form);
    }

    @ApiOperation(value = "查询管理员信息列表", notes = "查询管理员信息列表")
    @GetMapping("")
    public APIResult<PageInfo<AdminInfoVO>> queryTaskTemplateList(@RequestBody @Valid AdminInfoQueryForm form) {
        // 参数校验
        String adminInfoQueryCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(adminInfoQueryCheck)) {
            return APIResult.error(adminInfoQueryCheck);
        }
        return adminService.queryAdminInfoList(form);
    }

}
