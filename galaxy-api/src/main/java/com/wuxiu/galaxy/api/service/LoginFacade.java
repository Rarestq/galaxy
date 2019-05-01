package com.wuxiu.galaxy.api.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LoginDTO;

/**
 * 登录相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/1 15:11
 */
public interface LoginFacade {

    /**
     * 登录校验
     *
     * @param loginDTO
     * @return
     */
    APIResult<AdminInfoDTO> checkLogin(LoginDTO loginDTO);
}
