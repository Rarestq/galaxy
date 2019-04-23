package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.LuggageStorageRecordQueryForm;
import com.wuxiu.galaxy.web.biz.form.NewLuggageStorageRecordForm;
import com.wuxiu.galaxy.web.biz.vo.LuggageStorageRecordVO;

/**
 * 行李寄存相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 10:04
 */
public interface GwLuggageStorageRecordService {

    /**
     * 新增行李寄存记录
     *
     * @param form
     * @return
     */
    APIResult<LuggageStorageRecordVO> insertLuggageStorageRecord(NewLuggageStorageRecordForm form);

    /**
     * 查询行李寄存记录列表
     *
     * @param form
     * @return
     */
    APIResult<PageInfo<LuggageStorageRecordVO>> queryStorageRecordList(LuggageStorageRecordQueryForm form);

    /**
     * 行李取件
     *
     * @param luggageId  行李寄存记录id
     * @return
     */
    APIResult<Void> pickupLuggage(Long luggageId);
}
