package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.api.service.ChargeTypeFacade;
import com.wuxiu.galaxy.service.core.biz.service.ChargeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 费用类型相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 22:43
 */
@Service("chargeTypeFacade")
public class ChargeTypeFacadeImpl implements ChargeTypeFacade {

    @Autowired
    private ChargeTypeService chargeTypeService;

    /**
     * 获取费用类型列表
     *
     * @return
     */
    @Override
    public APIResult<List<PairDTO<Long, String>>> getChargeTypeList() {
        return APIResult.ok(chargeTypeService.getChargeTypeList());
    }
}
