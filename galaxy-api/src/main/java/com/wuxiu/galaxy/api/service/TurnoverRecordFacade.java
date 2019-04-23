package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.PairDTO;
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
     * 按照管理员id对查询到的营业额进行分组
     *
     * @return
     */
    APIResult<List<PairDTO<Long, String>>> getTurnoverRecordPair();

    /**
     * 查询营业额记录信息
     *
     * @param queryDTO
     * @return
     */
    APIResult<PageInfo<TurnoverRecordDTO>> queryTurnoverRecordList(
            TurnoverRecordQueryDTO queryDTO);
}
