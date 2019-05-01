package com.wuxiu.galaxy.service.core.facade;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LoginDTO;
import com.wuxiu.galaxy.api.service.LoginFacade;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/1 15:12
 */
@Service("loginFacade")
public class LoginFacadeImpl implements LoginFacade {

    @Autowired
    private LoginService loginService;

    /**
     * 登录校验
     *
     * @param loginDTO
     * @return
     */
    @Override
    public APIResult<AdminInfoDTO> checkLogin(LoginDTO loginDTO) {
        return APIResult.ok(loginService.checkLogin(loginDTO));
    }
}
