package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.ChargeCalculationRuleDTO;
import com.wuxiu.galaxy.api.dto.ChargeTemplateDTO;
import com.wuxiu.galaxy.api.dto.ChargeTemplateQueryDTO;
import com.wuxiu.galaxy.dal.common.utils.BeanCopierUtil;
import com.wuxiu.galaxy.integration.ChargeTemplateClient;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.StreamUtil;
import com.wuxiu.galaxy.web.biz.form.ChargeTemplateQueryForm;
import com.wuxiu.galaxy.web.biz.form.SaveChargeTemplateForm;
import com.wuxiu.galaxy.web.biz.service.GwChargeTemplateService;
import com.wuxiu.galaxy.web.biz.vo.ChargeCalculationRuleVO;
import com.wuxiu.galaxy.web.biz.vo.ChargeTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

/**
 * 计费模板相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/17 08:51
 */
@Slf4j
@Service
public class GwChargeTemplateServiceImpl implements GwChargeTemplateService {

    @Autowired
    private ChargeTemplateClient templateClient;

    /**
     * 新增/编辑计费模板
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<ChargeTemplateVO> saveChargeTemplate(SaveChargeTemplateForm form) {
        return null;
    }

    /**
     * 获取计费模板列表
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<ChargeTemplateVO>> getChargeTemplateList(
            ChargeTemplateQueryForm form) {
        ChargeTemplateQueryDTO queryDTO =
                BeanCopierUtil.convert(form, ChargeTemplateQueryDTO.class);
        PageInfoUtil.copy(form, queryDTO);

        APIResult<PageInfo<ChargeTemplateDTO>> chargeTemplatesAPIResult =
                templateClient.getChargeTemplateList(queryDTO);

        if (!chargeTemplatesAPIResult.isSuccess()) {
            log.warn("获取计费模板列表失败, result:{}, form:{}", chargeTemplatesAPIResult, form);
            return CommonUtil.errorAPIResult(chargeTemplatesAPIResult);
        }

        PageInfo<ChargeTemplateDTO> templateDTOPageInfo = chargeTemplatesAPIResult.getData();
        List<ChargeTemplateDTO> templateDTOS = templateDTOPageInfo.getRecords();

        // 按照 chargeTemplateId 对计费模板进行分组
        Map<Long, ChargeTemplateDTO> templateDTOMap =
                StreamUtil.toMap(templateDTOS, ChargeTemplateDTO::getChargeTemplateId);

        // 封装成 ChargeTemplateVO 对象返回
        List<ChargeTemplateVO> chargeTemplateVOS =
                StreamUtil.convertBeanCopy(templateDTOS, ChargeTemplateVO.class);

        // 将计费模板对应的计算规则转化为 ChargeCalculationRuleVO
        convertCalculRuleDTO2VO(templateDTOMap, chargeTemplateVOS);

        PageInfo<ChargeTemplateVO> pageInfo =
                new PageInfo<>(form.getCurrent(), form.getSize());
        pageInfo.setRecords(chargeTemplateVOS);
        pageInfo.setTotal(templateDTOPageInfo.getTotal());
        pageInfo.setPages(templateDTOPageInfo.getPages());

        return APIResult.ok(pageInfo);
    }

    /**
     * 将计费模板对应的计算规则转化为 ChargeCalculationRuleVO
     *
     * @param templateDTOMap
     * @param chargeTemplateVOS
     */
    private List<ChargeTemplateVO> convertCalculRuleDTO2VO(
            Map<Long, ChargeTemplateDTO> templateDTOMap,
            List<ChargeTemplateVO> chargeTemplateVOS) {

        List<ChargeTemplateVO> chargeTemplateVOList = newArrayList();
        // 将计费模板对应的计算规则转化为 ChargeCalculationRuleVO
        StreamUtil.convert(chargeTemplateVOS, chargeTemplateVO -> {
            ChargeTemplateDTO chargeTemplateDTO =
                    templateDTOMap.get(chargeTemplateVO.getChargeTemplateId());
            List<ChargeCalculationRuleDTO> calculationRuleDTOS = StreamUtil.flatConvert(
                    Collections.singletonList(chargeTemplateDTO),
                    ChargeTemplateDTO::getChargeCalculationRuleDTOList);

            List<ChargeCalculationRuleVO> calculationRuleVOS =
                    StreamUtil.convertBeanCopy(calculationRuleDTOS,
                            ChargeCalculationRuleVO.class);

            chargeTemplateVO.setChargeCalculationRuleVOS(calculationRuleVOS);
            chargeTemplateVOList.add(chargeTemplateVO);

            return chargeTemplateVOList;
        });

        return chargeTemplateVOList;
    }

    /**
     * 启用/禁用计费模板
     *
     * @param chargeTemplateId
     * @return
     */
    @Override
    public APIResult<Void> enableOrDisableTemplate(Long chargeTemplateId) {
        return templateClient.enableOrDisableTemplate(chargeTemplateId);
    }
}
