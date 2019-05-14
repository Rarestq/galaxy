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
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.api.dto.AdminDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.dal.common.dto.AdminInfoQueryDTO;
import com.wuxiu.galaxy.dal.common.dto.LoginDTO;
import com.wuxiu.galaxy.dal.dao.AdminDao;
import com.wuxiu.galaxy.dal.domain.Admin;
import com.wuxiu.galaxy.dal.manager.AdminManager;
import org.apache.commons.lang3.StringUtils;
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
            updateAdmin.setAdminType(adminInfoDTO.getAdminType());
            updateAdmin.setGmtModified(LocalDateTime.now());

            updateById(updateAdmin);

            return adminInfoDTO.getAdminId();
        }

        // 新增管理员信息
        Admin insertAdmin = new Admin();
        insertAdmin.setAdminNo(adminInfoDTO.getAdminNo());
        insertAdmin.setAdminName(adminInfoDTO.getAdminName());
        insertAdmin.setAdminPhone(adminInfoDTO.getAdminPhone());
        insertAdmin.setAdminType(adminInfoDTO.getAdminType());
        insertAdmin.setPassword(adminInfoDTO.getPassword());

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

        if (StringUtils.isNotBlank(queryDTO.getQueryCondition())) {
            wrapper.like("admin_no", queryDTO.getQueryCondition())
                    .or().like("admin_name", queryDTO.getQueryCondition());
        }
        if (Objects.nonNull(queryDTO.getAdminType())) {
            wrapper.eq("admin_type", queryDTO.getAdminType());
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("admin_id", false);

        // 获取管理员信息
        Page<Admin> adminPage = selectPage(queryDTO.getPage(), wrapper);

        return buildAdminDTOS(adminPage);
    }

    /**
     * 根据管理员姓名查询管理员信息
     *
     * @param adminName
     * @return
     */
    @Override
    public Admin selectAdminByName(String adminName) {
        Wrapper<Admin> wrapper = new EntityWrapper<>();
        wrapper.eq("admin_name", adminName);

        return selectOne(wrapper);
    }

    /**
     * 根据电话号码和登录密码查询管理员信息
     *
     * @param dto
     * @return
     */
    @Override
    public Admin getAdminInfoByNameAndPwd(LoginDTO dto) {
        Wrapper<Admin> wrapper = new EntityWrapper<Admin>()
                .eq("admin_name", dto.getAdminName())
                //.eq("admin_phone", dto.getAdminPhone())
                .eq("password", dto.getPassword());

        return selectOne(wrapper);
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
            adminDTO.setPassword(admin.getPassword());
            adminDTO.setAdminType(admin.getAdminType());
            adminDTO.setGmtCreate(admin.getGmtCreate().toString());
            adminDTO.setGmtModified(admin.getGmtModified().toString());
            adminDTOS.add(adminDTO);
        });

        Page<AdminDTO> page = new Page<>();
        page.setRecords(adminDTOS);
        page.setTotal(adminPage.getTotal());

        return page;
    }
}
