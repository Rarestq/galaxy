package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.wuxiu.galaxy.api.common.expection.BizException;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LoginDTO;
import com.wuxiu.galaxy.dal.domain.Admin;
import com.wuxiu.galaxy.dal.manager.AdminManager;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        String loginCheck = ValidatorUtil.returnAnyMessageIfError(loginDTO);
        if (StringUtils.isNotEmpty(loginCheck)) {
            log.info("登录校验，参数错误：{}", loginCheck);
            throw new ParamException(loginCheck);
        }

        com.wuxiu.galaxy.dal.common.dto.LoginDTO dto =
                new com.wuxiu.galaxy.dal.common.dto.LoginDTO();
        dto.setAdminName(loginDTO.getAdminName());
        dto.setPassword(loginDTO.getPassword());

        // 根据电话号码和登录密码查询管理员信息
        Admin admin = adminManager.getAdminInfoByNameAndPwd(dto);

        return buildAdminInfoDTO(admin);
    }

    /**
     * 构造 AdminInfoDTO 对象
     *
     * @param admin
     * @return
     */
    private AdminInfoDTO buildAdminInfoDTO(Admin admin) {
        if (Objects.isNull(admin)) {
            throw new BizException("该用户不存在，请检查用户名和密码信息");
        }

        AdminInfoDTO adminInfoDTO = new AdminInfoDTO();
        adminInfoDTO.setAdminId(admin.getAdminId());
        adminInfoDTO.setAdminNo(admin.getAdminNo());
        adminInfoDTO.setAdminName(admin.getAdminName());
        adminInfoDTO.setAdminPhone(admin.getAdminPhone());
        adminInfoDTO.setPassword(admin.getPassword());
        adminInfoDTO.setAdminType(admin.getAdminType());

        return adminInfoDTO;
    }
}
