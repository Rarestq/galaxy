package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.enums.GlobalErrorCodeEnum;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.AdminInfoForm;
import com.wuxiu.galaxy.web.biz.form.AdminInfoQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwAdminService;
import com.wuxiu.galaxy.web.biz.vo.AdminInfoVO;
import com.wuxiu.galaxy.web.utils.StringConvertUtil;
import com.wuxiu.galaxy.web.utils.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static com.wuxiu.galaxy.api.common.constants.CommonConstant.COMMA;

/**
 * 管理员相关接口
 *
 * @author: wuxiu
 * @date: 2019/4/15 10:39
 */
@Slf4j
@Api(tags = "管理员相关接口")
@RequestMapping("/luggage_storage/api/admin")
@RestController
public class AdminController {

    @Autowired
    private GwAdminService adminService;

    @ApiOperation(value = "新增/编辑管理员信息", notes = "新增/编辑管理员信息，根据是否有管理员id来判断")
    @PostMapping(value = "/save")
    public APIResult<AdminInfoVO> saveAdminInfo(@RequestBody @Valid AdminInfoForm form) {
        // 参数校验
        String adminInfoCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(adminInfoCheck)) {
            return APIResult.error(GlobalErrorCodeEnum.INVALID_PARAM.getCode(), adminInfoCheck);
        }

        if (StringUtils.isBlank(form.getAdminName()) || StringUtils.isBlank(form.getAdminPhone())
                || StringUtils.isBlank(form.getPassword())
                || Objects.isNull(form.getAdminType())) {
            return APIResult.error(GlobalErrorCodeEnum.INVALID_PARAM.getCode(),
                    GlobalErrorCodeEnum.INVALID_PARAM.getMessage());
        }
        return adminService.saveAdminInfo(form);
    }

    @ApiOperation(value = "查询管理员信息列表", notes = "查询管理员信息列表")
    @GetMapping("")
    public APIResult<PageInfo<AdminInfoVO>> queryAdminInfoList(AdminInfoQueryForm form) {
        // 参数校验
        String adminInfoCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(adminInfoCheck)) {
            return APIResult.error(adminInfoCheck);
        }
        return adminService.queryAdminInfoList(form);
    }

    @ApiOperation(value = "删除管理员信息", notes = "删除管理员信息")
    @PostMapping("/delete")
    public APIResult<Void> deleteAdmin(@RequestBody String adminIds) {

        List<Long> adminIdList = StringConvertUtil.string2Long(adminIds, COMMA);
        if (CollectionUtils.isEmpty(adminIdList)) {
            return APIResult.error("管理员id不能为空");
        }
        return adminService.deleteAdmin(adminIdList);
    }

}
