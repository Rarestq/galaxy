package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.PairDTO;
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
     * 按照管理员id对查询到的营业额进行分组
     *
     * @return
     */
    public APIResult<List<PairDTO<Long, String>>> getTurnoverRecordPair() {
        return turnoverRecordFacade.getTurnoverRecordPair();
    }

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
}
