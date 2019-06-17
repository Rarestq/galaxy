package com.wuxiu.galaxy.service.core.biz.service.apiservice;

import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LoginDTO;

/**
 * 登录服务
 *
 * @author: wuxiu
 * @date: 2019/4/2 14:34
 */
public interface LoginService {

    /**
     * 登录校验，根据手机号和密码->检查数据中是否存在该用户
     *
     * @param loginDTO
     * @return
     */
    AdminInfoDTO checkLogin(LoginDTO loginDTO);

}
