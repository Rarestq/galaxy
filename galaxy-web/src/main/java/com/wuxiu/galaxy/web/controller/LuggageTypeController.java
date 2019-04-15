package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.web.biz.form.AdminInfoQueryForm;
import com.wuxiu.galaxy.web.biz.vo.AdminInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 行李类型相关接口
 *
 * @author: wuxiu
 * @date: 2019/4/15 22:26
 */
@Slf4j
@Api(tags = "行李类型相关接口")
@RequestMapping("/station_luggage_storage/luggage_type")
@RestController
public class LuggageTypeController {

    @ApiOperation(value = "查询任务管理员信息列表", notes = "查询任务管理员信息列表")
    @GetMapping("")
    public APIResult<PageInfo<AdminInfoVO>> queryTaskTemplateList(AdminInfoQueryForm form) {
        // 参数校验
        String templateItemCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(templateItemCheck)) {
            return APIResult.error(templateItemCheck);
        }
        return null;
    }
}
