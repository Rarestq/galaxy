package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.web.biz.form.LuggageLostCompensateRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwLuggageLostCompensateService;
import com.wuxiu.galaxy.web.biz.vo.LuggageLostCompensateRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 行李遗失赔偿登记记录相关接口
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:04
 */
@Slf4j
@Api(tags = "行李遗失赔偿登记记录相关接口")
@RequestMapping("/luggage_lost_compensate/compensate")
@RestController
public class LuggageLostCompensateController {

    @Autowired
    private GwLuggageLostCompensateService compensateService;

    @ApiOperation(value = "查询行李遗失赔偿登记记录列表", notes = "查询行李遗失赔偿登记记录列表")
    @GetMapping("")
    public APIResult<PageInfo<LuggageLostCompensateRecordVO>> queryLostCompensateRecordList(
            @Valid LuggageLostCompensateRecordQueryForm form) {
        // 参数校验
        String recordQueryCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(recordQueryCheck)) {
            return APIResult.error(recordQueryCheck);
        }
        return compensateService.queryLostCompensateRecordList(form);
    }

    @ApiOperation(value = "对遗失的行李进行赔偿", notes = "对遗失的行李进行赔偿")
    @PostMapping("/compensate_luggage")
    public APIResult<LuggageLostCompensateRecordVO> compensateByLuggageType(
            Long lostRegistrationRecordId) {
        return compensateService.compensateByLuggageType(lostRegistrationRecordId);
    }
}
