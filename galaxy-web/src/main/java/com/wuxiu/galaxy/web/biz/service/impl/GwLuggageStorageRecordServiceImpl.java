package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.LuggageStorageInfoDTO;
import com.wuxiu.galaxy.api.dto.LuggageStorageRecordQueryDTO;
import com.wuxiu.galaxy.api.dto.NewLuggageStorageRecordDTO;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.dal.common.utils.BeanCopierUtil;
import com.wuxiu.galaxy.integration.LuggageStorageRecordClient;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.StreamUtil;
import com.wuxiu.galaxy.web.biz.form.LuggageStorageRecordQueryForm;
import com.wuxiu.galaxy.web.biz.form.NewLuggageStorageRecordForm;
import com.wuxiu.galaxy.web.biz.service.GwLuggageStorageRecordService;
import com.wuxiu.galaxy.web.biz.service.UserService;
import com.wuxiu.galaxy.web.biz.vo.LuggageStorageRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 行李寄存相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 17:03
 */
@Slf4j
@Service
public class GwLuggageStorageRecordServiceImpl implements GwLuggageStorageRecordService {

    @Autowired
    private LuggageStorageRecordClient storageRecordClient;

    @Autowired
    private UserService userService;

    /**
     * 新增行李寄存记录
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<LuggageStorageRecordVO> insertLuggageStorageRecord(
            NewLuggageStorageRecordForm form) {
        NewLuggageStorageRecordDTO storageRecordDTO =
                BeanCopierUtil.convert(form, NewLuggageStorageRecordDTO.class);

        // 获取当前登录人的信息
        OperateUserDTO operateUserDTO = userService.getCurrentOperateUser();
        APIResult<Long> result =
                storageRecordClient.insertLuggageStorageRecord(storageRecordDTO,
                        operateUserDTO);
        if (!result.isSuccess()) {
            log.warn("新增行李寄存记录失败，result:{}, form:{}", result, form);
            return CommonUtil.errorAPIResult(result);
        }

        Long luggageId = result.getData();
        if (Objects.isNull(luggageId)) {
            return APIResult.ok(null);
        }

        // 通过新增的 luggageId 去查询该条记录信息
        LuggageStorageRecordQueryDTO queryDTO = new LuggageStorageRecordQueryDTO();
        queryDTO.setLuggageId(luggageId);

        APIResult<PageInfo<LuggageStorageInfoDTO>> storageInfoAPIResult =
                storageRecordClient.queryStorageRecordList(queryDTO);
        if (!storageInfoAPIResult.isSuccess()) {
            log.warn("查询行李寄存记录失败，result:{}", storageInfoAPIResult);
            return CommonUtil.errorAPIResult(storageInfoAPIResult);
        }

        PageInfo<LuggageStorageInfoDTO> luggageStoragePageInfo =
                storageInfoAPIResult.getData();
        LuggageStorageInfoDTO storageInfoDTO = luggageStoragePageInfo.getRecords().get(0);
        LuggageStorageRecordVO storageRecordVO =
                BeanCopierUtil.convert(storageInfoDTO, LuggageStorageRecordVO.class);

        return APIResult.ok(storageRecordVO);
    }

    /**
     * 查询行李寄存记录列表
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<LuggageStorageRecordVO>> queryStorageRecordList(
            LuggageStorageRecordQueryForm form) {
        LuggageStorageRecordQueryDTO recordQueryDTO =
                BeanCopierUtil.convert(form, LuggageStorageRecordQueryDTO.class);
        // 拷贝分页参数
        PageInfoUtil.copy(form, recordQueryDTO);
        APIResult<PageInfo<LuggageStorageInfoDTO>> storageInfoAPIResult =
                storageRecordClient.queryStorageRecordList(recordQueryDTO);

        if (!storageInfoAPIResult.isSuccess()) {
            log.warn("查询行李寄存记录失败, result:{}, form:{}", storageInfoAPIResult, form);
            return CommonUtil.errorAPIResult(storageInfoAPIResult);
        }

        PageInfo<LuggageStorageInfoDTO> luggageStoragePageInfo =
                storageInfoAPIResult.getData();
        List<LuggageStorageInfoDTO> storageInfoDTOS = luggageStoragePageInfo.getRecords();

        List<LuggageStorageRecordVO> recordVOS =
                StreamUtil.convertBeanCopy(storageInfoDTOS, LuggageStorageRecordVO.class);

        PageInfo<LuggageStorageRecordVO> pageInfo =
                new PageInfo<>(form.getCurrent(), form.getSize());
        pageInfo.setRecords(recordVOS);
        pageInfo.setTotal(luggageStoragePageInfo.getTotal());
        pageInfo.setPages(luggageStoragePageInfo.getPages());

        return APIResult.ok(pageInfo);
    }

    /**
     * 行李取件
     *
     * @param luggageId 行李寄存记录id
     * @return
     */
    @Override
    public APIResult<Void> pickupLuggage(Long luggageId) {
        return storageRecordClient.pickupLuggage(luggageId);
    }
}
