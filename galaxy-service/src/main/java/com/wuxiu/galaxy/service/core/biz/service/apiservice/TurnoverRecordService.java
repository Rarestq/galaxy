package com.wuxiu.galaxy.service.core.biz.service.apiservice;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.StatisticsResultDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordQueryDTO;

import java.util.List;

/**
 * 营业额记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 14:10
 */
public interface TurnoverRecordService {

    /**
     * 查询营业额记录信息
     *
     * @param queryDTO
     * @return
     */
    PageInfo<TurnoverRecordDTO> queryTurnoverRecordList(TurnoverRecordQueryDTO queryDTO);

    /**
     * 按管理员统计营业额
     *
     * @return
     */
    List<StatisticsResultDTO> statisticsTurnoverByAdmin();

    /**
     * 按费用类型统计营业额
     *
     * @return
     */
    List<StatisticsResultDTO> statisticsTurnoverByFeeType();
}
