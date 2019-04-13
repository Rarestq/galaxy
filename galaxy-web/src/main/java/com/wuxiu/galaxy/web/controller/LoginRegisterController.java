package com.wuxiu.galaxy.web.controller;

import com.wuxiu.galaxy.common.entity.APIResult;
import com.wuxiu.galaxy.dal.domain.User;
import com.wuxiu.galaxy.dal.manager.UserManager;
import com.wuxiu.galaxy.service.core.LoginRegisterService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.wuxiu.galaxy.common.constant.GalaxyURL.LISSANDRA_LOGIN;
import static com.wuxiu.galaxy.common.constant.GalaxyURL.LISSANDRA_LOGOUT;
import static com.wuxiu.galaxy.common.constant.GalaxyURL.LISSANDRA_REGISTRE;
import static com.wuxiu.galaxy.common.enums.GlobalErrorCode.*;


/**
 * 登录
 *
 * @author: wuxiu
 * @date: 2019/4/2 14:51
 */
@Slf4j
@RequestMapping("/login")
@RestController
public class LoginRegisterController {

    @Autowired
    private LoginRegisterService loginRegisterService;
    @Autowired
    private UserManager userManager;


    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(value = LISSANDRA_LOGIN)
    public APIResult login(@RequestBody User user, HttpServletRequest request) {
        user = loginRegisterService.checkLogin(user);
        if (null != user){
            request.getSession().setAttribute("user",user);
            log.info("login -> " + user.getUserName() + "用户已登录 ");
            return APIResult.ok(SUCCESS.getMessage());
        }
        return APIResult.error(LOGIN_FAILURE.getCode(),LOGIN_FAILURE.getMessage());
    }

    @ApiOperation(value = "登出", notes = "登出")
    @PostMapping(value = LISSANDRA_LOGOUT)
    public APIResult logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        if (null == session.getAttribute("user")) {
            log.info("logout -> 用户已注销 ");
            return APIResult.ok();
        }
        return APIResult.error(LOGOUT_FAILURE.getCode(),LOGOUT_FAILURE.getMessage());
    }


    @ApiOperation(value = "注册", notes = "注册")
    @PostMapping(value = LISSANDRA_REGISTRE)
    public APIResult register(@RequestBody User user){
        if (null == user.getPhone() || user.getPhone().isEmpty()) {
            log.info("register -> 用户注册失败，未检测到手机号 ");
            return APIResult.error(REGISTER_FAILURE.getCode(), REGISTER_FAILURE.getMessage());
        }
        //todo:后期需要对手机号进行参数校验

        if (loginRegisterService.checkRegister(user.getPhone())) {
            return APIResult.ok(loginRegisterService.registerUser(user));
        }
        return APIResult.error(REGISTER_FAILURE.getCode(), REGISTER_FAILURE.getMessage());
    }
}
