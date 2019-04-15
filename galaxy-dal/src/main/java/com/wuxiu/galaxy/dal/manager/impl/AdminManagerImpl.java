/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager.impl;

import com.wuxiu.galaxy.dal.domain.Admin;
import com.wuxiu.galaxy.dal.dao.AdminDao;
import com.wuxiu.galaxy.dal.manager.AdminManager;
import com.wuxiu.galaxy.common.base.BaseManagerImpl;
import org.springframework.stereotype.Component;

/**
 * <p>AdminManager</p>
 * <p>
 * 管理员表 - 按照编号前缀的不同具有不同权限
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-15
 */
@Component
public class AdminManagerImpl extends BaseManagerImpl<AdminDao, Admin> implements AdminManager{

}