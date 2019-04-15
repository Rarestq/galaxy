package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoQueryDTO;
import com.wuxiu.galaxy.api.service.AdminFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 11:59
 */
@Service
public class AdminClient {

    @Autowired
    private AdminFacade adminFacade;

    /**
     * 新增/编辑管理员信息
     *
     * @param adminInfoDTO
     * @return
     */
    public APIResult<Long> saveAdminInfo(AdminInfoDTO adminInfoDTO) {
        return adminFacade.saveAdminInfo(adminInfoDTO);
    }

    /**
     * 查询管理员信息
     *
     * @param queryDTO
     * @return
     */
    public APIResult<PageInfo<AdminDTO>> queryAdminInfoList(AdminInfoQueryDTO queryDTO) {
        return adminFacade.queryAdminInfoList(queryDTO);
    }
}