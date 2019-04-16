package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.web.biz.service.GwLuggageTypeService;
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

    @Autowired
    private GwLuggageTypeService luggageTypeService;

    @ApiOperation(value = "获取行李类型列表", notes = "寄存行李时，需要选择行李类型，是一个下拉框")
    @GetMapping("")
    public APIResult<List<Pair<Long,String>>> getLuggageTypeList() {
        return luggageTypeService.getLuggageTypeList();
    }
}
