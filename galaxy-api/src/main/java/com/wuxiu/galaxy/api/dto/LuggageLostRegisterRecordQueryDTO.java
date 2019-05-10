package com.wuxiu.galaxy.api.dto;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行李遗失登记记录查询参数对象
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:33
 */
@ApiModel("行李遗失登记记录查询参数对象")
@Data
public class LuggageLostRegisterRecordQueryDTO extends PageInfo {

    private static final long serialVersionUID = 5697799673656389828L;

    /**
     * 行李所属者姓名
     */
    @ApiModelProperty(value = "行李所属者姓名", required = false)
    private String depositorName;

    /**
     * 遗失登记记录编号
     */
    @ApiModelProperty(value = "遗失登记记录编号", required = false)
    private String lostRecordNo;

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
