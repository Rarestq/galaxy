package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.web.biz.form.PickupLuggageRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwPickupLuggageService;
import com.wuxiu.galaxy.web.biz.vo.PickupLuggageRecordVO;
import com.wuxiu.galaxy.web.utils.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 行李取件相关接口
 *
 * @author: wuxiu
 * @date: 2019/4/26 09:29
 */
@Slf4j
@Api(tags = "行李取件相关接口")
@RequestMapping("/luggage_storage/api/pickup")
@RestController
public class PickupLuggageController {

    @Autowired
    private GwPickupLuggageService pickupLuggageService;

    @ApiOperation(value = "查询行李取件记录列表", notes = "查询行李取件记录列表")
    @GetMapping("")
    public APIResult<PageInfo<PickupLuggageRecordVO>> queryPickupLuggageRecordList(
            @Valid PickupLuggageRecordQueryForm form) {
        // 参数校验
        String recordQueryCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(recordQueryCheck)) {
            return APIResult.error(recordQueryCheck);
        }
        return pickupLuggageService.queryPickupLuggageRecordList(form);
    }

    @ApiOperation(value = "正常取件", notes = "正常取件")
    @PostMapping(value = "/common_pickup")
    public APIResult<Void> pickupLuggage(@RequestBody String luggageIds,
                                         HttpServletRequest request) {

        if (StringUtils.isEmpty(luggageIds)) {
            return APIResult.error("参数错误，行李寄存记录id不能为空");
        }

        // 获取当前系统登录用户信息
        AdminInfoDTO adminInfoDTO = (AdminInfoDTO) request.getSession(false)
                .getAttribute("adminInfoDTO");
        return pickupLuggageService.pickupLuggage(luggageIds, adminInfoDTO);
    }

    @ApiOperation(value = "标记为遗失", notes = "标记为遗失")
    @PostMapping(value = "/mark_as_lost")
    public APIResult<Void> markLuggageAsLost(@RequestBody String luggageIds,
                                             HttpServletRequest request) {

        if (StringUtils.isEmpty(luggageIds)) {
            return APIResult.error("参数错误，行李寄存记录id不能为空");
        }
        AdminInfoDTO adminInfoDTO = (AdminInfoDTO) request.getSession(false)
                .getAttribute("adminInfoDTO");
        return pickupLuggageService.markLuggageAsLost(luggageIds, adminInfoDTO);
    }

    @ApiOperation(value = "逾期取件", notes = "逾期取件")
    @PostMapping(value = "/overdue_pickup")
    public APIResult<Void> pickupOverdueLuggage(@RequestBody String luggageIds,
                                                HttpServletRequest request) {

        if (StringUtils.isEmpty(luggageIds)) {
            return APIResult.error("参数错误，行李寄存记录id不能为空");
        }
        AdminInfoDTO adminInfoDTO = (AdminInfoDTO) request.getSession(false)
                .getAttribute("adminInfoDTO");
        return pickupLuggageService.pickupOverdueLuggage(luggageIds, adminInfoDTO);
    }
}
