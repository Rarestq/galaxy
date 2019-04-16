package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.integration.LuggageStorageRecordClient;
import com.wuxiu.galaxy.web.biz.form.LuggageStorageRecordQueryForm;
import com.wuxiu.galaxy.web.biz.form.NewLuggageStorageRecordForm;
import com.wuxiu.galaxy.web.biz.service.GwLuggageStorageRecordService;
import com.wuxiu.galaxy.web.biz.vo.LuggageStorageRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 行李寄存相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 17:03
 */
@Service
public class GwLuggageStorageRecordServiceImpl implements GwLuggageStorageRecordService {

    @Autowired
    private LuggageStorageRecordClient storageRecordClient;

    /**
     * 新增行李寄存记录
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<LuggageStorageRecordVO> insertLuggageStorageRecord(NewLuggageStorageRecordForm form) {
        return null;
    }

    /**
     * 查询行李寄存记录列表
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<LuggageStorageRecordVO>> queryStorageRecordList(LuggageStorageRecordQueryForm form) {
        return null;
    }
}
