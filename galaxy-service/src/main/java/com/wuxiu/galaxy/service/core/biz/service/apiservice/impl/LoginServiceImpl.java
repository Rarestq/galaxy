package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LoginDTO;
import com.wuxiu.galaxy.dal.domain.Admin;
import com.wuxiu.galaxy.dal.manager.AdminManager;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录服务实现类
 *
 * @author: wuxiu
 * @date: 2019/4/2 14:36
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminManager adminManager;

    @Override
    public AdminInfoDTO checkLogin(LoginDTO loginDTO) {
        log.info("登录校验，params:{}", loginDTO);

        com.wuxiu.galaxy.dal.common.dto.LoginDTO dto =
                new com.wuxiu.galaxy.dal.common.dto.LoginDTO();
        dto.setAdminName(loginDTO.getAdminName());
        dto.setAdminPhone(loginDTO.getAdminPhone());
        dto.setPassword(loginDTO.getPassword());

        // 根据电话号码和登录密码查询管理员信息
        Admin admin = adminManager.getAdminInfoByPhoneAndPwd(dto);

        return buildAdminInfoDTO(admin);
    }

    /**
     * 构造 AdminInfoDTO 对象
     *
     * @param admin
     * @return
     */
    private AdminInfoDTO buildAdminInfoDTO(Admin admin) {
        AdminInfoDTO adminInfoDTO = new AdminInfoDTO();
        adminInfoDTO.setAdminId(admin.getAdminId());
        adminInfoDTO.setAdminNo(admin.getAdminNo());
        adminInfoDTO.setAdminName(admin.getAdminName());
        adminInfoDTO.setAdminPhone(admin.getAdminPhone());
        adminInfoDTO.setPassword(admin.getPassword());

        return adminInfoDTO;
    }
}
