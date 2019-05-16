/**
 *  
 *  * All rights Reserved, Designed By wuxiu
 * <p>
 *  * @Package com.wuxiu.galaxy.dal.dao
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *  * @date: 2018-04-16 20:35:12
 *  * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 *  
 */
package com.wuxiu.galaxy.dal.manager;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.base.BaseManager;
import com.wuxiu.galaxy.dal.common.dto.CabinetQueryDTO;
import com.wuxiu.galaxy.dal.domain.LuggageCabinet;

import java.util.List;

/**
 *   
 * LuggageCabinetManager
 *  * 行李寄存柜表
 *  
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *
 * @since 2019-05-15
 *  
 */
public interface LuggageCabinetManager extends BaseManager<LuggageCabinet> {

    /**
     * 添加行李寄存柜
     *
     * @param cabinet
     * @return
     */
    Long addLuggageCabinet(LuggageCabinet cabinet);

    /**
     * 查询行李寄存柜信息
     *
     * @param cabinetQueryDTO
     * @return
     */
    Page<LuggageCabinet> queryCabinetInfoList(CabinetQueryDTO cabinetQueryDTO);

    /**
     * 获取所有「空闲」状态的寄存柜信息
     *
     * @return
     */
    List<LuggageCabinet> getAllCabinets();

    /**
     * 维修寄存柜
     *
     * @param cabinetIds
     * @return
     */
    void repairCabinets(List<Long> cabinetIds);
}
