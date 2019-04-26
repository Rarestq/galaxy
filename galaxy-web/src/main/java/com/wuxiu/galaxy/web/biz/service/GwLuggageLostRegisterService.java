package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.LuggageLostRegisterRecordQueryForm;
import com.wuxiu.galaxy.web.biz.vo.LuggageLostRegisterRecordVO;

/**
 * 行李遗失登记记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:32
 */
public interface GwLuggageLostRegisterService {

    /**
     * 查询行李遗失登记记录列表
     *
     * @param form
     * @return
     */
    APIResult<PageInfo<LuggageLostRegisterRecordVO>> queryLostRegisterRecordList(
            LuggageLostRegisterRecordQueryForm form);
}
