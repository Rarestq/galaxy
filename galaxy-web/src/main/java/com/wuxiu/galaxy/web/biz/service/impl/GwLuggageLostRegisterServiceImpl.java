package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordDTO;
import com.wuxiu.galaxy.api.dto.LuggageLostRegisterRecordQueryDTO;
import com.wuxiu.galaxy.integration.LuggageLostRegisterClient;
import com.wuxiu.galaxy.web.biz.form.LuggageLostRegisterRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwLuggageLostRegisterService;
import com.wuxiu.galaxy.web.biz.vo.LuggageLostRegisterRecordVO;
import com.wuxiu.galaxy.web.utils.BeanCopierUtil;
import com.wuxiu.galaxy.web.utils.CommonUtil;
import com.wuxiu.galaxy.web.utils.PageInfoUtil;
import com.wuxiu.galaxy.web.utils.StreamUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 行李遗失登记记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:34
 */
@Slf4j
@Service
public class GwLuggageLostRegisterServiceImpl implements GwLuggageLostRegisterService {

    @Autowired
    private LuggageLostRegisterClient lostRegisterClient;

    /**
     * 查询行李遗失登记记录列表
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<LuggageLostRegisterRecordVO>> queryLostRegisterRecordList(
            LuggageLostRegisterRecordQueryForm form) {

        LuggageLostRegisterRecordQueryDTO queryDTO = BeanCopierUtil.convert(form,
                LuggageLostRegisterRecordQueryDTO.class);
        // 拷贝分页参数
        PageInfoUtil.copy(form, queryDTO);
        APIResult<PageInfo<LuggageLostRegisterRecordDTO>> registerRecordsAPIResult =
                lostRegisterClient.queryLostRegisterRecordList(queryDTO);

        if (!registerRecordsAPIResult.isSuccess()) {
            log.warn("查询行李遗失登记记录列表失败, result:{}, form:{}",
                    registerRecordsAPIResult, form);
            return CommonUtil.errorAPIResult(registerRecordsAPIResult);
        }

        // 构造 LuggageLostRegisterRecordVO 分页对象
        PageInfo<LuggageLostRegisterRecordDTO> registerRecordDTOPageInfo =
                registerRecordsAPIResult.getData();
        List<LuggageLostRegisterRecordDTO> registerRecordDTOS =
                registerRecordDTOPageInfo.getRecords();

        // 按照主键id对 registerRecordDTOS 进行分组
        Map<Long, LuggageLostRegisterRecordDTO> registerRecordDTOMap =
                StreamUtil.toMap(registerRecordDTOS,
                        LuggageLostRegisterRecordDTO::getLostRegistrationRecordId);

        List<LuggageLostRegisterRecordVO> recordVOS =
                StreamUtil.convertBeanCopy(registerRecordDTOS,
                        LuggageLostRegisterRecordVO.class);

        // 将 luggageTypeId 转化为中文类型
        recordVOS.forEach(recordVO -> recordVO.setLuggageType(
                LuggageTypeEnum.getDescByCode(registerRecordDTOMap.get(recordVO
                        .getLostRegistrationRecordId()).getLuggageTypeId())));

        PageInfo<LuggageLostRegisterRecordVO> pageInfo =
                new PageInfo<>(form.getCurrent(), form.getSize());
        pageInfo.setRecords(recordVOS);
        pageInfo.setTotal(registerRecordDTOPageInfo.getTotal());
        pageInfo.setPages(registerRecordDTOPageInfo.getPages());

        return APIResult.ok(pageInfo);
    }

}
