package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.LuggageCompensateForm;
import com.wuxiu.galaxy.web.biz.form.LuggageLostCompensateRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwLuggageLostCompensateService;
import com.wuxiu.galaxy.web.biz.vo.LuggageLostCompensateRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 行李遗失赔偿登记记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:31
 */
@Slf4j
@Service
public class GwLuggageLostCompensateServiceImpl implements GwLuggageLostCompensateService {

    /**
     * 查询行李遗失赔偿登记记录列表
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<LuggageLostCompensateRecordVO>> queryLostCompensateRecordList(
            LuggageLostCompensateRecordQueryForm form) {
        return null;
    }

    /**
     * 对遗失的行李进行赔偿
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<Void> compensateByLuggageType(LuggageCompensateForm form) {
        return null;
    }
}
