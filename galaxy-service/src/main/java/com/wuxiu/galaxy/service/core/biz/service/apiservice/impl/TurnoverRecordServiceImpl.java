package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.common.util.DateUtil;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordQueryDTO;
import com.wuxiu.galaxy.dal.manager.TurnoverRecordManager;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.TurnoverRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 营业额记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 14:13
 */
@Slf4j
@Service
public class TurnoverRecordServiceImpl implements TurnoverRecordService {

    @Autowired
    private TurnoverRecordManager turnoverRecordManager;

    /**
     * 按照管理员id对查询到的营业额进行分组
     *
     * @return
     */
    @Override
    public List<PairDTO<Long, String>> getTurnoverRecordPair() {
        log.info("按照管理员id对查询到的营业额进行分组");
        List<PairDTO<Long, String>> turnoverRecordPair =
                turnoverRecordManager.getTurnoverRecordPair();

        if (CollectionUtils.isEmpty(turnoverRecordPair)) {
            return Collections.emptyList();
        }

        return turnoverRecordPair;
    }

    /**
     * 查询营业额记录信息
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageInfo<TurnoverRecordDTO> queryTurnoverRecordList(
            TurnoverRecordQueryDTO queryDTO) {
        log.info("查询营业额记录信息, queryDTO:{}", queryDTO);

        // 参数校验
        String turnoverRecordCheck = ValidatorUtil.returnAnyMessageIfError(queryDTO);
        if (StringUtils.isNotEmpty(turnoverRecordCheck)) {
            log.info("查询营业额记录信息，参数错误，{}", turnoverRecordCheck);
            throw new ParamException(turnoverRecordCheck);
        }

        // 构造查询参数
        com.wuxiu.galaxy.dal.common.dto.TurnoverRecordQueryDTO recordQueryDTO =
                new com.wuxiu.galaxy.dal.common.dto.TurnoverRecordQueryDTO();
        recordQueryDTO.setPage(PageInfoUtil.convert(queryDTO));

        recordQueryDTO.setLuggageTypeId(queryDTO.getLuggageTypeId());
        recordQueryDTO.setAdminName(queryDTO.getAdminName());
        if (StringUtils.isNotEmpty(queryDTO.getGmtCreate())) {
            recordQueryDTO.setGmtCreate(DateUtil.string2LocalDateTime(queryDTO
                    .getGmtCreate()));
        }

        // 查询营业额记录列表信息
        Page<TurnoverRecordDTO> recordDTOPage =
                turnoverRecordManager.queryTurnoverRecordList(recordQueryDTO);
        if (PageInfoUtil.isEmpty(recordDTOPage)) {
            return PageInfoUtil.ofEmptyPage(queryDTO);
        }

        List<TurnoverRecordDTO> records = recordDTOPage.getRecords();

        return PageInfoUtil.of(recordDTOPage, records);
    }

    /**
     * todo:统计营业总额(按日期)
     *
     * @return
     */
    @Override
    public BigDecimal statisticsTotalTurnover() {
        List<String> turnovers = turnoverRecordManager.statisticsTotalTurnover();
        BigDecimal initValue = BigDecimal.ZERO;
        BigDecimal totalTurnover = BigDecimal.ZERO;
        for (String turnover : turnovers) {
            totalTurnover = initValue.add(new BigDecimal(turnover));
        }

        return totalTurnover;
    }

}
