package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.dto.OperateUserDTO;

/**
 *
 * @author: wuxiu
 * @date: 2019/4/15 15:42
 */
public interface UserService {

    /**
     * 获取当前操作人
     *
     * @return OperateUserDTO
     */
    OperateUserDTO getCurrentOperateUser();
}
