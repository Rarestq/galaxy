package com.wuxiu.galaxy.api.dto;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 营业额记录查询对象
 *
 * @author: wuxiu
 * @date: 2019/4/23 10:14
 */
@ApiModel("营业额记录查询对象")
@Data
public class TurnoverRecordQueryDTO extends PageInfo {

    private static final long serialVersionUID = 6066006292822146828L;

    /**
     * 行李类型id(下拉框)
     */
    @ApiModelProperty(name = "行李类型id(下拉框)", required = false)
    private Long luggageTypeId;
    /**
     * 管理员姓名(模糊搜索)
     */
    @ApiModelProperty(name = "管理员姓名(模糊搜索)", required = false)
    private String adminName;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间", required = false)
    private String gmtCreate;

}
