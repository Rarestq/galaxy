package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.integration.ChargeTypeClient;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.utils.ObjectConvertUtil;
import com.wuxiu.galaxy.web.biz.service.GwChargeTypeService;
import com.wuxiu.galaxy.web.biz.vo.Pair;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 费用类型相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 22:40
 */
@Slf4j
@Service
public class GwChargeTypeServiceImpl implements GwChargeTypeService {

    @Autowired
    private ChargeTypeClient chargeTypeClient;

    /**
     * 获取费用类型列表
     *
     * @return
     */
    @Override
    public APIResult<List<Pair<Long, String>>> getChargeTypeList() {
        APIResult<List<PairDTO<Long, String>>> chargeTypeListAPIResult =
                chargeTypeClient.getChargeTypeList();

        if (!chargeTypeListAPIResult.isSuccess()) {
            log.warn("获取费用类型列表失败，result:{}", chargeTypeListAPIResult);
            return CommonUtil.errorAPIResult(chargeTypeListAPIResult);
        }

        List<PairDTO<Long, String>> data = chargeTypeListAPIResult.getData();
        if (CollectionUtils.isEmpty(data)) {
            return APIResult.ok(Collections.emptyList());
        }
        // 将 PairDTO 转化为 Pair
        List<Pair<Long, String>> chargeTypeListPair =
                ObjectConvertUtil.convertDTO2Domain(data);

        return APIResult.ok(chargeTypeListPair);
    }
}
