/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.base.BaseManager;
import com.wuxiu.galaxy.api.dto.AdminDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.dal.common.dto.AdminInfoQueryDTO;
import com.wuxiu.galaxy.dal.domain.Admin;

/**  
 * AdminManager
 * 管理员表 - 按照编号前缀的不同具有不同权限
 * 
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-16
 */
public interface AdminManager extends BaseManager<Admin> {

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
    Page<AdminDTO> queryAdminInfoList(AdminInfoQueryDTO queryDTO);
}
