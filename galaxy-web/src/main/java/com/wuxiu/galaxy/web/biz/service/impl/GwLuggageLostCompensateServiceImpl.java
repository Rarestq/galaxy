package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordInfoDTO;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordQueryDTO;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.dal.common.utils.BeanCopierUtil;
import com.wuxiu.galaxy.dal.common.utils.PageInfoUtil;
import com.wuxiu.galaxy.integration.LuggageLostCompensateClient;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.service.core.base.utils.StreamUtil;
import com.wuxiu.galaxy.web.biz.form.LuggageLostCompensateRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwLuggageLostCompensateService;
import com.wuxiu.galaxy.web.biz.service.UserService;
import com.wuxiu.galaxy.web.biz.vo.LuggageLostCompensateRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 行李遗失赔偿登记记录相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:31
 */
@Slf4j
@Service
public class GwLuggageLostCompensateServiceImpl implements GwLuggageLostCompensateService {

    @Autowired
    private LuggageLostCompensateClient lostCompensateClient;

    @Autowired
    private UserService userService;

    /**
     * 查询行李遗失赔偿登记记录列表
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<LuggageLostCompensateRecordVO>> queryLostCompensateRecordList(
            LuggageLostCompensateRecordQueryForm form) {
        LostCompensateRecordQueryDTO queryDTO =
                BeanCopierUtil.convert(form, LostCompensateRecordQueryDTO.class);
        PageInfoUtil.copy(form, queryDTO);

        // 查询行李遗失赔偿登记记录列表
        APIResult<PageInfo<LostCompensateRecordInfoDTO>> storageInfoAPIResult =
                lostCompensateClient.queryLostCompensateRecordList(queryDTO);

        if (!storageInfoAPIResult.isSuccess()) {
            log.warn("查询行李遗失赔偿登记记录列表, result:{}, form:{}",
                    storageInfoAPIResult, form);
            return CommonUtil.errorAPIResult(storageInfoAPIResult);
        }

        PageInfo<LostCompensateRecordInfoDTO> luggageStoragePageInfo =
                storageInfoAPIResult.getData();
        List<LostCompensateRecordInfoDTO> storageInfoDTOS =
                luggageStoragePageInfo.getRecords();

        List<LuggageLostCompensateRecordVO> recordVOS =
                StreamUtil.convertBeanCopy(storageInfoDTOS,
                        LuggageLostCompensateRecordVO.class);

        PageInfo<LuggageLostCompensateRecordVO> pageInfo =
                new PageInfo<>(form.getCurrent(), form.getSize());
        pageInfo.setRecords(recordVOS);
        pageInfo.setTotal(luggageStoragePageInfo.getTotal());
        pageInfo.setPages(luggageStoragePageInfo.getPages());

        return APIResult.ok(pageInfo);
    }

    /**
     * 对遗失的行李进行赔偿
     *
     * @param lostRegistrationRecordId
     * @return
     */
    @Override
    public APIResult<LuggageLostCompensateRecordVO> compensateByLuggageType(
            Long lostRegistrationRecordId) {

        // 获取当前登录操作人的信息
        OperateUserDTO operateUser = userService.getCurrentOperateUser();

        // 遗失行李赔偿
        APIResult<Long> compensateAPIResult = lostCompensateClient.compensateByLuggageType(
                lostRegistrationRecordId, operateUser);
        if (!compensateAPIResult.isSuccess()) {
            log.warn("遗失行李赔偿失败, result:{}, lostRegistrationRecordId:{}",
                    compensateAPIResult, lostRegistrationRecordId);
            return CommonUtil.errorAPIResult(compensateAPIResult);
        }

        // 获取遗失赔偿记录id
        Long compensateRecordId = compensateAPIResult.getData();
        if (Objects.isNull(compensateRecordId)) {
            return APIResult.ok(null);
        }
        // 构造行李遗失赔偿记录查询参数
        LostCompensateRecordQueryDTO queryDTO = new LostCompensateRecordQueryDTO();
        queryDTO.setLuggageLostCompensationRecordId(compensateRecordId);

        // 根据行李遗失赔偿记录主键id查询其对应的记录信息
        APIResult<PageInfo<LostCompensateRecordInfoDTO>> compensateRecordsAPIResult =
                lostCompensateClient.queryLostCompensateRecordList(queryDTO);
        if (!compensateRecordsAPIResult.isSuccess()) {
            log.warn("查询行李遗失赔偿记录失败，result:{}", compensateRecordsAPIResult);
            return CommonUtil.errorAPIResult(compensateRecordsAPIResult);
        }

        // 获取行李遗失赔偿记录信息
        PageInfo<LostCompensateRecordInfoDTO> compensateRecordDTOPageInfo =
                compensateRecordsAPIResult.getData();
        LostCompensateRecordInfoDTO compensateRecordInfoDTO =
                compensateRecordDTOPageInfo.getRecords().get(0);

        // 封装成 LuggageLostCompensateRecordVO 对象返回
        LuggageLostCompensateRecordVO compensateRecordVO = BeanCopierUtil.convert(
                compensateRecordInfoDTO, LuggageLostCompensateRecordVO.class);

        return APIResult.ok(compensateRecordVO);
    }
}
