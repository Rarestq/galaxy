package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.enums.LuggageTypeEnum;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordInfoDTO;
import com.wuxiu.galaxy.api.dto.LostCompensateRecordQueryDTO;
import com.wuxiu.galaxy.dal.common.utils.BeanCopierUtil;
import com.wuxiu.galaxy.dal.common.utils.PageInfoUtil;
import com.wuxiu.galaxy.integration.LuggageLostCompensateClient;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.service.core.base.utils.ObjectConvertUtil;
import com.wuxiu.galaxy.service.core.base.utils.StreamUtil;
import com.wuxiu.galaxy.web.biz.form.LuggageLostCompensateRecordQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwLuggageLostCompensateService;
import com.wuxiu.galaxy.web.biz.service.UserService;
import com.wuxiu.galaxy.web.biz.vo.LuggageLostCompensateRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
    public APIResult<PageInfo<LuggageLostCompensateRecordVO>>
    queryLostCompensateRecordList(
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

        // 构造 LuggageLostCompensateRecordVO 对象
        List<LuggageLostCompensateRecordVO> recordVOS =
                buildLuggageLostCompensateRecordVOS(luggageStoragePageInfo);

        PageInfo<LuggageLostCompensateRecordVO> pageInfo =
                new PageInfo<>(form.getCurrent(), form.getSize());
        pageInfo.setRecords(recordVOS);
        pageInfo.setTotal(luggageStoragePageInfo.getTotal());
        pageInfo.setPages(luggageStoragePageInfo.getPages());

        return APIResult.ok(pageInfo);
    }

    /**
     * 构造 LuggageLostCompensateRecordVO 对象
     *
     * @param luggageStoragePageInfo
     * @return
     */
    private List<LuggageLostCompensateRecordVO> buildLuggageLostCompensateRecordVOS(
            PageInfo<LostCompensateRecordInfoDTO> luggageStoragePageInfo) {
        List<LostCompensateRecordInfoDTO> storageInfoDTOS =
                luggageStoragePageInfo.getRecords();

        List<LuggageLostCompensateRecordVO> recordVOS =
                StreamUtil.convertBeanCopy(storageInfoDTOS,
                        LuggageLostCompensateRecordVO.class);

        // 按照主键id对 registerRecordDTOS 进行分组
        Map<Long, LostCompensateRecordInfoDTO> compensateRecordInfoDTOMap =
                StreamUtil.toMap(storageInfoDTOS, LostCompensateRecordInfoDTO::getLuggageLostCompensationRecordId);

        // 将 luggageType 转化为中文类型
        recordVOS.forEach(recordVO -> recordVO.setLuggageType(
                LuggageTypeEnum.getDescByCode(compensateRecordInfoDTOMap.get(recordVO
                        .getLuggageLostCompensationRecordId()).getLuggageType())));

        return recordVOS;
    }

    /**
     * 对遗失的行李进行赔偿
     *
     * @param lostRegistRecordIds
     * @param adminInfoDTO
     * @return
     */
    @Override
    public APIResult<LuggageLostCompensateRecordVO> compensateByLuggageType(
            String lostRegistRecordIds, AdminInfoDTO adminInfoDTO) {

        // 遗失行李赔偿
        APIResult<Long> compensateAPIResult = lostCompensateClient
                .compensateByLuggageType(ObjectConvertUtil
                        .lostRegisterRecordIdsIdFromString2Long(lostRegistRecordIds,
                                CommonConstant.COMMA).get(0), adminInfoDTO);

        if (!compensateAPIResult.isSuccess()) {
            log.warn("遗失行李赔偿失败, result:{}, lostRegistRecordIds:{}",
                    compensateAPIResult, lostRegistRecordIds);
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
