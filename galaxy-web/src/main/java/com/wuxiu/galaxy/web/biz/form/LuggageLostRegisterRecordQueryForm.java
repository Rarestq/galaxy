package com.wuxiu.galaxy.web.biz.form;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行李遗失登记记录查询参数表单
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:33
 */
@ApiModel("行李遗失登记记录查询参数表单")
@Data
public class LuggageLostRegisterRecordQueryForm extends PageInfo {

    private static final long serialVersionUID = 5697799673656389828L;

    /**
     * 查询条件(遗失登记记录编号、寄存人姓名)
     */
    @ApiModelProperty(value = "查询条件(遗失登记记录编号、寄存人姓名)", required = false)
    private String queryCondition;

    /**
     * 遗失记录状态(0-遗失，1-已赔偿)
     */
    @ApiModelProperty(value = "遗失记录状态(0-遗失，1-已赔偿)", required = false)
    private Integer status;

    /**
     * 行李遗失登记时间
     */
    @ApiModelProperty(value = "行李遗失登记时间", required = false)
    private String lostTime;
}
