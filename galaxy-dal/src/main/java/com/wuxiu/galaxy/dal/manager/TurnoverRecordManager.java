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
import com.wuxiu.galaxy.api.dto.PairDTO;
import com.wuxiu.galaxy.api.dto.TurnoverRecordDTO;
import com.wuxiu.galaxy.dal.common.dto.TurnoverRecordQueryDTO;
import com.wuxiu.galaxy.dal.domain.TurnoverRecord;
import com.wuxiu.galaxy.api.common.base.BaseManager;

import java.util.List;

/**  
 * TurnoverRecordManager
  * 营业额记录表
 * 
  * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-04-22
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
     * 按照管理员id对查询到的营业额进行分组
     *
     * @return
     */
    List<PairDTO<Long, String>> getTurnoverRecordPair();
}
