package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.web.biz.form.LoginForm;

/**
 * 登录相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/1 15:06
 */
public interface GwLoginService {

    /**
     * 登录校验
     *
     * @param loginForm
     * @return
     */
    APIResult<AdminInfoDTO> checkLogin(LoginForm loginForm);
}
