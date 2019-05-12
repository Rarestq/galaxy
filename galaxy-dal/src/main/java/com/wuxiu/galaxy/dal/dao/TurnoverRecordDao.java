/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.dao;

import com.wuxiu.galaxy.api.common.base.BaseDao;
import com.wuxiu.galaxy.api.dto.StatisticsResultDTO;
import com.wuxiu.galaxy.dal.domain.TurnoverRecord;

import java.util.List;

/**  
 * <p>TurnoverRecordDao接口</p>
 * 营业额记录表
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-22
 */
public interface TurnoverRecordDao extends BaseDao<TurnoverRecord> {

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
