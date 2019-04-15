package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoQueryDTO;

/**
 * 管理员服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 14:15
 */
public interface AdminFacade {

    /**
     * 新增/编辑管理员信息
     *
     * @param adminInfoDTO
     * @return
     */
    APIResult<Long> saveAdminInfo(AdminInfoDTO adminInfoDTO);

    /**
     * 查询管理员信息
     *
     * @param queryDTO
     * @return
     */
    APIResult<PageInfo<AdminDTO>> queryAdminInfoList(AdminInfoQueryDTO queryDTO);
}
