package com.wuxiu.galaxy.web.biz.form;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行李遗失赔偿记录参数查询表单
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:30
 */
@ApiModel("行李遗失赔偿记录参数查询表单")
@Data
public class LuggageLostCompensateRecordQueryForm extends PageInfo {

    private static final long serialVersionUID = -7194370096686614596L;

    /**
     * 查询条件(管理员姓名、赔偿对象姓名)
     */
    @ApiModelProperty(value = "赔偿对象姓名", required = false)
    private String queryCondition;
    /**
     * 行李类型
     */
    @ApiModelProperty(value = "行李类型", required = false)
    private Integer luggageTypeId;
}
