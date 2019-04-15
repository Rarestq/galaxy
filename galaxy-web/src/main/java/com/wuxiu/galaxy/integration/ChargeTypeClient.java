package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.api.service.ChargeTypeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 费用类型服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 22:41
 */
@Service
public class ChargeTypeClient {

    @Autowired
    private ChargeTypeFacade chargeTypeFacade;

    /**
     * 获取费用类型列表
     *
     * @return
     */
    public APIResult<List<PairDTO<Long, String>>> getChargeTypeList() {
        return chargeTypeFacade.getChargeTypeList();
    }
}
