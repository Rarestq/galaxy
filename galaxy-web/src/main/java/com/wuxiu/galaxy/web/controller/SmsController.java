package com.wuxiu.galaxy.web.controller;

/**
 * 短信相关接口
 *
 * @author: wuxiu
 * @date: 2019/5/2 22:28
 */
//@Slf4j
//@Api(tags = "短信相关接口")
//@RequestMapping("/luggage_storage/api/sms")
//@RestController
//public class SmsController {
//
//    @Autowired
//    private FinishStorageEventSmsService smsService;
//
//    @ApiOperation(value = "行李寄存完成时，发送短信", notes = "行李寄存完成时，发送短信")
//    @PostMapping(value = "/send_sms")
//    public APIResult<Void> sendSmsWhenStorageFinished(Long luggageId) {
//
//        if (Objects.isNull(luggageId)) {
//            return APIResult.error("参数错误，行李寄存记录id不能为空");
//        }
//
//        smsService.notifyDepositorBySMS(luggageId);
//
//        return APIResult.ok();
//    }
//}
