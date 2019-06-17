package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.web.biz.form.LoginForm;
import com.wuxiu.galaxy.web.biz.service.GwLoginService;
import com.wuxiu.galaxy.web.utils.ValidatorUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

import static com.wuxiu.galaxy.api.common.enums.GlobalErrorCodeEnum.*;

/**
 * 登录
 *
 * @author: wuxiu
 * @date: 2019/4/2 14:51
 */
@Slf4j
@RequestMapping("/luggage_storage/api/login")
@RestController
public class LoginController {

    @Autowired
    private GwLoginService loginService;

    private static final String ADMIN_STRING = "admin";

    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(value = "/login")
    public APIResult login(@RequestBody @Valid LoginForm loginForm, HttpServletRequest request) {
        String loginCheck = ValidatorUtil.returnAnyMessageIfError(loginForm);
        if (StringUtils.isNotEmpty(loginCheck)) {
            return APIResult.error(loginCheck);
        }

        try {
            APIResult<AdminInfoDTO> adminInfoDTOAPIResult = loginService.checkLogin(loginForm);
            if (!adminInfoDTOAPIResult.isSuccess()) {
                return APIResult.error(LOGIN_FAILURE.getCode(), LOGIN_FAILURE.getMessage());
            }

            AdminInfoDTO adminInfoDTO = adminInfoDTOAPIResult.getData();
            request.getSession().setAttribute("adminInfoDTO",
                    adminInfoDTO);
            log.info("login -> 用户 " + adminInfoDTO.getAdminName() + " 已登录 ");
        } catch (Exception ex) {
            return APIResult.error(LOGIN_FAILURE.getCode(), LOGIN_FAILURE.getMessage());
        }

        return APIResult.ok(SUCCESS.getCode());
    }

    @ApiOperation(value = "登出", notes = "登出")
    @PostMapping(value = "/logout")
    public APIResult logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(ADMIN_STRING);
        if (Objects.nonNull(session.getAttribute(ADMIN_STRING))) {
            return APIResult.error(LOGOUT_FAILURE.getCode(), LOGOUT_FAILURE.getMessage());
        }

        log.info("logout -> 用户已注销 ");
        return APIResult.ok();
    }

}
