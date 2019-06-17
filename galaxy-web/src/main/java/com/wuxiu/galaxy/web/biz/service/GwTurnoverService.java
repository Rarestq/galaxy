package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.StatisticsResultDTO;
import com.wuxiu.galaxy.web.biz.form.TurnoverRecordQueryForm;
import com.wuxiu.galaxy.web.biz.vo.TurnoverRecordVO;

import java.util.List;

/**
 * 营业额记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 10:12
 */
public interface GwTurnoverService {

    /**
     * 查询营业额记录信息
     *
     * @param form
     * @return
     */
    APIResult<PageInfo<TurnoverRecordVO>> queryTurnoverRecordList(
            TurnoverRecordQueryForm form);

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
