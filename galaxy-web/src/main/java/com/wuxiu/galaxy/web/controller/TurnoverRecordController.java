package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.StatisticsResultDTO;
import com.wuxiu.galaxy.web.biz.form.TurnoverRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwTurnoverService;
import com.wuxiu.galaxy.web.biz.vo.TurnoverRecordVO;
import com.wuxiu.galaxy.web.utils.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 营业额记录相关接口
 *
 * @author: wuxiu
 * @date: 2019/4/23 10:10
 */
@Slf4j
@Api(tags = "营业额记录相关接口")
@RequestMapping("/luggage_storage/api/turnover")
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

    /**
     * 按管理员统计营业额
     *
     * @return
     */
    @ApiOperation(value = "按照管理员对查询到的营业额进行分组",
            notes = "按照管理员对查询到的营业额进行分组")
    @GetMapping("/statistics_by_admin")
    public APIResult<List<StatisticsResultDTO>> statisticsTurnoverByAdmin() {
        return turnoverService.statisticsTurnoverByAdmin();
    }

    /**
     * 按费用类型统计营业额
     *
     * @return
     */

    @ApiOperation(value = "按照费用类型对查询到的营业额进行分组",
            notes = "按照费用类型对查询到的营业额进行分组")
    @GetMapping("/statistics_by_fee_type")
    public APIResult<List<StatisticsResultDTO>> statisticsTurnoverByFeeType() {
        return turnoverService.statisticsTurnoverByFeeType();
    }
}
