package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.api.common.enums.UserTypeEnum;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.dal.domain.Admin;
import com.wuxiu.galaxy.service.core.base.utils.UUIDGenerateUtil;
import com.wuxiu.galaxy.web.utils.SessionHelper;
import com.wuxiu.galaxy.web.biz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
 * 用户相关服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 16:00
 */
@Slf4j
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private SessionHelper sessionHelper;

    /**
     * 获取当前操作人
     *
     * @return OperateUserDTO
     */
    @Override
    public OperateUserDTO getCurrentOperateUser() {
        // 获取当前登录人信息
        Admin currentUser = sessionHelper.getUser();
        if (Objects.isNull(currentUser)) {
            OperateUserDTO operateUserDTO = new OperateUserDTO();
            operateUserDTO.setOperateUserId(0L);
            operateUserDTO.setOperateUserNo(UUIDGenerateUtil.generateUniqueNo(
                    CommonConstant.SYSTEM_PREFIX));
            operateUserDTO.setName("");
            operateUserDTO.setOperateUserPhone("");
            operateUserDTO.setUserTypeEnum(UserTypeEnum.SYSTEM);

            return operateUserDTO;
        }
        OperateUserDTO operateUserDTO = new OperateUserDTO();
        Long userId = Optional.ofNullable(currentUser.getAdminId())
                //.map(Long::valueOf)
                .orElse(0L);
        operateUserDTO.setOperateUserId(userId);
        operateUserDTO.setOperateUserNo(Optional.ofNullable(currentUser.getAdminNo())
                .orElse("0"));
        operateUserDTO.setName(Optional.ofNullable(currentUser.getAdminName())
                .orElse(""));
        operateUserDTO.setOperateUserPhone(Optional.ofNullable(currentUser.getAdminPhone())
                .orElse(""));
        operateUserDTO.setUserTypeEnum(UserTypeEnum.valueOf(currentUser.getAdminType()));

        return operateUserDTO;
    }
}
