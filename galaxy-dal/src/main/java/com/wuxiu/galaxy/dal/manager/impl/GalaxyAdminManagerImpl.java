/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager.impl;

import com.wuxiu.galaxy.dal.domain.GalaxyAdmin;
import com.wuxiu.galaxy.dal.dao.GalaxyAdminDao;
import com.wuxiu.galaxy.dal.manager.GalaxyAdminManager;
import com.wuxiu.galaxy.common.base.BaseManagerImpl;
import org.springframework.stereotype.Component;

/**
 * <p>GalaxyAdminManager</p>
 * <p>
 * 管理员表 - 按照编号前缀的不同具有不同权限
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-14
 */
@Component
public class GalaxyAdminManagerImpl extends BaseManagerImpl<GalaxyAdminDao, GalaxyAdmin> implements GalaxyAdminManager{

}
