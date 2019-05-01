package com.wuxiu.galaxy.service.core.biz.service.apiservice.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.api.common.enums.UserTypeEnum;
import com.wuxiu.galaxy.api.common.expection.ParamException;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.AdminDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.api.dto.AdminInfoQueryDTO;
import com.wuxiu.galaxy.dal.manager.AdminManager;
import com.wuxiu.galaxy.service.core.base.utils.PageInfoUtil;
import com.wuxiu.galaxy.service.core.base.utils.UUIDGenerateUtil;
import com.wuxiu.galaxy.service.core.base.utils.ValidatorUtil;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
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

        // 构造 AdminInfoDTO 对象
        AdminInfoDTO saveAdminInfoDTO = buildAdminDTO(adminInfoDTO);

        return adminManager.saveAdminInfo(saveAdminInfoDTO);
    }

    /**
     * 构造 AdminDTO 对象
     *
     * @param adminInfoDTO
     * @return
     */
    private AdminInfoDTO buildAdminDTO(AdminInfoDTO adminInfoDTO) {

        Integer adminType = adminInfoDTO.getAdminType();

        if (Objects.nonNull(adminInfoDTO.getAdminId())) {
            // 编辑管理员信息
            AdminInfoDTO editAdminInfoDTO = new AdminInfoDTO();
            editAdminInfoDTO.setAdminId(adminInfoDTO.getAdminId());
            editAdminInfoDTO.setAdminName(adminInfoDTO.getAdminName());
            editAdminInfoDTO.setAdminNo(adminInfoDTO.getAdminNo());
            editAdminInfoDTO.setAdminPhone(adminInfoDTO.getAdminPhone());
            editAdminInfoDTO.setAdminType(adminType);
            editAdminInfoDTO.setPassword(adminInfoDTO.getPassword());
            editAdminInfoDTO.setGmtModified(LocalDateTime.now());

            return editAdminInfoDTO;
        }

        // 新增管理员信息
        AdminInfoDTO newAdminInfoDTO = new AdminInfoDTO();
        newAdminInfoDTO.setAdminName(adminInfoDTO.getAdminName());

        if (Objects.equals(UserTypeEnum.valueOf(adminType), UserTypeEnum.ADMIN)) {
            newAdminInfoDTO.setAdminNo(UUIDGenerateUtil.generateUniqueNo(
                    CommonConstant.ADMIN_NO_PREFIX));
        } else if (Objects.equals(UserTypeEnum.valueOf(adminType),
                UserTypeEnum.SUPER_ADMIN)) {
            newAdminInfoDTO.setAdminNo(UUIDGenerateUtil.generateUniqueNo(
                    CommonConstant.SUPER_ADMIN_NO_PREFIX));
        }
        newAdminInfoDTO.setAdminPhone(adminInfoDTO.getAdminPhone());
        newAdminInfoDTO.setAdminType(adminType);
        newAdminInfoDTO.setPassword(Optional.ofNullable(adminInfoDTO.getPassword())
                .orElse("admin"));
        newAdminInfoDTO.setGmtCreate(LocalDateTime.now());
        newAdminInfoDTO.setGmtModified(LocalDateTime.now());

        return newAdminInfoDTO;
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

    /**
     * 删除管理员信息
     *
     * @param adminIds
     * @return
     */
    @Override
    public void deleteAdmin(List<Long> adminIds) {
        if (CollectionUtils.isEmpty(adminIds)) {
            log.info("删除管理员信息失败，adminId 不能为空");
            return;
        }
        adminManager.deleteBatchIds(adminIds);
    }
}
