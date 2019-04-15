package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.web.biz.service.GwChargeTypeService;
import com.wuxiu.galaxy.web.biz.vo.Pair;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 费用类型相关接口
 *
 * @author: wuxiu
 * @date: 2019/4/15 22:26
 */
@Slf4j
@Api(tags = "费用类型相关接口")
@RequestMapping("/station_luggage_storage/charge_type")
@RestController
public class ChargeTypeController {

    @Autowired
    private GwChargeTypeService chargeTypeService;

    @ApiOperation(value = "获取费用类型列表", notes = "新增计费模板时，需要选择费用类型，是一个下拉框")
    @GetMapping("")
    public APIResult<List<Pair<Long,String>>> getChargeTypeList() {
        return chargeTypeService.getChargeTypeList();
    }
}
