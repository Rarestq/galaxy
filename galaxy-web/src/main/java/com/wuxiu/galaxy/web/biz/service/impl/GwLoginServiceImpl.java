package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.LoginDTO;
import com.wuxiu.galaxy.dal.common.utils.BeanCopierUtil;
import com.wuxiu.galaxy.integration.LoginClient;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.web.biz.form.LoginForm;
import com.wuxiu.galaxy.web.biz.service.GwLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录相关服务
 *
 * @author: wuxiu
 * @date: 2019/5/1 14:58
 */
@Slf4j
@Service
public class GwLoginServiceImpl implements GwLoginService {

    @Autowired
    private LoginClient loginClient;

    /**
     * 登录校验
     *
     * @param loginForm
     * @return
     */
    @Override
    public APIResult<AdminInfoDTO> checkLogin(LoginForm loginForm) {
        LoginDTO loginDTO = BeanCopierUtil.convert(loginForm, LoginDTO.class);
        APIResult<AdminInfoDTO> adminInfoAPIResult = loginClient.checkLogin(loginDTO);
        if (!adminInfoAPIResult.isSuccess()) {
            log.warn("登录校验失败, result:{}, form:{}",
                    adminInfoAPIResult, loginForm);
            return CommonUtil.errorAPIResult(adminInfoAPIResult);
        }
        AdminInfoDTO adminInfoDTO = adminInfoAPIResult.getData();

        return APIResult.ok(adminInfoDTO);
    }
}
