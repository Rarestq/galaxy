package com.wuxiu.galaxy.web.biz.service;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.web.biz.form.AdminInfoForm;
import com.wuxiu.galaxy.web.biz.form.AdminInfoQueryForm;
import com.wuxiu.galaxy.web.biz.vo.AdminInfoVO;

import java.util.List;

/**
 * 管理员服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 11:59
 */
public interface GwAdminService {

    /**
     * 新增/编辑管理员信息
     *
     * @param form
     * @return
     */
    APIResult<AdminInfoVO> saveAdminInfo(AdminInfoForm form);

    /**
     * 查询管理员信息
     *
     * @param form
     * @return
     */
    APIResult<PageInfo<AdminInfoVO>> queryAdminInfoList(AdminInfoQueryForm form);

    /**
     * 删除管理员信息
     *
     * @param adminIds
     * @return
     */
    APIResult<Void> deleteAdmin(List<Long> adminIds);
}
