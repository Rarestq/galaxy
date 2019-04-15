package com.wuxiu.galaxy.api.dto;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 管理员信息查询对象
 *
 * @author: wuxiu
 * @date: 2019/4/15 15:53
 */
@ApiModel(description = "管理员信息查询对象")
@Data
public class AdminInfoQueryDTO extends PageInfo {

    private static final long serialVersionUID = -209331198404294812L;

    /**
     * 管理员id
     */
    private Long adminId;

}
