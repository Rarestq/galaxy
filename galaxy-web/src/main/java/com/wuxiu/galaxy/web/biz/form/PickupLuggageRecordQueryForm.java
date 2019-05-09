package com.wuxiu.galaxy.web.biz.form;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行李取件记录查询参数表单
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:16
 */
@ApiModel("行李取件记录查询参数表单")
@Data
public class PickupLuggageRecordQueryForm extends PageInfo {

    private static final long serialVersionUID = 6883399169024073300L;

    /**
     * 行李寄存者姓名
     */
    @ApiModelProperty(value = "行李寄存者姓名", required = false)
    private String depositorName;

    /**
     * 取件时间
     */
    @ApiModelProperty(value = "取件时间", required = false)
    private String pickupTime;
}
