package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.web.biz.form.LuggageLostRegisterRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwLuggageLostRegisterService;
import com.wuxiu.galaxy.web.biz.vo.LuggageLostRegisterRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 行李遗失登记记录相关接口
 *
 * @author: wuxiu
 * @date: 2019/4/26 19:59
 */
@Slf4j
@Api(tags = "行李遗失登记记录相关接口")
@RequestMapping("/luggage_lost_register/register")
@RestController
public class LuggageLostRegistRecordController {

    @Autowired
    private GwLuggageLostRegisterService registerService;

    @ApiOperation(value = "查询行李遗失登记记录列表", notes = "查询行李遗失登记记录列表")
    @GetMapping("")
    public APIResult<PageInfo<LuggageLostRegisterRecordVO>> queryLostRegisterRecordList(
            @Valid LuggageLostRegisterRecordQueryForm form) {
        // 参数校验
        String recordQueryCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(recordQueryCheck)) {
            return APIResult.error(recordQueryCheck);
        }
        return registerService.queryLostRegisterRecordList(form);
    }
}
