package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.web.biz.form.TurnoverRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwTurnoverService;
import com.wuxiu.galaxy.web.biz.vo.Pair;
import com.wuxiu.galaxy.web.biz.vo.TurnoverRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * 营业额记录相关接口
 *
 * @author: wuxiu
 * @date: 2019/4/23 10:10
 */
@Slf4j
@Api(tags = "营业额记录相关接口")
@RequestMapping("/turnover_record/turnover")
@RestController
public class TurnoverRecordController {

    @Autowired
    private GwTurnoverService turnoverService;

    @ApiOperation(value = "查询营业额记录列表", notes = "查询营业额记录列表")
    @GetMapping("")
    public APIResult<PageInfo<TurnoverRecordVO>> queryTurnoverRecordList(
            @Valid TurnoverRecordQueryForm form) {
        // 参数校验
        String recordQueryCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(recordQueryCheck)) {
            return APIResult.error(recordQueryCheck);
        }
        return turnoverService.queryTurnoverRecordList(form);
    }

    @ApiOperation(value = "按照管理员id对查询到的营业额进行分组",
            notes = "按照管理员id对查询到的营业额进行分组")
    @GetMapping("/query")
    public APIResult<List<Pair<Long, String>>> getTurnoverRecordPair() {
        return turnoverService.getTurnoverRecordPair();
    }

    @ApiOperation(value = "统计营业总额", notes = "统计营业总额")
    @GetMapping("/statistics")
    public APIResult<BigDecimal> statisticsTotalTurnover() {
        return turnoverService.statisticsTotalTurnover();
    }
}
