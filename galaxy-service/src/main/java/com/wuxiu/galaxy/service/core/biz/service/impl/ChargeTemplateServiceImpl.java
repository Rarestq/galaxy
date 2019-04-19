package com.wuxiu.galaxy.service.core.biz.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.enums.ChargeCalculationTypeEnum;
import com.wuxiu.galaxy.api.common.expection.BizException;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.ChargeCalculationRuleDTO;
import com.wuxiu.galaxy.api.dto.ChargeTemplateDTO;
import com.wuxiu.galaxy.api.dto.ChargeTemplateQueryDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeTemplateDTO;
import com.wuxiu.galaxy.dal.domain.ChargeTemplate;
import com.wuxiu.galaxy.dal.manager.ChargeTemplateManager;
import com.wuxiu.galaxy.service.core.base.utils.CalculateRulesValidateUtil;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.ChargeTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 计费模板相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/19 09:17
 */
@Slf4j
@Service
public class ChargeTemplateServiceImpl implements ChargeTemplateService {

    @Autowired
    private ChargeTemplateManager templateManager;

    /**
     * 新增/编辑计费模板
     *
     * @param templateDTO
     * @return
     */
    @Override
    public Long saveChargeTemplate(SaveChargeTemplateDTO templateDTO) {
        log.info("新增/编辑计费模板, templateDTO:{}", templateDTO);

        validateRuleParams(templateDTO);

        //todo: 构造 ChargeTemplateDTO 对象


        return templateManager.saveChargeTemplate(templateDTO);
    }

    /**
     * 参数校验
     *
     * @param templateDTO
     */
    private void validateRuleParams(SaveChargeTemplateDTO templateDTO) {

        String templateCheck = ValidatorUtil.returnAnyMessageIfError(templateDTO);
        if (StringUtils.isNotEmpty(templateCheck)) {
            log.info("新增/编辑计费模板, 参数错误，templateCheck:{}", templateCheck);
            throw new ParamException(templateCheck);
        }

        // 计费模板名称不能为空字符串
        String chargeTemplateName = templateDTO.getChargeTemplateName().trim();
        if (StringUtils.isEmpty(chargeTemplateName)) {
            log.warn("计费模板名称不能为空");
            throw new ParamException("计费模板名称不能为空");
        }

        // 根据当前计费模板名称查询计费模板信息
        ChargeTemplate chargeTemplate =
                templateManager.getChargeTemplateByName(templateDTO.getChargeTemplateName());

        if (Objects.nonNull(templateDTO.getChargeTemplateId())) {
            // 编辑计费模板时，校验计费模板名称不能和其他已有模板名称相同(可以保持不变)
            if (Objects.nonNull(chargeTemplate) &&
                    !Objects.equals(chargeTemplate.getChargeTemplateId(),
                            templateDTO.getChargeTemplateId())) {

                log.warn("计费模板名称已存在");
                throw new ParamException("计费模板名称已存在");
            }
        } else if (Objects.nonNull(chargeTemplate)) {
            // 校验新增的计费模板名称
            log.warn("计费模板名称已存在");
            throw new ParamException("计费模板名称已存在");
        }

        // 编辑计费模板时，要对该计费模板下绑定的计算规则进行校验，至少要有一个(本次只做一对一)
        if (Objects.isNull(templateDTO.getChargeCalculationRuleDTOList()) ||
                templateDTO.getChargeCalculationRuleDTOList().size() < 1) {

            log.warn("计费模板至少需要绑定一个计算规则");
            throw new BizException("计费规则至少需要绑定一个计费模板");
        }

        // 计算规则参数校验
        validateCalculationRuleParams(templateDTO.getChargeCalculationRuleDTOList());
    }

    /**
     * 计算规则参数校验
     *
     * @param chargeCalculationRuleDTOList
     */
    private void validateCalculationRuleParams(
            List<ChargeCalculationRuleDTO> chargeCalculationRuleDTOList) {

        // 判断是属于什么类型的计算规则，进行对应的校验
        ChargeCalculationRuleDTO calculationRuleDTO = chargeCalculationRuleDTOList.get(0);
        if (Objects.equals(calculationRuleDTO.getCalculationType(),
                ChargeCalculationTypeEnum.FIXED_CHARGE_CALCULATION.getCode())) {
            // 固定计算规则
            CalculateRulesValidateUtil.validateFixedCalculateParams(calculationRuleDTO);

        } else if (Objects.equals(calculationRuleDTO.getCalculationType(),
                ChargeCalculationTypeEnum.CYCLE_CHARGE_CALCULATION.getCode())) {
            // 周期计算规则
            CalculateRulesValidateUtil.validateCycleCalculateParams(calculationRuleDTO);
        }
    }

    /**
     * 获取计费模板列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageInfo<ChargeTemplateDTO> getChargeTemplateList(
            ChargeTemplateQueryDTO queryDTO) {
        log.info("获取计费模板列表, queryDTO:{}", queryDTO);

        // 参数校验
        String chargeTemplateCheck = ValidatorUtil.returnAnyMessageIfError(queryDTO);
        if (StringUtils.isNotEmpty(chargeTemplateCheck)) {
            log.info("获取计费模板列表，参数错误，{}", chargeTemplateCheck);
            throw new ParamException(chargeTemplateCheck);
        }

        // 构造查询参数
        com.wuxiu.galaxy.dal.common.dto.ChargeTemplateQueryDTO templateQueryDTO =
                new com.wuxiu.galaxy.dal.common.dto.ChargeTemplateQueryDTO();
        templateQueryDTO.setPage(PageInfoUtil.convert(queryDTO));
        templateQueryDTO.setChargeTemplateId(queryDTO.getChargeTemplateId());
        templateQueryDTO.setChargeTemplateName(queryDTO.getChargeTemplateName());

        // 查询计费模板列表
        Page<ChargeTemplateDTO> templateDTOPage =
                templateManager.getChargeTemplates(templateQueryDTO);
        if (PageInfoUtil.isEmpty(templateDTOPage)) {
            return PageInfoUtil.ofEmptyPage(queryDTO);
        }

        return PageInfoUtil.of(templateDTOPage, templateDTOPage.getRecords());
    }

    /**
     * 启用/禁用计费模板
     *
     * @param chargeTemplateId
     * @return
     */
    @Override
    public void enableOrDisableTemplate(Long chargeTemplateId) {
        log.info("启用/禁用计费模板, chargeTemplateId:{}", chargeTemplateId);
        if (Objects.isNull(chargeTemplateId)) {
            log.info("参数错误，计费模板 id 不能为空");
            throw new ParamException("参数错误，计费模板 id 不能为空");
        }

        templateManager.enableOrDisableTemplate(chargeTemplateId);
    }
}
