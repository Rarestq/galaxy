package com.wuxiu.galaxy.web.biz.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 管理员信息表单
 *
 * @author: wuxiu
 * @date: 2019/4/15 11:40
 */
@ApiModel("管理员信息表单")
@Data
public class AdminInfoForm implements Serializable {

    private static final long serialVersionUID = -3878144997576152012L;

    /**
     * 管理员姓名
     */
    @ApiModelProperty(value = "管理员姓名", required = true)
    @NotNull(message = "管理员姓名不能为空")
    private String adminName;
    /**
     * 管理员电话
     */
    @ApiModelProperty(value = "管理员电话", required = true)
    @NotNull(message = "管理员电话不能为空")
    private String adminPhone;
    /**
     * 管理员性别(0-男，1-女)
     */
    @ApiModelProperty(value = "管理员性别(0-男，1-女)", required = true)
    @NotNull(message = "管理员性别不能为空")
    private Integer gender;
}
