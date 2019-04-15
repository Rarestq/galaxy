package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoQueryDTO;
import com.wuxiu.galaxy.api.service.AdminFacade;
import com.wuxiu.galaxy.service.core.biz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 12:14
 */
@Service("adminFacade")
public class AdminFacadeImpl implements AdminFacade {

    @Autowired
    private AdminService adminService;

    /**
     * 新增/编辑管理员信息
     *
     * @param adminInfoDTO
     * @return
     */
    @Override
    public APIResult<Long> saveAdminInfo(AdminInfoDTO adminInfoDTO) {
        return APIResult.ok(adminService.saveAdminInfo(adminInfoDTO));
    }

    /**
     * 查询管理员信息
     *
     * @param queryDTO
     * @return
     */
    @Override
    public APIResult<PageInfo<AdminDTO>> queryAdminInfoList(AdminInfoQueryDTO queryDTO) {
        return null;
    }
}
