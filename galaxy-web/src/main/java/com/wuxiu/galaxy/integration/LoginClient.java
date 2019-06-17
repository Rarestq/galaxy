package com.wuxiu.galaxy.integration;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LoginDTO;
import com.wuxiu.galaxy.api.service.LoginFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/1 15:07
 */
@Service
public class LoginClient {

    @Autowired
    private LoginFacade loginFacade;

    /**
     * 登录校验
     *
     * @param loginDTO
     * @return
     */
    public APIResult<AdminInfoDTO> checkLogin(LoginDTO loginDTO) {
        return loginFacade.checkLogin(loginDTO);
    }
}
