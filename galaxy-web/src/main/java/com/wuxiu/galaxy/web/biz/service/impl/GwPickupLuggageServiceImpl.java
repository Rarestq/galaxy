package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordDTO;
import com.wuxiu.galaxy.api.dto.PickupLuggageRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.utils.BeanCopierUtil;
import com.wuxiu.galaxy.dal.common.utils.PageInfoUtil;
import com.wuxiu.galaxy.integration.PickupLuggageClient;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.service.core.base.utils.StreamUtil;
import com.wuxiu.galaxy.web.biz.form.PickupLuggageRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwPickupLuggageService;
import com.wuxiu.galaxy.web.biz.service.UserService;
import com.wuxiu.galaxy.web.biz.vo.PickupLuggageRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * All rights Reserved, Designed By www.maihaoche.com
 *
 * @author: wuxiu
 * @date: 2019/4/26 09:32
 */
@Slf4j
@Service
public class GwPickupLuggageServiceImpl implements GwPickupLuggageService {

    @Autowired
    private PickupLuggageClient pickupLuggageClient;

    @Autowired
    private UserService userService;

    /**
     * 行李取件
     *
     * @param luggageId 行李寄存记录id
     * @return
     */
    @Override
    public APIResult<Void> pickupLuggage(Long luggageId) {
        // 获取当前登录人的信息
        OperateUserDTO operateUserDTO = userService.getCurrentOperateUser();
        return pickupLuggageClient.pickupLuggage(luggageId, operateUserDTO);
    }

    /**
     * 标记为遗失
     *
     * @param luggageId
     * @return
     */
    @Override
    public APIResult<Void> markLuggageAsLost(Long luggageId) {
        OperateUserDTO operateUserDTO = userService.getCurrentOperateUser();
        return pickupLuggageClient.markLuggageAsLost(luggageId, operateUserDTO);
    }

    /**
     * 逾期取件
     *
     * @param luggageId
     * @return
     */
    @Override
    public APIResult<Void> pickupOverdueLuggage(Long luggageId) {
        OperateUserDTO operateUserDTO = userService.getCurrentOperateUser();
        return pickupLuggageClient.pickupOverdueLuggage(luggageId, operateUserDTO);
    }

    /**
     * 查询行李取件记录列表
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<PickupLuggageRecordVO>> queryPickupLuggageRecordList(
            PickupLuggageRecordQueryForm form) {
        PickupLuggageRecordQueryDTO queryDTO =
                BeanCopierUtil.convert(form, PickupLuggageRecordQueryDTO.class);
        PageInfoUtil.copy(form, queryDTO);

        APIResult<PageInfo<PickupLuggageRecordDTO>> pickupRecordPageInfoAPIResult =
                pickupLuggageClient.queryPickupLuggageRecordList(queryDTO);

        if (!pickupRecordPageInfoAPIResult.isSuccess()) {
            log.warn("查询行李取件记录列表失败, result:{}, form:{}",
                    pickupRecordPageInfoAPIResult, form);
            return CommonUtil.errorAPIResult(pickupRecordPageInfoAPIResult);
        }

        // 将 PickupLuggageRecordDTO 转化为 PickupLuggageRecordVO
        PageInfo<PickupLuggageRecordDTO> recordDTOPageInfo =
                pickupRecordPageInfoAPIResult.getData();
        List<PickupLuggageRecordDTO> pickupLuggageRecordDTOS =
                recordDTOPageInfo.getRecords();

        List<PickupLuggageRecordVO> pickupLuggageRecordVOS = StreamUtil.convertBeanCopy(
                pickupLuggageRecordDTOS, PickupLuggageRecordVO.class);

        PageInfo<PickupLuggageRecordVO> pageInfo =
                new PageInfo<>(form.getCurrent(), form.getSize());
        pageInfo.setRecords(pickupLuggageRecordVOS);
        pageInfo.setTotal(recordDTOPageInfo.getTotal());
        pageInfo.setPages(recordDTOPageInfo.getPages());

        return APIResult.ok(pageInfo);
    }
}
