package com.wuxiu.galaxy.dal.common.dto;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.dal.domain.Admin;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 管理员信息查询对象
 *
 * @author: wuxiu
 * @date: 2019/4/15 20:17
 */
@ApiModel(description = "管理员信息查询对象")
@Data
public class AdminInfoQueryDTO implements Serializable {

    private static final long serialVersionUID = -209331198404294812L;

    /**
     * 分页条件
     */
    Page<Admin> page;

    /**
     * 管理员id
     */
    private Long adminId;

}
