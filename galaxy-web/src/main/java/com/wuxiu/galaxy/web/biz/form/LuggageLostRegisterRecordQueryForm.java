package com.wuxiu.galaxy.web.biz.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
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
     * 行李所属者姓名
     */
    @ApiModelProperty(value = "行李所属者姓名", required = false)
    private String depositorName;

    /**
     * 行李所属者电话
     */
    @ApiModelProperty(value = "行李所属者电话", required = false)
    private String depositorPhone;

    /**
     * 行李遗失登记时间
     */
    @ApiModelProperty(value = "行李遗失登记时间", required = false)
    private String lostTime;
}
