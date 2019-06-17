package com.wuxiu.galaxy.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 登录参数对象
 *
 * @author: wuxiu
 * @date: 2019/5/1 14:51
 */
@ApiModel("登录参数对象")
@Data
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 1257114361697586123L;

    /**
     * 管理员姓名
     */
    @ApiModelProperty(value = "管理员姓名", required = true)
    @NotNull(message = "管理员姓名不能为空")
    private String adminName;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码", required = true)
    @NotNull(message = "登录密码不能为空")
    private String password;

    /**
     * 管理员电话
     */
//    @ApiModelProperty(value = "管理员电话", required = true)
//    @NotNull(message = "管理员电话不能为空")
//    private String adminPhone;
}
