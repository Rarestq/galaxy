package com.wuxiu.galaxy.service.core.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wuxiu.galaxy.dal.domain.User;
import com.wuxiu.galaxy.dal.manager.UserManager;
import com.wuxiu.galaxy.service.core.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 登录服务实现类
 *
 * @author: wuxiu
 * @date: 2019/4/2 14:36
 */
@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {

    @Autowired
    private UserManager userManager;

    @Override
    public User checkLogin(User user) {
        return userManager.selectOne(user);
    }

    @Override
    public Integer registerUser(User user) {
        return userManager.insert(user);
    }

    @Override
    public boolean checkRegister(String phone) {
        return null == userManager.selectOne(
                new EntityWrapper<User>().eq("phone", phone));
    }

}
