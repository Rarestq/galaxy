package com.wuxiu.galaxy.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 行李类型对象
 *
 * @author: wuxiu
 * @date: 2019/5/6 20:55
 */
@ApiModel(description = "行李类型对象")
@Data
public class LuggageTypeDTO implements Serializable {

    private static final long serialVersionUID = -8462255442510010156L;

    /**
     * 行李类型主键id
     */
    @ApiModelProperty(value = "行李类型主键id", required = true)
    @NotNull(message = "行李类型主键id不能为空")
    private Long luggageTypeId;
    /**
     * 行李类型(1-普通物件、2-易碎物件、3-贵重物件)
     */
    @ApiModelProperty(value = "行李类型(1-普通物件、2-易碎物件、3-贵重物件)", required = true)
    @NotNull(message = "行李类型不能为空")
    private String luggageType;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    @NotNull(message = "创建时间不能为空")
    private String gmtCreate;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", required = true)
    @NotNull(message = "修改时间不能为空")
    private String gmtModified;
}
