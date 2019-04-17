/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.api.dto.AdminDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.dal.common.dto.AdminInfoQueryDTO;
import com.wuxiu.galaxy.dal.dao.AdminDao;
import com.wuxiu.galaxy.dal.domain.Admin;
import com.wuxiu.galaxy.dal.manager.AdminManager;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;

/**
 * <p>AdminManager</p>
 * <p>
 * 管理员表 - 按照编号前缀的不同具有不同权限
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-16
 */
@Component
public class AdminManagerImpl extends BaseManagerImpl<AdminDao, Admin> implements AdminManager{

    /**
     * 新增/编辑管理员信息
     *
     * @param adminInfoDTO
     * @return
     */
    @Override
    public Long saveAdminInfo(AdminInfoDTO adminInfoDTO) {

        if (Objects.nonNull(adminInfoDTO.getAdminId())) {
            // 编辑管理员信息
            Admin updateAdmin = new Admin();
            updateAdmin.setAdminId(adminInfoDTO.getAdminId());
            updateAdmin.setAdminNo(adminInfoDTO.getAdminNo());
            updateAdmin.setAdminName(adminInfoDTO.getAdminName());
            updateAdmin.setAdminPhone(adminInfoDTO.getAdminPhone());
            updateAdmin.setGender(adminInfoDTO.getGender());
            updateAdmin.setGmtModified(LocalDateTime.now());

            updateById(updateAdmin);

            return adminInfoDTO.getAdminId();
        }

        // 新增管理员信息
        Admin insertAdmin = new Admin();
        insertAdmin.setAdminNo(adminInfoDTO.getAdminNo());
        insertAdmin.setAdminName(adminInfoDTO.getAdminName());
        insertAdmin.setAdminPhone(adminInfoDTO.getAdminPhone());
        insertAdmin.setGender(adminInfoDTO.getGender());

        // 新增管理员信息
        insert(insertAdmin);

        return insertAdmin.getAdminId();
    }

    /**
     * 查询管理员信息
     *
     * @param queryDTO
     * @return
     */
    @Override
    public Page<AdminDTO> queryAdminInfoList(AdminInfoQueryDTO queryDTO) {
        // 构造查询参数
        EntityWrapper<Admin> wrapper = new EntityWrapper<>();
        if (Objects.nonNull(queryDTO.getAdminId())) {
            wrapper.eq("admin_id", queryDTO.getAdminId());
        }

        if (Objects.nonNull(queryDTO.getAdminNo())) {
            wrapper.eq("admin_no", queryDTO.getAdminNo());
        }

        if (Objects.nonNull(queryDTO.getAdminName())) {
            wrapper.eq("admin_name", queryDTO.getAdminName());
        }

        if (Objects.nonNull(queryDTO.getGender())) {
            wrapper.eq("gender", queryDTO.getGender());
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("admin_id", false);

        // 获取管理员信息
        Page<Admin> adminPage = selectPage(queryDTO.getPage(), wrapper);

        return buildAdminDTOS(adminPage);
    }

    /**
     * 构造 AdminDTO 列表对象
     *
     * @param adminPage
     * @return
     */
    private Page<AdminDTO> buildAdminDTOS(Page<Admin> adminPage) {

        List<AdminDTO> adminDTOS = newArrayList();
        List<Admin> adminList = adminPage.getRecords();

        // 将 Admin 对象转化为 AdminDTO 对象
        adminList.forEach(admin -> {
            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setAdminId(admin.getAdminId());
            adminDTO.setAdminNo(admin.getAdminNo());
            adminDTO.setAdminName(admin.getAdminName());
            adminDTO.setAdminPhone(admin.getAdminPhone());
            adminDTO.setGender(admin.getGender());
            adminDTOS.add(adminDTO);
        });

        Page<AdminDTO> page = new Page<>();
        page.setRecords(adminDTOS);
        page.setTotal(adminPage.getTotal());

        return page;
    }
}
