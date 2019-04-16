package com.wuxiu.galaxy.dal.common.dto;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.dal.domain.Admin;
import lombok.Data;

/**
 * 管理员信息搜索对象
 *
 * @author: wuxiu
 * @date: 2019/4/15 15:53
 */
@Data
public class AdminInfoSearchDTO extends PageInfo {

    private static final long serialVersionUID = -209331198404294812L;

    /**
     * 分页条件
     */
    Page<Admin> page;
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