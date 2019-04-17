package com.wuxiu.galaxy.service.core.biz.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.expection.BizException;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.ChargeRuleDTO;
import com.wuxiu.galaxy.api.dto.ChargeRuleDeleteDTO;
import com.wuxiu.galaxy.api.dto.ChargeRuleQueryDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeRuleDTO;
import com.wuxiu.galaxy.dal.domain.ChargeRules;
import com.wuxiu.galaxy.dal.manager.ChargeRulesManager;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.ChargeRuleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public Long saveChargeRule(SaveChargeRuleDTO chargeRuleDTO) {
        log.info("新增/编辑计费规则， chargeRuleDTO:{}", chargeRuleDTO);

        // 参数校验
        checkParams(chargeRuleDTO);

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
    private void checkParams(SaveChargeRuleDTO chargeRuleDTO) {
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

        // 编辑计费规则时，要对该计费规则下绑定的计费模板进行校验，至少需要一个计费模板
        if (Objects.isNull(chargeRuleDTO.getChargeTemplateDTOList()) ||
                chargeRuleDTO.getChargeTemplateDTOList().size() < 1) {

            log.warn("计费规则至少需要绑定一个计费模板");
            throw new BizException("计费规则至少需要绑定一个计费模板");
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
