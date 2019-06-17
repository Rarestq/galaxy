package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.web.biz.service.GwChargeCalculateRuleService;
import com.wuxiu.galaxy.web.biz.vo.ChargeCalculateRuleVO;
import com.wuxiu.galaxy.web.biz.vo.Pair;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 行李计费规则相关接口
 *
 * @author: wuxiu
 * @date: 2019/5/5 14:07
 */
@Slf4j
@Api(tags = "行李计费规则相关接口")
@RequestMapping("/luggage_storage/api/rule")
@RestController
public class ChargeCalculationRuleController {

    @Autowired
    private GwChargeCalculateRuleService calculateRuleService;

    @ApiOperation(value = "获取计费规则列表", notes = "寄存行李时，需要选择计费规则(行李类型)，是一个下拉框")
    @GetMapping("/pair")
    public APIResult<List<Pair<Long,String>>> getChargeCalculateRulesPair() {
        return calculateRuleService.getChargeCalculateRules();
    }

    @ApiOperation(value = " 根据行李类型联动计费规则", notes = " 下拉框选中行李类型后，联动计费规则名称")
    @ApiImplicitParam(value = "行李类型id", name = "luggageTypeId", paramType = "query", dataType = "Long")
    @GetMapping("/query")
    public APIResult<List<Pair<Long, String>>> queryCalculateRulesByLuggageType(
            @RequestParam("luggageTypeId") Long luggageTypeId) {
        if (Objects.isNull(luggageTypeId)) {
            return APIResult.error("参数错误，行李类型id不能为空");
        }
        return calculateRuleService.queryCalculateRulesByLuggageType(luggageTypeId);
    }

    @ApiOperation(value = "获取计费规则列表", notes = "获取计费规则列表")
    @GetMapping("")
    public APIResult<List<ChargeCalculateRuleVO>> getChargeCalculateRuleList() {
        return calculateRuleService.getChargeCalculateRuleList();
    }
}
