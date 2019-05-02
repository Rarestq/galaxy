package com.wuxiu.galaxy.web.biz.form;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 管理员信息查询对象
 *
 * @author: wuxiu
 * @date: 2019/4/15 11:40
 */
@ApiModel("管理员信息查询对象")
@Data
public class AdminInfoQueryForm extends PageInfo {

    private static final long serialVersionUID = -5682748262287484738L;

    /**
     * 管理员编号
     */
    @ApiModelProperty(value = "管理员编号", required = false)
    private String adminNo;

    /**
     * 管理员姓名
     */
    @ApiModelProperty(value = "管理员姓名", required = false)
    private String adminName;

    /**
     * 管理员类型(1-普通管理员，2-超级管理员,3-系统)
     */
    @ApiModelProperty(value = "管理员类型(1-普通管理员，2-超级管理员,3-系统)", required = false)
    private Integer adminType;
}
