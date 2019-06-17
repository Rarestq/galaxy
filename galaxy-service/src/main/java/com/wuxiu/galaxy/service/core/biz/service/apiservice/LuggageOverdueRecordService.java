package com.wuxiu.galaxy.service.core.biz.service.apiservice;


import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageOverdueRecordQueryDTO;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.api.dto.SaveLuggageOverdueRecordDTO;

/**
 * 行李逾期未取清理相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/23 17:14
 */
public interface LuggageOverdueRecordService {

    /**
     * 创建行李逾期记录
     *
     * @param overdueRecordDTO
     * @param operateUserDTO
     * @return
     */
    Long createLuggageOverdueRecord(SaveLuggageOverdueRecordDTO overdueRecordDTO,
                                    OperateUserDTO operateUserDTO);

    /**
     * 查询行李逾期未取清理记录列表
     *
     * @param queryDTO
     * @return
     */
    PageInfo<LuggageOverdueRecordInfoDTO> queryOverdueRecordList(
            LuggageOverdueRecordQueryDTO queryDTO);
}
