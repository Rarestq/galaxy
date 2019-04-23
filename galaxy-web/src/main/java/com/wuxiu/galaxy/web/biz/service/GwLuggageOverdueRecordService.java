package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.LuggageOverdueRecordQueryForm;
import com.wuxiu.galaxy.web.biz.vo.LuggageOverdueRecordVO;

/**
 * 行李逾期未取清理相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 21:01
 */
public interface GwLuggageOverdueRecordService {

    /**
     * 查询行李逾期未取清理记录列表
     *
     * @param form
     * @return
     */
    APIResult<PageInfo<LuggageOverdueRecordVO>> queryOverdueRecordList(
            LuggageOverdueRecordQueryForm form);
}
