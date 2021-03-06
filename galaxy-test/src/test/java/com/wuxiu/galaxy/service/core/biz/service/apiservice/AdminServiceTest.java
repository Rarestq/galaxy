package com.wuxiu.galaxy.service.core.biz.service.apiservice;

import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.api.common.enums.RecordStatusEnum;
import com.wuxiu.galaxy.api.common.enums.UserTypeEnum;
import com.wuxiu.galaxy.api.dto.AdminInfoDTO;
import com.wuxiu.galaxy.dal.domain.Admin;
import com.wuxiu.galaxy.dal.manager.AdminManager;
import com.wuxiu.galaxy.service.core.base.utils.UUIDGenerateUtil;
import com.wuxiu.galaxy.test.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 管理员核心服务测试类
 *
 * @author: wuxiu
 * @date: 2019/5/1 16:30
 */
@Slf4j
public class AdminServiceTest extends BaseTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminManager adminManager;

    private Long adminId;

    private AdminInfoDTO adminInfoDTO;

    private Admin admin;

    /**
     * 初始化数据
     */
    @Before
    public void setUp() {
        this.adminId = saveAdminInfo();
        this.admin = adminManager.selectById(adminId);
    }

    /**
     * 删掉该测试类中每一个测试用例产生的数据
     */
    @After
    public void cleanData() {
        adminManager.deleteById(adminId);
    }

    /**
     * 新增管理员信息
     *
     * @return 管理员id
     */
    private Long saveAdminInfo() {
        AdminInfoDTO adminInfoDTO = new AdminInfoDTO();

        adminInfoDTO.setAdminNo(UUIDGenerateUtil.generateUniqueNo(
                CommonConstant.ADMIN_NO_PREFIX));
        adminInfoDTO.setAdminName("admin");
        adminInfoDTO.setAdminPhone("-");
        adminInfoDTO.setAdminType(UserTypeEnum.ADMIN.getCode());
        adminInfoDTO.setPassword("123456");
        adminInfoDTO.setDeleted(RecordStatusEnum.NORMAL.getCode());
        adminInfoDTO.setGmtCreate(LocalDateTime.now());
        adminInfoDTO.setGmtModified(LocalDateTime.now());

        this.adminInfoDTO = adminInfoDTO;
        Long adminInfoId = adminService.saveAdminInfo(adminInfoDTO);
        this.adminId = adminInfoId;

        return adminInfoId;
    }

    /**
     * 编辑管理员信息
     */
    private void editAdminInfo() {
        AdminInfoDTO adminInfoDTO = new AdminInfoDTO();

        adminInfoDTO.setAdminId(adminId);
        adminInfoDTO.setAdminNo(UUIDGenerateUtil.generateUniqueNo(
                CommonConstant.ADMIN_NO_PREFIX));
        adminInfoDTO.setAdminName("无朽");
        adminInfoDTO.setAdminPhone("15180354187");
        adminInfoDTO.setAdminType(UserTypeEnum.ADMIN.getCode());
        adminInfoDTO.setPassword("admin");
        adminInfoDTO.setDeleted(RecordStatusEnum.NORMAL.getCode());
        adminInfoDTO.setGmtCreate(LocalDateTime.now());
        adminInfoDTO.setGmtModified(LocalDateTime.now());

        this.adminInfoDTO = adminInfoDTO;

        adminService.saveAdminInfo(adminInfoDTO);
    }

    /**
     * 测试新增管理员
     */
    @Test
    public void testInsertAdminInfo() {
        for (int i = 0; i < 20; i++) {
            Long adminId = saveAdminInfo();
            assertNotNull(adminId);
        }
    }

    /**
     * 测试编辑管理员信息
     */
    @Test
    public void testEditAdminInfo() {
        editAdminInfo();

        String password = admin.getPassword();
        String adminInfoDTOPassword = adminInfoDTO.getPassword();
        assertNotEquals(password, adminInfoDTOPassword);
    }

}
