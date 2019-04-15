package com.wuxiu.galaxy.api.dto;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 管理员信息搜索对象
 *
 * @author: wuxiu
 * @date: 2019/4/15 15:53
 */
@ApiModel(description = "管理员信息搜索对象")
@Data
public class AdminInfoSearchDTO extends PageInfo {

    private static final long serialVersionUID = -209331198404294812L;

    /**
     * 管理员id
     */
    private Long adminId;

    /**
     * 管理员编号
     */
    private String adminNo;

    /**
     * 管理员姓名
     */
    private String adminName;

    /**
     * 管理员性别(0-男，1-女)
     */
    private Integer gender;
}
