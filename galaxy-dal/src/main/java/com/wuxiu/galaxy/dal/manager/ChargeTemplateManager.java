/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.dto.ChargeTemplateDTO;
import com.wuxiu.galaxy.api.dto.SaveChargeTemplateDTO;
import com.wuxiu.galaxy.dal.common.dto.ChargeTemplateQueryDTO;
import com.wuxiu.galaxy.dal.domain.ChargeTemplate;
import com.wuxiu.galaxy.api.common.base.BaseManager;

/**  
 * ChargeTemplateManager
 * 计费模板表
 * 
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-16
 */
public interface ChargeTemplateManager extends BaseManager<ChargeTemplate> {

    /**
     * 新增/编辑计费模板
     *
     * @param templateDTO
     * @return 计费模板主键id
     */
    Long saveChargeTemplate(SaveChargeTemplateDTO templateDTO);

    /**
     * 根据计费模板名称查询计费模板信息
     *
     * @param chargeTemplateName
     * @return
     */
    ChargeTemplate getChargeTemplateByName(String chargeTemplateName);

    /**
     * 查询计费模板列表
     *
     * @param templateQueryDTO
     * @return
     */
    Page<ChargeTemplateDTO> getChargeTemplates(ChargeTemplateQueryDTO templateQueryDTO);

    /**
     * 禁用/启用计费模板
     *
     * @param chargeTemplateId
     */
    void enableOrDisableTemplate(Long chargeTemplateId);
}
