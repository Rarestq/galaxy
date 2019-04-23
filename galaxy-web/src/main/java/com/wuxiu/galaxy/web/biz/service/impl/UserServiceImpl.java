package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.web.biz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * All rights Reserved, Designed By www.maihaoche.com
 *
 * @author: wuxiu
 * @date: 2019/4/15 16:00
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    /**
     * 获取当前操作人
     *
     * @return OperateUserDTO
     */
    @Override
    public OperateUserDTO getCurrentOperateUser() {
        // todo:获取当前登录人信息
        return null;
    }
}
