package com.wuxiu.galaxy.web.biz.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
