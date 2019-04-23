package com.wuxiu.galaxy.web.biz.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordQueryDTO;
import com.wuxiu.galaxy.integration.TurnoverRecordClient;
import com.wuxiu.galaxy.service.core.base.utils.BeanCopierUtil;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.StreamUtil;
import com.wuxiu.galaxy.utils.ObjectConvertUtil;
import com.wuxiu.galaxy.web.biz.form.TurnoverRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwTurnoverService;
import com.wuxiu.galaxy.web.biz.vo.Pair;
import com.wuxiu.galaxy.web.biz.vo.TurnoverRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 营业额记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 11:31
 */
@Slf4j
@Service
public class GwTurnoverServiceImpl implements GwTurnoverService {

    @Autowired
    private TurnoverRecordClient turnoverRecordClient;

    /**
     * 查询营业额记录信息
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<TurnoverRecordVO>> queryTurnoverRecordList(
            TurnoverRecordQueryForm form) {
        TurnoverRecordQueryDTO queryDTO =
                BeanCopierUtil.convert(form, TurnoverRecordQueryDTO.class);
        // 拷贝分页参数
        PageInfoUtil.copy(form, queryDTO);

        APIResult<PageInfo<TurnoverRecordDTO>> turnoverRecordsAPIResult =
                turnoverRecordClient.queryTurnoverRecordList(queryDTO);
        if (!turnoverRecordsAPIResult.isSuccess()) {
            log.warn("查询营业额记录信息失败, result:{}, form:{}",
                    turnoverRecordsAPIResult, form);
            return CommonUtil.errorAPIResult(turnoverRecordsAPIResult);
        }

        PageInfo<TurnoverRecordDTO> recordDTOPageInfo = turnoverRecordsAPIResult.getData();
        List<TurnoverRecordDTO> turnoverRecordDTOS = recordDTOPageInfo.getRecords();
        List<TurnoverRecordVO> turnoverRecordVOS =
                StreamUtil.convertBeanCopy(turnoverRecordDTOS, TurnoverRecordVO.class);

        PageInfo<TurnoverRecordVO> pageInfo =
                new PageInfo<>(form.getCurrent(), form.getSize());
        pageInfo.setRecords(turnoverRecordVOS);
        pageInfo.setTotal(recordDTOPageInfo.getTotal());
        pageInfo.setPages(recordDTOPageInfo.getPages());

        return APIResult.ok(pageInfo);
    }

    /**
     * 按照管理员id对查询到的营业额进行分组
     *
     * @return
     */
    @Override
    public APIResult<List<Pair<Long, String>>> getTurnoverRecordPair() {
        APIResult<List<PairDTO<Long, String>>> turnoverRecordPair =
                turnoverRecordClient.getTurnoverRecordPair();
        if (!turnoverRecordPair.isSuccess()) {
            log.warn("查询营业额列表失败，result:{}", turnoverRecordPair);
            return CommonUtil.errorAPIResult(turnoverRecordPair);
        }

        List<PairDTO<Long, String>> data = turnoverRecordPair.getData();
        if (CollectionUtils.isEmpty(data)) {
            return APIResult.ok(Collections.emptyList());
        }
        // 将 PairDTO 转化为 Pair
        List<Pair<Long, String>> turnoverRecords =
                ObjectConvertUtil.convertDTO2Domain(data);

        return APIResult.ok(turnoverRecords);
    }
}
