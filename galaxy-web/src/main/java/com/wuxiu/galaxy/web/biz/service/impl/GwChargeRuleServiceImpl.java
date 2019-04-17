package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.*;
import com.wuxiu.galaxy.dal.common.utils.BeanCopierUtil;
import com.wuxiu.galaxy.integration.ChargeRuleClient;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.StreamUtil;
import com.wuxiu.galaxy.web.biz.form.ChargeRuleDeleteForm;
import com.wuxiu.galaxy.web.biz.form.ChargeRuleQueryForm;
import com.wuxiu.galaxy.web.biz.form.SaveChargeRuleForm;
import com.wuxiu.galaxy.web.biz.service.GwChargeRuleService;
import com.wuxiu.galaxy.web.biz.vo.ChargeRuleVO;
import com.wuxiu.galaxy.web.biz.vo.ChargeTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 计费规则相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:30
 */
@Slf4j
@Service
public class GwChargeRuleServiceImpl implements GwChargeRuleService {

    @Autowired
    private ChargeRuleClient chargeRuleClient;

    /**
     * 新增/编辑计费规则
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<ChargeRuleVO> saveChargeRule(SaveChargeRuleForm form) {
        SaveChargeRuleDTO chargeRuleDTO =
                BeanCopierUtil.convert(form, SaveChargeRuleDTO.class);
        APIResult<Long> result = chargeRuleClient.saveChargeRule(chargeRuleDTO);
        if (!result.isSuccess()) {
            log.warn("新增/编辑计费规则信息失败，result:{}, form:{}", result, form);
            return CommonUtil.errorAPIResult(result);
        }

        Long chargeRuleId = result.getData();
        if (Objects.isNull(chargeRuleId)) {
            return APIResult.ok(null);
        }

        // 通过新增/编辑的计费规则的 chargeRuleId 再去查询计费规则信息
        ChargeRuleQueryDTO ruleQueryDTO = new ChargeRuleQueryDTO();
        ruleQueryDTO.setChargeRuleId(chargeRuleId);

        APIResult<PageInfo<ChargeRuleDTO>> chargeRulesAPIResult =
                chargeRuleClient.getChargeRuleList(ruleQueryDTO);
        if (!chargeRulesAPIResult.isSuccess()) {
            log.warn("获取计费规则信息失败，result:{}", chargeRulesAPIResult);
            return CommonUtil.errorAPIResult(chargeRulesAPIResult);
        }

        PageInfo<ChargeRuleDTO> chargeRuleDTOPageInfo = chargeRulesAPIResult.getData();
        ChargeRuleDTO ruleDTO = chargeRuleDTOPageInfo.getRecords().get(0);
        ChargeRuleVO chargeRuleVO = BeanCopierUtil.convert(ruleDTO, ChargeRuleVO.class);
        //todo:设置 chargeTemplateVOList 属性值
        List<ChargeTemplateDTO> chargeTemplateDTOS = ruleDTO.getChargeTemplateDTOS();
        List<ChargeTemplateVO> chargeTemplateVOS =
                StreamUtil.convertBeanCopy(chargeTemplateDTOS, ChargeTemplateVO.class);
        chargeRuleVO.setChargeTemplateVOList(chargeTemplateVOS);

        return APIResult.ok(chargeRuleVO);
    }

    /**
     * 获取计费规则列表
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<ChargeRuleVO>> getChargeRuleList(ChargeRuleQueryForm form) {
        ChargeRuleQueryDTO queryDTO = BeanCopierUtil.convert(form, ChargeRuleQueryDTO.class);
        // 拷贝分页参数
        PageInfoUtil.copy(form, queryDTO);

        APIResult<PageInfo<ChargeRuleDTO>> chargeRulesAPIResult =
                chargeRuleClient.getChargeRuleList(queryDTO);
        if (!chargeRulesAPIResult.isSuccess()) {
            log.warn("获取计费规则列表失败, result:{}, form:{}", chargeRulesAPIResult, form);
            return CommonUtil.errorAPIResult(chargeRulesAPIResult);
        }

        PageInfo<ChargeRuleDTO> ruleDTOPageInfo = chargeRulesAPIResult.getData();
        List<ChargeRuleDTO> chargeRuleDTOS = ruleDTOPageInfo.getRecords();

        // 按照 chargeRuleId 对计费规则进行分组
        Map<Long, ChargeRuleDTO> chargeRuleDTOMap =
                StreamUtil.toMap(chargeRuleDTOS, ChargeRuleDTO::getChargeRuleId);

        // 封装成 ChargeRuleVO 对象返回
        List<ChargeRuleVO> chargeRuleVOS =
                StreamUtil.convertBeanCopy(chargeRuleDTOS, ChargeRuleVO.class);

        // 将计费规则对应的计费模板转化为 ChargeTemplateVO
        convertTemplateDTO2VO(chargeRuleDTOMap, chargeRuleVOS);

        PageInfo<ChargeRuleVO> pageInfo = new PageInfo<>(form.getCurrent(), form.getSize());
        pageInfo.setRecords(chargeRuleVOS);
        pageInfo.setTotal(ruleDTOPageInfo.getTotal());
        pageInfo.setPages(ruleDTOPageInfo.getPages());

        return APIResult.ok(pageInfo);
    }

    /**
     * 将计费规则对应的计费模板转化为 ChargeTemplateVO
     *
     * @param chargeRuleDTOMap
     * @param chargeRuleVOS
     */
    private void convertTemplateDTO2VO(Map<Long, ChargeRuleDTO> chargeRuleDTOMap,
                                       List<ChargeRuleVO> chargeRuleVOS) {

        // 将计费规则对应的计费模板转化为 ChargeTemplateVO
        chargeRuleVOS.forEach(chargeRuleVO -> {
            ChargeRuleDTO chargeRuleDTO =
                    chargeRuleDTOMap.get(chargeRuleVO.getChargeRuleId());
            List<ChargeTemplateDTO> chargeTemplateDTOS = StreamUtil.flatConvert(
                    Collections.singletonList(chargeRuleDTO),
                    ChargeRuleDTO::getChargeTemplateDTOS);

            List<ChargeTemplateVO> chargeTemplateVOS =
                    StreamUtil.convertBeanCopy(chargeTemplateDTOS, ChargeTemplateVO.class);

            chargeRuleVO.setChargeTemplateVOList(chargeTemplateVOS);
        });
    }

    /**
     * 删除计费规则
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<Void> deleteChargeRule(ChargeRuleDeleteForm form) {
        ChargeRuleDeleteDTO ruleDeleteDTO =
                BeanCopierUtil.convert(form, ChargeRuleDeleteDTO.class);
        APIResult<Void> result = chargeRuleClient.deleteChargeRule(ruleDeleteDTO);
        if (!result.isSuccess()) {
            log.warn("删除计费规则失败, result:{}, form:{}", result, form);
            return CommonUtil.errorAPIResult(result);
        }

        return APIResult.ok(result.getData());
    }
}
