package com.wuxiu.galaxy.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理员数据转换对象
 *
 * @author: wuxiu
 * @date: 2019/4/15 15:07
 */
@ApiModel(description = "管理员数据转换对象")
@Data
public class AdminDTO implements Serializable {

    private static final long serialVersionUID = 7053393664333430249L;

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
     * 管理员类型(1-普通管理员，2-超级管理员,3-系统)
     */
    @ApiModelProperty(value = "管理员类型(1-普通管理员，2-超级管理员,3-系统)", required = true)
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "创建时间", required = true)
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "修改时间", required = true)
    private LocalDateTime gmtModified;
}
