package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.service.core.biz.service.smsservice.FinishStorageEventSmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 短信相关接口
 *
 * @author: wuxiu
 * @date: 2019/5/2 22:28
 */
@Slf4j
@Api(tags = "短信相关接口")
@RequestMapping("/luggage_storage/sms")
@RestController
public class SmsController {

    @Autowired
    private FinishStorageEventSmsService smsService;

    @ApiOperation(value = "行李寄存完成时，发送短信", notes = "行李寄存完成时，发送短信")
    @PostMapping(value = "/send_sms")
    public APIResult<Void> sendSmsWhenStorageFinished(Long luggageId) {

        if (Objects.isNull(luggageId)) {
            return APIResult.error("参数错误，行李寄存记录id不能为空");
        }

        smsService.notifyDepositorBySMS(luggageId);

        return APIResult.ok();
    }
}
