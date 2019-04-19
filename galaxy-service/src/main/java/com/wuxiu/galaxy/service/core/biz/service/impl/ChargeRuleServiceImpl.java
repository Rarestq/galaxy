package com.wuxiu.galaxy.service.core.biz.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.enums.ChargeCalculationTypeEnum;
import com.wuxiu.galaxy.api.common.expection.BizException;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.*;
import com.wuxiu.galaxy.dal.domain.ChargeRules;
import com.wuxiu.galaxy.dal.manager.ChargeRulesManager;
import com.wuxiu.galaxy.service.core.base.utils.AlphabeticNumericUtil;
import com.wuxiu.galaxy.service.core.base.utils.CalculateRulesValidateUtil;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.ChargeRuleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * ChargeRuleFacadeImpl
 *
 * @author: wuxiu
 * @date: 2019/4/16 22:33
 */
@Slf4j
@Service
public class ChargeRuleServiceImpl implements ChargeRuleService {

    @Autowired
    private ChargeRulesManager chargeRulesManager;

    /**
     * 新增/编辑计费规则
     *
     * @param chargeRuleDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Long saveChargeRule(SaveChargeRuleDTO chargeRuleDTO) {
        log.info("新增/编辑计费规则， chargeRuleDTO:{}", chargeRuleDTO);

        // 参数校验
        validateRuleParams(chargeRuleDTO);

        // 构造 SaveChargeRuleDTO 对象
        SaveChargeRuleDTO saveChargeRuleDTO = buildSaveChargeRuleDTO(chargeRuleDTO);

        return chargeRulesManager.saveChargeRule(saveChargeRuleDTO);
    }

    /**
     * 构造 SaveChargeRuleDTO 对象
     *
     * @param chargeRuleDTO
     * @return
     */
    private SaveChargeRuleDTO buildSaveChargeRuleDTO(SaveChargeRuleDTO chargeRuleDTO) {

        if (Objects.nonNull(chargeRuleDTO.getChargeRuleId())) {
            // 编辑计费规则信息
            SaveChargeRuleDTO editChargeRuleDTO = new SaveChargeRuleDTO();
            editChargeRuleDTO.setChargeRuleId(chargeRuleDTO.getChargeRuleId());
            editChargeRuleDTO.setChargeRuleName(chargeRuleDTO.getChargeRuleName());
            editChargeRuleDTO.setChargeRuleType(chargeRuleDTO.getChargeRuleType());
            editChargeRuleDTO.setChargeTemplateDTOList(
                    chargeRuleDTO.getChargeTemplateDTOList());

            return editChargeRuleDTO;
        }

        // 新增计费规则信息
        SaveChargeRuleDTO newChargeRuleDTO = new SaveChargeRuleDTO();
        newChargeRuleDTO.setChargeRuleName(chargeRuleDTO.getChargeRuleName());
        newChargeRuleDTO.setChargeRuleType(chargeRuleDTO.getChargeRuleType());
        newChargeRuleDTO.setChargeTemplateDTOList(
                chargeRuleDTO.getChargeTemplateDTOList());

        return newChargeRuleDTO;
    }

    /**
     * 参数校验
     *
     * @param chargeRuleDTO
     */
    private void validateRuleParams(SaveChargeRuleDTO chargeRuleDTO) {
        // 参数校验
        String chargeRuleCheck = ValidatorUtil.returnAnyMessageIfError(chargeRuleDTO);
        if (StringUtils.isNotEmpty(chargeRuleCheck)) {
            log.info("新增/编辑计费规则，参数错误：{}", chargeRuleCheck);
            throw new ParamException(chargeRuleCheck);
        }

        // 计费规则名称不能为空字符串
        String chargeRuleName = chargeRuleDTO.getChargeRuleName().trim();
        if (StringUtils.isEmpty(chargeRuleName)) {
            log.warn("计费规则名称不能为空");
            throw new ParamException("计费规则名称不能为空");
        }

        // 根据当前计费规则名称查询计费规则信息
        ChargeRules chargeRule =
                chargeRulesManager.getChargeRuleByName(chargeRuleDTO.getChargeRuleName());

        if (Objects.nonNull(chargeRuleDTO.getChargeRuleId())) {
            // 编辑计费规则时，校验计费规则名称不能和其他已有规则名称相同(可以保持不变)
            if (Objects.nonNull(chargeRule) &&
                    !Objects.equals(chargeRule.getChargeRuleId(),
                            chargeRuleDTO.getChargeRuleId())) {

                log.warn("计费规则名称已存在");
                throw new ParamException("计费规则名称已存在");
            }
        } else if (Objects.nonNull(chargeRule)) {
            // 校验新增的计费规则名称
            log.warn("计费规则名称已存在");
            throw new ParamException("计费规则名称已存在");
        }

        // 编辑计费规则时，要对该计费规则下绑定的计费模板进行校验，至少需要一个计费模板
        if (Objects.isNull(chargeRuleDTO.getChargeTemplateDTOList()) ||
                chargeRuleDTO.getChargeTemplateDTOList().size() < 1) {

            log.warn("计费规则至少需要绑定一个计费模板");
            throw new BizException("计费规则至少需要绑定一个计费模板");
        }

        // 计费模板及计算规则参数校验
        validateTemplateParams(chargeRuleDTO.getChargeTemplateDTOList());
    }

    /**
     * 对计费模板信息进行校验
     *
     * @param chargeTemplateDTOS
     */
    private void validateTemplateParams(List<ChargeTemplateDTO> chargeTemplateDTOS) {
        if (CollectionUtils.isEmpty(chargeTemplateDTOS)) {
            log.warn("计费模板不能为空");
            throw new ParamException("计费模板不能为空");
        }

        for (ChargeTemplateDTO chargeTemplateDTO : chargeTemplateDTOS) {
            if (Objects.isNull(chargeTemplateDTO.getChargeTypeId())) {
                throw new ParamException("费用类型ID不能为空");
            }

            List<ChargeCalculationRuleDTO> calculationRuleDTOList =
                    chargeTemplateDTO.getChargeCalculationRuleDTOList();
            if (CollectionUtils.isEmpty(calculationRuleDTOList)) {
                throw new ParamException("计算规则不能为空");
            }

            String maxFee = chargeTemplateDTO.getMaxFee();
            String minFee = chargeTemplateDTO.getMinFee();

            if (Objects.nonNull(minFee) &&
                    !AlphabeticNumericUtil.isDoubleString(minFee.trim())) {
                throw new ParamException("最小值金额输入错误");
            }

            if (Objects.nonNull(maxFee) &&
                    !AlphabeticNumericUtil.isDoubleString(maxFee.trim())) {
                throw new ParamException("最大值金额输入错误");
            }

            if (Objects.nonNull(maxFee) && Objects.nonNull(minFee)
                    && (new BigDecimal(maxFee).compareTo(new BigDecimal(minFee)) < 0)) {
                throw new ParamException("最高收费应该大于等于最低收费");
            }

            // 判断是属于什么类型的计算规则，进行对应的校验
            ChargeCalculationRuleDTO calculationRuleDTO = calculationRuleDTOList.get(0);
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
    }

    /**
     * 获取计费规则列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageInfo<ChargeRuleDTO> getChargeRuleList(ChargeRuleQueryDTO queryDTO) {
        log.info("获取计费规则列表, queryDTO:{}", queryDTO);

        // 参数校验
        String chargeRuleCheck = ValidatorUtil.returnAnyMessageIfError(queryDTO);
        if (StringUtils.isNotEmpty(chargeRuleCheck)) {
            log.info("获取计费规则列表，参数错误，{}", chargeRuleCheck);
            throw new ParamException(chargeRuleCheck);
        }

        // 构造查询参数
        com.wuxiu.galaxy.dal.common.dto.ChargeRuleQueryDTO ruleQueryDTO =
                new com.wuxiu.galaxy.dal.common.dto.ChargeRuleQueryDTO();
        ruleQueryDTO.setPage(PageInfoUtil.convert(queryDTO));
        ruleQueryDTO.setChargeRuleId(queryDTO.getChargeRuleId());
        ruleQueryDTO.setChargeRuleName(queryDTO.getChargeRuleName());
        ruleQueryDTO.setChargeRuleType(queryDTO.getChargeRuleType());

        // 查询计费规则列表 todo:查询计费模板列表，将其组装到 ChargeRuleDTO 中
        Page<ChargeRuleDTO> ruleDTOPage =
                chargeRulesManager.getChargeRuleList(ruleQueryDTO);
        if (PageInfoUtil.isEmpty(ruleDTOPage)) {
            return PageInfoUtil.ofEmptyPage(queryDTO);
        }

        List<ChargeRuleDTO> ruleDTOS = ruleDTOPage.getRecords();

        return PageInfoUtil.of(ruleDTOPage, ruleDTOS);
    }

    /**
     * 删除计费规则
     *
     * @return
     */
    @Override
    public void deleteChargeRule(ChargeRuleDeleteDTO deleteDTO) {
        log.info("删除计费规则, deleteDTO:{}", deleteDTO);

        // 参数校验
        String deleteRuleCheck = ValidatorUtil.returnAnyMessageIfError(deleteDTO);
        if (StringUtils.isNotEmpty(deleteRuleCheck)) {
            log.info("删除计费规则，参数错误，{}", deleteRuleCheck);
            throw new ParamException(deleteRuleCheck);
        }

        chargeRulesManager.deleteById(deleteDTO.getChargeRuleId());
    }
}
