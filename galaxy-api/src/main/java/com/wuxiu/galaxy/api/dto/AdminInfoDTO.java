package com.wuxiu.galaxy.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理员信息
 *
 * @author: wuxiu
 * @date: 2019/4/15 15:53
 */
@ApiModel(description = "管理员数据转换对象")
@Data
public class AdminInfoDTO implements Serializable {

    private static final long serialVersionUID = -3592781130891080756L;

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
     * 管理员类型（默认：admin）
     */
    @ApiModelProperty(value = "管理员类型（默认：admin）", required = true)
    private Integer adminType;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码", required = true)
    private String password;
    /**
     * 管理员状态
     */
    @ApiModelProperty(value = "管理员状态", required = true)
    private Integer deleted;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = CommonConstant.TIME_PATTERN)
    @ApiModelProperty(value = "创建时间", required = true)
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = CommonConstant.TIME_PATTERN)
    @ApiModelProperty(value = "修改时间", required = true)
    private LocalDateTime gmtModified;
}
