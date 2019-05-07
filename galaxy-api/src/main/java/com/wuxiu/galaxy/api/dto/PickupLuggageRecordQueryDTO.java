package com.wuxiu.galaxy.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 行李取件记录查询参数对象
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:16
 */
@ApiModel("行李取件记录查询参数对象")
@Data
public class PickupLuggageRecordQueryDTO extends PageInfo {

    private static final long serialVersionUID = 6883399169024073300L;

    /**
     * 行李寄存记录编号
     */
    @ApiModelProperty(value = "行李寄存记录编号", required = false)
    private String luggageRecordNo;

    /**
     * 行李寄存者姓名
     */
    @ApiModelProperty(value = "行李寄存者姓名", required = false)
    private String depositorName;

    /**
     * 取件时间
     */
    @JsonFormat(pattern = CommonConstant.TIME_PATTERN)
    @ApiModelProperty(value = "取件时间", required = false)
    private LocalDateTime pickupTime;
}
