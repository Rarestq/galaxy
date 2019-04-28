package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.LuggageLostCompensateRecordQueryForm;
import com.wuxiu.galaxy.web.biz.vo.LuggageLostCompensateRecordVO;

/**
 * 行李遗失赔偿登记记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:11
 */
public interface GwLuggageLostCompensateService {

    /**
     * 查询行李遗失赔偿登记记录列表
     *
     * @param form
     * @return
     */
    APIResult<PageInfo<LuggageLostCompensateRecordVO>> queryLostCompensateRecordList(
            LuggageLostCompensateRecordQueryForm form);

    /**
     * 对遗失的行李进行赔偿
     *
     * @param lostRegistrationRecordId
     * @return
     */
    APIResult<LuggageLostCompensateRecordVO> compensateByLuggageType(
            Long lostRegistrationRecordId);
}
