package com.wuxiu.galaxy.dal.manager;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.wuxiu.galaxy.api.common.enums.LuggageCabinetStatusEnum;
import com.wuxiu.galaxy.api.common.page.PageInfo;
import com.wuxiu.galaxy.api.dto.CabinetInfoDTO;
import com.wuxiu.galaxy.api.dto.CabinetQueryDTO;
import com.wuxiu.galaxy.dal.domain.LuggageCabinet;
import com.wuxiu.galaxy.service.core.biz.service.apiservice.CabinetService;
import com.wuxiu.galaxy.test.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 行李寄存柜测试类
 *
 * @author: wuxiu
 * @date: 2019/5/15 11:45
 */
@Slf4j
public class LuggageCabinetManagerTest extends BaseTest {

    @Autowired
    private CabinetService cabinetService;

    private Long cabinetId;

//    /**
//     * 初始化数据
//     */
//    @Before
//    public void setUp() {
//        this.cabinetId = addLuggageCabinet();
//    }
//
//    /**
//     * 删掉该测试类中每一个测试用例产生的数据
//     */
//    @After
//    public void cleanData() {
//        cabinetService.deleteById(cabinetId);
//    }

    /**
     * 新增行李寄存柜信息
     *
     * @return 管理员id
     */
    private Long addLuggageCabinet() {
        LuggageCabinet luggageCabinet = new LuggageCabinet();

        Long luggageCabinetId = cabinetService.addLuggageCabinet(luggageCabinet);

        this.cabinetId = luggageCabinetId;

        return luggageCabinetId;
    }

    @Test
    public void testAddLuggageCabinetInfo() {
        for (int i = 0; i < 50; i++) {
            Long luggageCabinetId = addLuggageCabinet();
            assertNotNull(luggageCabinetId);
        }
    }

    /**
     * 测试查询行李寄存柜信息
     */
    @Test
    public void testQueryCabinetInfoList() {

        CabinetQueryDTO queryDTO = new CabinetQueryDTO();
        queryDTO.setStatus(LuggageCabinetStatusEnum.FREE.getCode());


        PageInfo<CabinetInfoDTO> cabinetInfoDTOPageInfo =
                cabinetService.queryCabinetInfoList(queryDTO);
        List<CabinetInfoDTO> cabinetInfoDTOS = cabinetInfoDTOPageInfo.getRecords();

        assertTrue(CollectionUtils.isNotEmpty(cabinetInfoDTOS));
    }

}
