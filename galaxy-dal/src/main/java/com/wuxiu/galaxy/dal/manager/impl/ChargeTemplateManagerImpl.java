/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.api.common.enums.ChargeCalculationTypeEnum;
import com.wuxiu.galaxy.api.common.enums.ChargeTemplateStatusEnum;
import com.wuxiu.galaxy.api.dto.ChargeCalculationRuleDTO;
import com.wuxiu.galaxy.api.dto.ChargeTemplateDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeTemplateDTO;
import com.wuxiu.galaxy.dal.common.dto.ChargeTemplateQueryDTO;
import com.wuxiu.galaxy.dal.common.utils.StreamUtil;
import com.wuxiu.galaxy.dal.dao.ChargeTemplateDao;
import com.wuxiu.galaxy.dal.domain.ChargeCalculationRules;
import com.wuxiu.galaxy.dal.domain.ChargeTemplate;
import com.wuxiu.galaxy.dal.domain.ChargeTemplateCalculationRelation;
import com.wuxiu.galaxy.dal.manager.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

/**
 * <p>ChargeTemplateManager</p>
 * <p>
 * 计费模板表
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-16
 */
@Component
public class ChargeTemplateManagerImpl extends BaseManagerImpl<ChargeTemplateDao, ChargeTemplate> implements ChargeTemplateManager{

    @Autowired
    private ChargeTemplateCalculationRelationManager relationManager;

    @Autowired
    private ChargeCalculationRulesManager calculationRulesManager;

    @Autowired
    private FixedChargeCalculationDetailManager fixedChargeCalculationDetailManager;

    @Autowired
    private CycleChargeCalculationDetailManager cycleChargeCalculationDetailManager;

    /**
     * 新增/编辑计费模板
     *
     * @param templateDTO
     * @return 计费模板主键id
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Long saveChargeTemplate(SaveChargeTemplateDTO templateDTO) {

        List<ChargeCalculationRuleDTO> calculationRuleDTOS =
                templateDTO.getChargeCalculationRuleDTOList();
        List<Long> calculationRuleIds = StreamUtil.collectDistinctKeyProperty(
                calculationRuleDTOS, ChargeCalculationRuleDTO::getCalculationRuleId);

        if (Objects.nonNull(templateDTO.getChargeTemplateId())) {
            // 编辑计费模板信息
            ChargeTemplate updateChargeTemplate = new ChargeTemplate();
            updateChargeTemplate.setChargeTemplateId(templateDTO.getChargeTemplateId());
            updateChargeTemplate.setChargeTemplateNo(templateDTO.getChargeTemplateNo());
            updateChargeTemplate.setChargeTemplateName(templateDTO.getChargeTemplateName());
            updateChargeTemplate.setChargeTypeId(templateDTO.getChargeTypeId());
            updateChargeTemplate.setMinFee(templateDTO.getMinFee());
            updateChargeTemplate.setMaxFee(templateDTO.getMaxFee());
            updateChargeTemplate.setGmtModified(LocalDateTime.now());

            updateById(updateChargeTemplate);

            // 同步更新计费模板-计算规则关联表的信息(先逻辑删除原来的，再添加)
            relationManager.refreshAll(templateDTO.getChargeTemplateId(), calculationRuleIds);

            return updateChargeTemplate.getChargeTemplateId();
        }

        // 新增计费模板信息
        ChargeTemplate insertChargeTemplate = new ChargeTemplate();
        insertChargeTemplate.setChargeTemplateNo(templateDTO.getChargeTemplateNo());
        insertChargeTemplate.setChargeTemplateName(templateDTO.getChargeTemplateName());
        insertChargeTemplate.setChargeTypeId(templateDTO.getChargeTypeId());
        insertChargeTemplate.setMinFee(templateDTO.getMinFee());
        insertChargeTemplate.setMaxFee(templateDTO.getMaxFee());

        insert(insertChargeTemplate);

        // 同步更新计费模板-计算规则关联表的信息
        relationManager.insertNew(templateDTO.getChargeTemplateId(), calculationRuleIds);

        return insertChargeTemplate.getChargeRuleId();
    }

    /**
     * 根据计费模板名称查询计费模板信息
     *
     * @param chargeTemplateName
     * @return
     */
    @Override
    public ChargeTemplate getChargeTemplateByName(String chargeTemplateName) {
        Wrapper<ChargeTemplate> wrapper = new EntityWrapper<>();
        wrapper.eq("charge_template_name", chargeTemplateName);

        return selectOne(wrapper);
    }

    /**
     * 查询计费模板列表
     *
     * @param queryDTO
     * @return
     */
    @Override
    public Page<ChargeTemplateDTO> getChargeTemplates(
            ChargeTemplateQueryDTO queryDTO) {

        // 构造查询参数
        EntityWrapper<ChargeTemplate> wrapper = new EntityWrapper<>();
        if (Objects.nonNull(queryDTO.getChargeTemplateId())) {
            wrapper.eq("charge_template_id", queryDTO.getChargeTemplateId());
        }

        if (Objects.nonNull(queryDTO.getChargeTemplateName())) {
            wrapper.eq("charge_template_name", queryDTO.getChargeTemplateName());
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("charge_template_id", false);

        // 获取计费规则列表信息
        Page<ChargeTemplate> templatePage = selectPage(queryDTO.getPage(), wrapper);

        return buildChargeTemplateDTOS(templatePage);
    }

    /**
     * 禁用/启用计费模板
     *
     * @param chargeTemplateId
     */
    @Override
    public void enableOrDisableTemplate(Long chargeTemplateId) {
        if (Objects.isNull(chargeTemplateId)) {
            return;
        }

        // 获取计费模板实例
        ChargeTemplate template = selectById(chargeTemplateId);

        ChargeTemplate chargeTemplate = new ChargeTemplate();

        // 如果之前是启用，则改为禁用状态
        if (Objects.equals(template.getChargeTemplateStatus(),
                ChargeTemplateStatusEnum.ENABLE.getCode())) {
            chargeTemplate.setGmtModified(LocalDateTime.now());
            chargeTemplate.setChargeTemplateId(template.getChargeTemplateId());
            chargeTemplate.setChargeTemplateStatus(
                    ChargeTemplateStatusEnum.DISABLE.getCode());
        }

        // 如果之前是禁用，则改为启用状态
        if (Objects.equals(template.getChargeTemplateStatus(),
                ChargeTemplateStatusEnum.DISABLE.getCode())) {
            chargeTemplate.setGmtModified(LocalDateTime.now());
            chargeTemplate.setChargeTemplateId(template.getChargeTemplateId());
            chargeTemplate.setChargeTemplateStatus(ChargeTemplateStatusEnum.ENABLE.getCode());
        }

        // 更新该计费模板状态信息
        updateById(chargeTemplate);
    }

    /**
     * 构造 ChargeTemplateDTO 列表信息
     *
     * @param templatePage
     * @return
     */
    private Page<ChargeTemplateDTO> buildChargeTemplateDTOS(
            Page<ChargeTemplate> templatePage) {

        List<ChargeTemplateDTO> templateDTOS = newArrayList();
        List<ChargeTemplate> chargeTemplates = templatePage.getRecords();
        List<Long> chargeTemplateIds =
                StreamUtil.convert(chargeTemplates, ChargeTemplate::getChargeTemplateId);

        // 通过计费模板id从计费模板-计算规则关联表中查询其对应的计费规则信息
        List<ChargeTemplateCalculationRelation> calculationRelations =
                relationManager.selectCalculateRulesByTemplateIds(chargeTemplateIds);

        // 将查询到的计算规则信息按照计费模板id进行分组
        Map<Long, List<ChargeTemplateCalculationRelation>> templateCalcuRuleMap =
                calculationRelations.stream().collect(Collectors.groupingBy
                        (ChargeTemplateCalculationRelation::getChargeTemplateId));

        List<Long> calculateRuleIds = StreamUtil.convert(calculationRelations,
                ChargeTemplateCalculationRelation::getCalculationRuleId);
        // 查询计算规则信息
        List<ChargeCalculationRules> chargeCalculationRules =
                calculationRulesManager.selectBatchIds(calculateRuleIds);


        //todo: 将 ChargeCalculationRules 转化为 ChargeCalculationRuleDTO
        List<ChargeCalculationRuleDTO> calculationRuleDTOS = newArrayList();
        StreamUtil.convert(chargeCalculationRules, calculationRule -> {
            ChargeCalculationRuleDTO calculationRuleDTO = new ChargeCalculationRuleDTO();
            calculationRuleDTO.setCalculationRuleId(calculationRule.getCalculationRuleId());
            if (Objects.equals(calculationRule.getCalculationType(),
                    ChargeCalculationTypeEnum.FIXED_CHARGE_CALCULATION.getCode())) {
                calculationRuleDTO.setCalculationType(
                        ChargeCalculationTypeEnum.FIXED_CHARGE_CALCULATION.getCode());
            }

            if (Objects.equals(calculationRule.getCalculationType(),
                    ChargeCalculationTypeEnum.CYCLE_CHARGE_CALCULATION.getCode())) {
                calculationRuleDTO.setCalculationType(
                        ChargeCalculationTypeEnum.CYCLE_CHARGE_CALCULATION.getCode());
            }

            calculationRuleDTOS.add(calculationRuleDTO);

            return calculationRuleDTOS;
        });

        // 将 ChargeTemplate 对象转化为 ChargeTemplateDTO 对象
        chargeTemplates.forEach(chargeTemplate -> {
            ChargeTemplateDTO templateDTO = new ChargeTemplateDTO();
            templateDTO.setChargeTemplateId(chargeTemplate.getChargeTemplateId());
            templateDTO.setChargeTemplateNo(chargeTemplate.getChargeTemplateNo());
            templateDTO.setChargeTemplateName(chargeTemplate.getChargeTemplateName());
            templateDTO.setChargeTypeId(chargeTemplate.getChargeTypeId());
            templateDTO.setMaxFee(chargeTemplate.getMaxFee());
            templateDTO.setMinFee(chargeTemplate.getMinFee());

            List<ChargeTemplateCalculationRelation> calculationRelationList =
                    templateCalcuRuleMap.get(chargeTemplate.getChargeTemplateId());



            //todo: 将查询到的计算规则信息转化为 ChargeCalculationRuleDTO,并添加到 templateDTO 中

            //templateDTO.setChargeCalculationRuleDTOList();
            templateDTOS.add(templateDTO);
        });

        Page<ChargeTemplateDTO> page = new Page<>();
        page.setRecords(templateDTOS);
        page.setTotal(templatePage.getTotal());

        return page;
    }
}
