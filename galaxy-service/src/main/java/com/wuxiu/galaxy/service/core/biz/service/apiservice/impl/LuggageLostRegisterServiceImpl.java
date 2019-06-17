package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.common.util.DateUtil;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordDTO;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.utils.PageInfoUtil;
import com.wuxiu.galaxy.dal.manager.LuggageLostRegistrationRecordManager;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LuggageLostRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行李遗失登记相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/27 16:38
 */
@Slf4j
@Service
public class LuggageLostRegisterServiceImpl implements LuggageLostRegisterService {

    @Autowired
    private LuggageLostRegistrationRecordManager registrationRecordManager;

    /**
     * 查询行李遗失登记记录列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageInfo<LuggageLostRegisterRecordDTO> queryLostRegisterRecordList(
            LuggageLostRegisterRecordQueryDTO queryDTO) {
        log.info("查询行李遗失登记记录列表， queryDTO:{}", queryDTO);

        // 参数校验
        String registerRecordCheck = ValidatorUtil.returnAnyMessageIfError(queryDTO);
        if (StringUtils.isNotEmpty(registerRecordCheck)) {
            log.info("查询行李遗失登记记录列表，参数错误，{}", registerRecordCheck);
            throw new ParamException(registerRecordCheck);
        }

        // 构造查询条件
        com.wuxiu.galaxy.dal.common.dto.LuggageLostRegisterRecordQueryDTO recordQueryDTO =
                new com.wuxiu.galaxy.dal.common.dto.LuggageLostRegisterRecordQueryDTO();
        recordQueryDTO.setPage(PageInfoUtil.convert(queryDTO));
        recordQueryDTO.setQueryCondition(queryDTO.getQueryCondition());
        recordQueryDTO.setStatus(queryDTO.getStatus());
        if (StringUtils.isNotEmpty(queryDTO.getLostTime())) {
            recordQueryDTO.setLostTime(DateUtil.string2LocalDateTime(queryDTO
                    .getLostTime()));
        }

        // 查询行李遗失登记记录列表
        Page<LuggageLostRegisterRecordDTO> registerRecordDTOPage =
                registrationRecordManager.queryLostRegisterRecordList(recordQueryDTO);
        if (PageInfoUtil.isEmpty(registerRecordDTOPage)) {
            return PageInfoUtil.ofEmptyPage(queryDTO);
        }

        List<LuggageLostRegisterRecordDTO> records = registerRecordDTOPage.getRecords();

        return PageInfoUtil.of(registerRecordDTOPage, records);
    }
}
