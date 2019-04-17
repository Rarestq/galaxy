package com.wuxiu.galaxy.web.biz.service.impl;

import com.wuxiu.galaxy.api.common.entity.APIResult;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoQueryDTO;
import com.wuxiu.galaxy.integration.AdminClient;
import com.wuxiu.galaxy.service.core.base.utils.BeanCopierUtil;
import com.wuxiu.galaxy.service.core.base.utils.CommonUtil;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.StreamUtil;
import com.wuxiu.galaxy.web.biz.form.AdminInfoForm;
import com.wuxiu.galaxy.web.biz.form.AdminInfoQueryForm;
import com.wuxiu.galaxy.web.biz.service.GwAdminService;
import com.wuxiu.galaxy.web.biz.vo.AdminInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 管理员服务
 *
 * @author: wuxiu
 * @date: 2019/4/15 12:00
 */
@Slf4j
@Service
public class GwAdminServiceImpl implements GwAdminService {

    @Autowired
    private AdminClient adminClient;

    /**
     * 新增/编辑管理员信息
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<AdminInfoVO> saveAdminInfo(AdminInfoForm form) {
        AdminInfoDTO adminInfoDTO = BeanCopierUtil.convert(form, AdminInfoDTO.class);
        APIResult<Long> result = adminClient.saveAdminInfo(adminInfoDTO);
        if (!result.isSuccess()) {
            log.warn("新增/编辑管理员信息失败，result:{}, form:{}", result, form);
            return CommonUtil.errorAPIResult(result);
        }

        Long adminId = result.getData();
        if (Objects.isNull(adminId)) {
            return APIResult.ok(null);
        }

        // 通过新增/编辑的管理员的 adminId 再去查询这个管理员信息
        AdminInfoQueryDTO adminInfoQueryDTO = new AdminInfoQueryDTO();
        adminInfoQueryDTO.setAdminId(adminId);

        APIResult<PageInfo<AdminDTO>> infoAPIResult =
                adminClient.queryAdminInfoList(adminInfoQueryDTO);
        if (!infoAPIResult.isSuccess()) {
            log.warn("查询管理员信息失败，result:{}", infoAPIResult);
            return CommonUtil.errorAPIResult(infoAPIResult);
        }

        PageInfo<AdminDTO> adminDTOPageInfo = infoAPIResult.getData();
        AdminDTO adminDTO = adminDTOPageInfo.getRecords().get(0);
        AdminInfoVO adminInfoVO = BeanCopierUtil.convert(adminDTO, AdminInfoVO.class);

        return APIResult.ok(adminInfoVO);
    }

    /**
     * 查询管理员信息
     *
     * @param form
     * @return
     */
    @Override
    public APIResult<PageInfo<AdminInfoVO>> queryAdminInfoList(AdminInfoQueryForm form) {
        AdminInfoQueryDTO queryDTO = BeanCopierUtil.convert(form, AdminInfoQueryDTO.class);
        // 拷贝分页参数
        PageInfoUtil.copy(form, queryDTO);

        // 获取管理员信息列表
        APIResult<PageInfo<AdminDTO>> adminInfoListAPIResult =
                adminClient.queryAdminInfoList(queryDTO);
        if (!adminInfoListAPIResult.isSuccess()) {
            log.warn("查询管理员信息列表失败，result:{}, form:{}", adminInfoListAPIResult, form);
            return CommonUtil.errorAPIResult(adminInfoListAPIResult);
        }

        PageInfo<AdminDTO> adminDTOPageInfo = adminInfoListAPIResult.getData();
        List<AdminDTO> adminDTOS = adminDTOPageInfo.getRecords();

        // 封装成 AdminInfoVO 对象返回
        List<AdminInfoVO> adminInfoVOS =
                StreamUtil.convertBeanCopy(adminDTOS, AdminInfoVO.class);

        PageInfo<AdminInfoVO> pageInfo = new PageInfo<>(form.getCurrent(), form.getSize());
        pageInfo.setRecords(adminInfoVOS);
        pageInfo.setTotal(adminDTOPageInfo.getTotal());
        pageInfo.setPages(adminDTOPageInfo.getPages());

        return APIResult.ok(pageInfo);
    }
}
