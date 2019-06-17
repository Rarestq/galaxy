package com.wuxiu.galaxy.api.dto;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行李遗失赔偿记录查询参数
 *
 * @author: wuxiu
 * @date: 2019/4/28 09:37
 */
@ApiModel("行李遗失赔偿记录查询参数")
@Data
public class LostCompensateRecordQueryDTO extends PageInfo {

    private static final long serialVersionUID = -4338941191519603214L;

    /**
     * 赔偿记录主键id
     */
    private Long luggageLostCompensationRecordId;
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
