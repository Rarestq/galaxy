package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.LuggageTypeDTO;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.integration.LuggageTypeClient;
import com.wuxiu.galaxy.web.biz.service.GwLuggageTypeService;
import com.wuxiu.galaxy.web.biz.vo.LuggageTypeVO;
import com.wuxiu.galaxy.web.biz.vo.Pair;
import com.wuxiu.galaxy.web.utils.CommonUtil;
import com.wuxiu.galaxy.web.utils.ObjectConvertUtil;
import com.wuxiu.galaxy.web.utils.StreamUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 行李类型相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 09:22
 */
@Slf4j
@Service
public class GwLuggageTypeServiceImpl implements GwLuggageTypeService {

    @Autowired
    private LuggageTypeClient luggageTypeClient;

    /**
     * 获取行李类型列表
     *
     * @return
     */
    @Override
    public APIResult<List<Pair<Long, String>>> getLuggageTypeList() {
        APIResult<List<PairDTO<Long, String>>> luggageTypeListAPIResult =
                luggageTypeClient.getLuggageTypeList();

        if (!luggageTypeListAPIResult.isSuccess()) {
            log.warn("获取行李类型列表失败，result:{}", luggageTypeListAPIResult);
            return CommonUtil.errorAPIResult(luggageTypeListAPIResult);
        }

        List<PairDTO<Long, String>> data = luggageTypeListAPIResult.getData();
        if (CollectionUtils.isEmpty(data)) {
            return APIResult.ok(Collections.emptyList());
        }
        // 将 PairDTO 转化为 Pair
        List<Pair<Long, String>> luggageTypeListPair =
                ObjectConvertUtil.convertDTO2Domain(data);

        return APIResult.ok(luggageTypeListPair);
    }

    /**
     * 获取行李类型列表
     *
     * @return
     */
    @Override
    public APIResult<List<LuggageTypeVO>> getLuggageTypes() {
        APIResult<List<LuggageTypeDTO>> luggageTypesAPIResult =
                luggageTypeClient.getLuggageTypes();
        if (!luggageTypesAPIResult.isSuccess()) {
            log.warn("获取行李类型列表失败，result:{}", luggageTypesAPIResult);
            return CommonUtil.errorAPIResult(luggageTypesAPIResult);
        }

        List<LuggageTypeDTO> data = luggageTypesAPIResult.getData();
        if (CollectionUtils.isEmpty(data)) {
            return APIResult.ok(Collections.emptyList());
        }
        List<LuggageTypeVO> luggageTypeVOS = StreamUtil.convertBeanCopy(data,
                LuggageTypeVO.class);

        return APIResult.ok(luggageTypeVOS);
    }
}
