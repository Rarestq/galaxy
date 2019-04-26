package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.PickupLuggageRecordQueryForm;
import com.wuxiu.galaxy.web.biz.vo.PickupLuggageRecordVO;

/**
 * 行李取件相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/26 09:31
 */
public interface GwPickupLuggageService {

    /**
     * 行李取件
     *
     * @param luggageId 行李寄存记录id
     * @return
     */
    APIResult<Void> pickupLuggage(Long luggageId);

    /**
     * 标记为遗失
     *
     * @param luggageId
     * @return
     */
    APIResult<Void> markLuggageAsLost(Long luggageId);

    /**
     * 逾期取件
     *
     * @param luggageId
     * @return
     */
    APIResult<Void> pickupOverdueLuggage(Long luggageId);

    /**
     * 查询行李取件记录列表
     *
     * @param form
     * @return
     */
    APIResult<PageInfo<PickupLuggageRecordVO>> queryPickupLuggageRecordList(
            PickupLuggageRecordQueryForm form);
}
