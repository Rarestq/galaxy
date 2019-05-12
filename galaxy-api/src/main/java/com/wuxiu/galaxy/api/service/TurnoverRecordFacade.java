package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.StatisticsResultDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordQueryDTO;

import java.util.List;

/**
 * 营业额记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 11:36
 */
public interface TurnoverRecordFacade {

    /**
     * 查询营业额记录信息
     *
     * @param queryDTO
     * @return
     */
    APIResult<PageInfo<TurnoverRecordDTO>> queryTurnoverRecordList(
            TurnoverRecordQueryDTO queryDTO);

    /**
     * 按管理员统计营业额
     *
     * @return
     */
    APIResult<List<StatisticsResultDTO>> statisticsTurnoverByAdmin();

    /**
     * 按费用类型统计营业额
     *
     * @return
     */
    APIResult<List<StatisticsResultDTO>> statisticsTurnoverByFeeType();
}
