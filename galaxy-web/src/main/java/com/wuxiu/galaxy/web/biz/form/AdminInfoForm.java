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
     * 管理员id(主键)
     */
    @ApiModelProperty(value = "管理员id", required = false)
    private Long adminId;
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
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码", required = true)
    @NotNull(message = "登录密码不能为空")
    private String password;

    /**
     * 管理员类型(1-普通管理员，2-超级管理员,3-系统)
     */
    @ApiModelProperty(value = "管理员类型(1-普通管理员，2-超级管理员,3-系统)", required = true)
    @NotNull(message = "管理员类型不能为空")
    private Integer adminType;
}
