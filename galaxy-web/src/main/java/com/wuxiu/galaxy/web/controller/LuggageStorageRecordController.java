package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.web.biz.form.LuggageStorageRecordQueryForm;
import com.wuxiu.galaxy.web.biz.form.NewLuggageStorageRecordForm;
import com.wuxiu.galaxy.web.biz.service.GwLuggageStorageRecordService;
import com.wuxiu.galaxy.web.biz.vo.LuggageStorageRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 行李寄存相关接口
 *
 * @author: wuxiu
 * @date: 2019/4/15 22:23
 */
@Slf4j
@Api(tags = "行李寄存相关接口")
@RequestMapping("/luggage_storage/api/storage")
@RestController
public class LuggageStorageRecordController {

    @Autowired
    private GwLuggageStorageRecordService storageRecordService;

    @ApiOperation(value = "新增行李寄存记录", notes = "新增行李寄存记录")
    @PostMapping(value = "/create")
    public APIResult<LuggageStorageRecordVO> insertLuggageStorageRecord(
            @RequestBody @Valid NewLuggageStorageRecordForm form) {
        // 参数校验
        String storageRecordCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(storageRecordCheck)) {
            return APIResult.error(storageRecordCheck);
        }
        return storageRecordService.insertLuggageStorageRecord(form);
    }

    @ApiOperation(value = "查询行李寄存记录列表", notes = "查询行李寄存记录列表")
    @GetMapping("")
    public APIResult<PageInfo<LuggageStorageRecordVO>> queryStorageRecordList(
            LuggageStorageRecordQueryForm form) {
        // 参数校验
        String recordQueryCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(recordQueryCheck)) {
            return APIResult.error(recordQueryCheck);
        }
        return storageRecordService.queryStorageRecordList(form);
    }
}
