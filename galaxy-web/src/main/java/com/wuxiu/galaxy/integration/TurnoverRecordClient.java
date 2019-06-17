package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.StatisticsResultDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordQueryDTO;
import com.wuxiu.galaxy.api.service.TurnoverRecordFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 营业额记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 11:32
 */
@Service
public class TurnoverRecordClient {

    @Autowired
    private TurnoverRecordFacade turnoverRecordFacade;

    /**
     * 查询营业额记录信息
     *
     * @param queryDTO
     * @return
     */
    public APIResult<PageInfo<TurnoverRecordDTO>> queryTurnoverRecordList(
            TurnoverRecordQueryDTO queryDTO) {
        return turnoverRecordFacade.queryTurnoverRecordList(queryDTO);
    }
    /**
     * 按管理员统计营业额
     *
     * @return
     */
    public APIResult<List<StatisticsResultDTO>> statisticsTurnoverByAdmin() {
        return turnoverRecordFacade.statisticsTurnoverByAdmin();
    }

    /**
     * 按费用类型统计营业额
     *
     * @return
     */
    public APIResult<List<StatisticsResultDTO>> statisticsTurnoverByFeeType() {
        return turnoverRecordFacade.statisticsTurnoverByFeeType();
    }
}
