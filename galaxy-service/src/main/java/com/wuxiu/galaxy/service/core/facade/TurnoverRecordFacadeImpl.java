package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordQueryDTO;
import com.wuxiu.galaxy.api.service.TurnoverRecordFacade;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.TurnoverRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 营业额记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 14:09
 */
@Service("turnoverRecordFacade")
public class TurnoverRecordFacadeImpl implements TurnoverRecordFacade {

    @Autowired
    private TurnoverRecordService turnoverRecordService;

    /**
     * 按照管理员id对查询到的营业额进行分组
     *
     * @return
     */
    @Override
    public APIResult<List<PairDTO<Long, String>>> getTurnoverRecordPair() {
        return APIResult.ok(turnoverRecordService.getTurnoverRecordPair());
    }

    /**
     * 查询营业额记录信息
     *
     * @param queryDTO
     * @return
     */
    @Override
    public APIResult<PageInfo<TurnoverRecordDTO>> queryTurnoverRecordList(
            TurnoverRecordQueryDTO queryDTO) {
        return APIResult.ok(turnoverRecordService.queryTurnoverRecordList(queryDTO));
    }
}
