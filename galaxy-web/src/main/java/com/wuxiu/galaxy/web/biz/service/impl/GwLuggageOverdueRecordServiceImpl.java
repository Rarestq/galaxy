package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.enums.LuggageOverdueStatusEnum;
import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.utils.PageInfoUtil;
import com.wuxiu.galaxy.integration.LuggageOverdueRecordClient;
import com.wuxiu.galaxy.service.core.base.utils.BeanCopierUtil;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.service.core.base.utils.StreamUtil;
import com.wuxiu.galaxy.web.biz.form.LuggageOverdueRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwLuggageOverdueRecordService;
import com.wuxiu.galaxy.web.biz.vo.LuggageOverdueRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 行李逾期未取清理相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 21:31
 */
@Slf4j
@Service
public class GwLuggageOverdueRecordServiceImpl implements GwLuggageOverdueRecordService {

    @Autowired
    private LuggageOverdueRecordClient overdueRecordClient;

    /**
     * 查询行李逾期未取清理记录列表
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<LuggageOverdueRecordVO>> queryOverdueRecordList(
            LuggageOverdueRecordQueryForm form) {

        LuggageOverdueRecordQueryDTO queryDTO =
                BeanCopierUtil.convert(form, LuggageOverdueRecordQueryDTO.class);
        // 拷贝分页参数
        PageInfoUtil.copy(form, queryDTO);

        APIResult<PageInfo<LuggageOverdueRecordInfoDTO>> overdueRecordInfoAPIResult =
                overdueRecordClient.queryOverdueRecordList(queryDTO);

        if (!overdueRecordInfoAPIResult.isSuccess()) {
            log.warn("查询行李寄存记录失败, result:{}, form:{}",
                    overdueRecordInfoAPIResult, form);
            return CommonUtil.errorAPIResult(overdueRecordInfoAPIResult);
        }

        PageInfo<LuggageOverdueRecordInfoDTO> overdueRecordPageInfo =
                overdueRecordInfoAPIResult.getData();

        List<LuggageOverdueRecordInfoDTO> overdueRecordInfoDTOS =
                overdueRecordPageInfo.getRecords();

        Map<Long, LuggageOverdueRecordInfoDTO> overdueRecordInfoDTOMap = StreamUtil.toMap(
                overdueRecordInfoDTOS,
                LuggageOverdueRecordInfoDTO::getLuggageOverdueRecordId);

        // 将 LuggageOverdueRecordInfoDTO 转化为 LuggageOverdueRecordVO
        List<LuggageOverdueRecordVO> recordVOS =
                StreamUtil.convertBeanCopy(overdueRecordInfoDTOS,
                        LuggageOverdueRecordVO.class);

        // 将 luggageType 和 status 转化为中文类型
        recordVOS.forEach(recordVO -> {
            recordVO.setLuggageType(LuggageTypeEnum.getDescByCode
                    (overdueRecordInfoDTOMap.get(recordVO
                            .getLuggageOverdueRecordId()).getLuggageTypeId()));
            recordVO.setStatus(LuggageOverdueStatusEnum.getDescByCode
                    (overdueRecordInfoDTOMap.get(recordVO.getLuggageOverdueRecordId())
                            .getStatus()));
        });

        PageInfo<LuggageOverdueRecordVO> pageInfo =
                new PageInfo<>(form.getCurrent(), form.getSize());
        pageInfo.setRecords(recordVOS);
        pageInfo.setTotal(overdueRecordPageInfo.getTotal());
        pageInfo.setPages(overdueRecordPageInfo.getPages());

        return APIResult.ok(pageInfo);
    }
}
