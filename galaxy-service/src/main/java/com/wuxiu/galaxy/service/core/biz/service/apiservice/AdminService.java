package com.wuxiu.galaxy.service.core.biz.service.apiservice;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoQueryDTO;

import java.util.List;

/**
 * 管理员服务接口
 *
 * @author: wuxiu
 * @date: 2019/4/14 20:57
 */
public interface AdminService {

    /**
     * 新增/编辑管理员信息
     *
     * @param adminInfoDTO
     * @return
     */
    Long saveAdminInfo(AdminInfoDTO adminInfoDTO);

    /**
     * 查询管理员信息
     *
     * @param queryDTO
     * @return
     */
    PageInfo<AdminDTO> queryAdminInfoList(AdminInfoQueryDTO queryDTO);

    /**
     * 删除管理员信息
     *
     * @param adminIds
     * @return
     */
    void deleteAdmin(List<Long> adminIds);

    /**
     * 根据管理员姓名查看管理员信息
     *
     * @param adminName
     * @return
     */
    AdminDTO findByName(String adminName);


}
