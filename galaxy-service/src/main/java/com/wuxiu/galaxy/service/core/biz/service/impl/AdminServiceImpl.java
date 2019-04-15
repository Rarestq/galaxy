package com.wuxiu.galaxy.service.core.biz.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoQueryDTO;
import com.wuxiu.galaxy.dal.manager.AdminManager;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.UUIDGenerator;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 *
 *
 * @author: wuxiu
 * @date: 2019/4/15 19:39
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminManager adminManager;

    /**
     * 新增/编辑管理员信息
     *
     * @param adminInfoDTO
     * @return
     */
    @Override
    public Long saveAdminInfo(AdminInfoDTO adminInfoDTO) {
        log.info("新增/编辑管理员信息，adminInfoDTO:{}", adminInfoDTO);

        // 参数校验
        String adminInfoCheck = ValidatorUtil.returnAnyMessageIfError(adminInfoDTO);
        if (StringUtils.isNotEmpty(adminInfoCheck)) {
            log.info("新增/编辑管理员信息，参数错误：{}", adminInfoCheck);
            throw new ParamException(adminInfoCheck);
        }

        // 构造 AdminDTO 对象
        AdminDTO adminDTO = buildAdminDTO(adminInfoDTO);

        return adminDTO.getAdminId();
    }

    /**
     * 构造 AdminDTO 对象
     *
     * @param adminInfoDTO
     * @return
     */
    private AdminDTO buildAdminDTO(AdminInfoDTO adminInfoDTO) {

        if (Objects.nonNull(adminInfoDTO.getAdminId())) {
            // 编辑管理员信息
            AdminDTO editAdminDTO = new AdminDTO();
            editAdminDTO.setAdminId(adminInfoDTO.getAdminId());
            editAdminDTO.setAdminName(adminInfoDTO.getAdminName());
            editAdminDTO.setAdminNo(adminInfoDTO.getAdminNo());
            editAdminDTO.setAdminPhone(adminInfoDTO.getAdminPhone());
            editAdminDTO.setGender(adminInfoDTO.getGender());
            editAdminDTO.setGmtModified(LocalDateTime.now());

            return editAdminDTO;
        }

        // 新增管理员信息
        AdminDTO newAdminDTO = new AdminDTO();
        newAdminDTO.setAdminName(adminInfoDTO.getAdminName());
        // TODO：随机生成 adminNo
        newAdminDTO.setAdminNo(UUIDGenerator.getUUID());
        newAdminDTO.setAdminPhone(adminInfoDTO.getAdminPhone());
        newAdminDTO.setGender(adminInfoDTO.getGender());
        newAdminDTO.setGmtCreate(LocalDateTime.now());
        newAdminDTO.setGmtModified(LocalDateTime.now());

        return newAdminDTO;
    }

    /**
     * 查询管理员信息
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageInfo<AdminDTO> queryAdminInfoList(AdminInfoQueryDTO queryDTO) {
        log.info("查询管理员信息列表，queryDTO:{}", queryDTO);
        // 参数校验
        String adminDTOCheck = ValidatorUtil.returnAnyMessageIfError(queryDTO);
        if (StringUtils.isNotEmpty(adminDTOCheck)) {
            log.info("查询管理员信息列表，参数错误：{}", adminDTOCheck);
            throw new ParamException(adminDTOCheck);
        }

        com.wuxiu.galaxy.dal.common.dto.AdminInfoQueryDTO adminInfoQueryDTO =
                new com.wuxiu.galaxy.dal.common.dto.AdminInfoQueryDTO();
        adminInfoQueryDTO.setPage(PageInfoUtil.convert(queryDTO));
        adminInfoQueryDTO.setAdminId(queryDTO.getAdminId());

        // 查询管理员信息
        Page<AdminDTO> adminDTOPage = adminManager.queryAdminInfoList(adminInfoQueryDTO);
        if (PageInfoUtil.isEmpty(adminDTOPage)) {
            return PageInfoUtil.ofEmptyPage(queryDTO);
        }

        // 获取管理员信息列表
        List<AdminDTO> adminDTOS = adminDTOPage.getRecords();

        return PageInfoUtil.of(adminDTOPage, adminDTOS);
    }
}
