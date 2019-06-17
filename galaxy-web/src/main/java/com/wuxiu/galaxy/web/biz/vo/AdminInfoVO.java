package com.wuxiu.galaxy.web.biz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 管理员信息页面展示对象
 *
 * @author: wuxiu
 * @date: 2019/4/15 11:39
 */
@ApiModel("管理员信息页面展示对象")
@Data
public class AdminInfoVO implements Serializable {

    private static final long serialVersionUID = -1017277063798578639L;

    /**
     * 管理员id(主键)
     */
    @ApiModelProperty(value = "管理员id", required = true)
    private Long adminId;
    /**
     * 管理员编号(随机生成)
     */
    @ApiModelProperty(value = "管理员编号", required = true)
    private String adminNo;
    /**
     * 管理员姓名
     */
    @ApiModelProperty(value = "管理员姓名", required = true)
    private String adminName;
    /**
     * 管理员电话
     */
    @ApiModelProperty(value = "管理员电话", required = true)
    private String adminPhone;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码", required = true)
    private String password;
    /**
     * 管理员类型(1-普通管理员，2-超级管理员,3-系统)
     */
    @ApiModelProperty(value = "管理员类型(1-普通管理员，2-超级管理员,3-系统)", required = true)
    private String adminType;
    /**
     * 创建时间
     */
//    @JsonFormat(pattern = CommonConstant.TIME_PATTERN)
    @ApiModelProperty(value = "创建时间", required = true)
    private String gmtCreate;
    /**
     * 修改时间
     */
//    @JsonFormat(pattern = CommonConstant.TIME_PATTERN)
    @ApiModelProperty(value = "修改时间", required = true)
    private String gmtModified;
}
