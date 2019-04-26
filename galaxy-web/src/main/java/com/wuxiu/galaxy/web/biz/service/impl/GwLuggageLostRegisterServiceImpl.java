package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.LuggageLostRegisterRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwLuggageLostRegisterService;
import com.wuxiu.galaxy.web.biz.vo.LuggageLostRegisterRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 行李遗失登记记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:34
 */
@Slf4j
@Service
public class GwLuggageLostRegisterServiceImpl implements GwLuggageLostRegisterService {

    /**
     * 查询行李遗失登记记录列表
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<LuggageLostRegisterRecordVO>> queryLostRegisterRecordList(
            LuggageLostRegisterRecordQueryForm form) {
        return null;
    }
}
