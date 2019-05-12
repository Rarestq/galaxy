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
import com.wuxiu.galaxy.api.dto.StatisticsResultDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordDTO;
import com.wuxiu.galaxy.dal.common.dto.TurnoverRecordQueryDTO;
import com.wuxiu.galaxy.dal.domain.TurnoverRecord;

import java.util.List;

/**
 *   
 * TurnoverRecordManager
 *  * 营业额记录表
 *  
 *  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 *
 * @since 2019-04-22
 *  
 */
public interface TurnoverRecordManager extends BaseManager<TurnoverRecord> {

    /**
     * 查询营业额记录列表信息
     *
     * @param recordQueryDTO
     * @return
     */
    Page<TurnoverRecordDTO> queryTurnoverRecordList(TurnoverRecordQueryDTO recordQueryDTO);

    /**
     * 根据行李寄存id查询对应的营业额记录
     *
     * @param luggageId
     * @return
     */
    TurnoverRecord getTurnoverRecordByLuggageId(Long luggageId);

    /**
     * 按管理员统计营业额
     *
     * @return
     */
    List<StatisticsResultDTO> statisticsTurnoverByAdmin();

    /**
     * 按费用类型统计营业额
     *
     * @return
     */
    List<StatisticsResultDTO> statisticsTurnoverByFeeType();
}
