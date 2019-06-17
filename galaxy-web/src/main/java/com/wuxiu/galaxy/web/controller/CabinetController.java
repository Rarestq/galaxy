package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.CabinetInfoQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwCabinetService;
import com.wuxiu.galaxy.web.biz.vo.CabinetInfoVO;
import com.wuxiu.galaxy.web.utils.StringConvertUtil;
import com.wuxiu.galaxy.web.utils.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wuxiu.galaxy.api.common.constants.CommonConstant.COMMA;

/**
 * 行李寄存柜相关接口
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:38
 */
@Slf4j
@Api(tags = "管理员相关接口")
@RequestMapping("/luggage_storage/api/cabinet")
@RestController
public class CabinetController {

    @Autowired
    private GwCabinetService cabinetService;

    @ApiOperation(value = "查询寄存柜信息列表", notes = "查询寄存柜信息列表")
    @GetMapping("")
    public APIResult<PageInfo<CabinetInfoVO>> getCabinetInfoPage(
            CabinetInfoQueryForm form) {
        // 参数校验
        String cabinetCheck = ValidatorUtil.returnAnyMessageIfError(form);
        if (StringUtils.isNotEmpty(cabinetCheck)) {
            return APIResult.error(cabinetCheck);
        }
        return cabinetService.queryCabinetInfoList(form);
    }

    @ApiOperation(value = "维修寄存柜", notes = "维修寄存柜")
    @PostMapping("/repair")
    public APIResult<Void> repairCabinets(@RequestBody String cabinets) {

        List<Long> cabinetIds = StringConvertUtil.string2Long(cabinets, COMMA);
        if (CollectionUtils.isEmpty(cabinetIds)) {
            return APIResult.error("寄存柜id不能为空");
        }
        return cabinetService.repairCabinets(cabinetIds);
    }

}
