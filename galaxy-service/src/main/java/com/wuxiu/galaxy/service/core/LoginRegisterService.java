package com.wuxiu.galaxy.service.core;

/**
 * 登录服务
 *
 * @author: wuxiu
 * @date: 2019/4/2 14:34
 */
public interface LoginRegisterService {

    /**
     * 登录校验，根据手机号和密码->检查数据中是否存在该用户
     *
     * @param user
     * @return
     */
    //User checkLogin(User user);

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    //Integer registerUser(User user);

    /**
     * 注册校验，根据手机号去查是否有已经存在的用户
     *
     * @param phone
     * @return
     */
    boolean checkRegister(String phone);

}
