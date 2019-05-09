package com.wuxiu.galaxy.service.core.biz.service.apiservice;

import com.wuxiu.galaxy.api.common.enums.UserTypeEnum;
import com.wuxiu.galaxy.api.dto.NewLuggageStorageRecordDTO;
import com.wuxiu.galaxy.api.dto.OperateUserDTO;
import com.wuxiu.galaxy.test.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;

/**
 * 行李寄存接口测试
 *
 * @author: wuxiu
 * @date: 2019/5/7 09:46
 */
@Slf4j
public class LuggageStorageServiceTest extends BaseTest {

    @Autowired
    private LuggageStorageRecordService storageRecordService;

    private Long luggageRecordId;

    /**
     * 新增行李寄存信息
     *
     * @return 管理员id
     */
    private Long addLuggageStorageRecordInfo() {
        NewLuggageStorageRecordDTO recordDTO = new NewLuggageStorageRecordDTO();

        recordDTO.setDepositorName("hkw");
        recordDTO.setDepositorPhone("15180354187");
        recordDTO.setCalculateRuleId(5L);
        recordDTO.setLuggageTypeId(3L);
        recordDTO.setRemark("贵重行李");
        LocalDateTime time = LocalDateTime.of(2019, 5, 7, 16, 36);
        recordDTO.setStorageEndTime(time.toString());

        OperateUserDTO operateUserDTO = new OperateUserDTO();
        operateUserDTO.setOperateUserId(69L);
        operateUserDTO.setOperateUserNo("ADMIN201905070843234591544");
        operateUserDTO.setName("小明");
        operateUserDTO.setOperateUserPhone("15180354187");
        operateUserDTO.setUserTypeEnum(UserTypeEnum.valueOf(1));

        Long luggageId = storageRecordService.insertLuggageStorageRecord(recordDTO,
                operateUserDTO);
        this.luggageRecordId = luggageId;

        return luggageId;
    }

    @Test
    public void testAddLuggageStorageRecordInfo() {
        for (int i = 0; i < 5; i++) {
            Long luggageId = addLuggageStorageRecordInfo();
            assertNotNull(luggageId);
        }
    }

}
