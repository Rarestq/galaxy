/** 
 * All rights Reserved, Designed By wuxiu
 *
 * @Package com.wuxiu.galaxy.dal.dao
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @date: 2018-04-16 20:35:12
 * @Copyright: 2019-2022 https://github.com/Rarestq Inc. All rights reserved.
 */
package com.wuxiu.galaxy.dal.manager.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.api.common.base.BaseManagerImpl;
import com.wuxiu.galaxy.api.common.enums.LuggageCabinetStatusEnum;
import com.wuxiu.galaxy.dal.common.dto.CabinetQueryDTO;
import com.wuxiu.galaxy.dal.dao.LuggageCabinetDao;
import com.wuxiu.galaxy.dal.domain.LuggageCabinet;
import com.wuxiu.galaxy.dal.manager.LuggageCabinetManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * <p>LuggageCabinetManager</p>
 * <p>
 * 行李寄存柜表
 * </p>
 * @author: Baomidou_Generater（rarestzhou@gmail.com）
 * @since 2019-05-15
 */
@Component
public class LuggageCabinetManagerImpl extends BaseManagerImpl<LuggageCabinetDao, LuggageCabinet> implements LuggageCabinetManager{

    /**
     * 添加行李寄存柜
     *
     * @param cabinet
     * @return
     */
    @Override
    public Long addLuggageCabinet(LuggageCabinet cabinet) {
        LuggageCabinet luggageCabinet = new LuggageCabinet();
        luggageCabinet.setLuggageCabinetNo(cabinet.getLuggageCabinetNo());
        luggageCabinet.setStatus(cabinet.getStatus());
        luggageCabinet.setGmtCreate(cabinet.getGmtCreate());
        luggageCabinet.setGmtModified(cabinet.getGmtModified());

        insert(luggageCabinet);

        return luggageCabinet.getLuggageCabinetId();
    }

    /**
     * 查询行李寄存柜信息
     *
     * @param queryDTO
     * @return
     */
    @Override
    public Page<LuggageCabinet> queryCabinetInfoList(
            CabinetQueryDTO queryDTO) {

        // 构造查询参数
        Wrapper<LuggageCabinet> wrapper = new EntityWrapper<>();
        if (Objects.nonNull(queryDTO.getLuggageCabinetId())) {
            wrapper.eq("luggage_cabinet_id", queryDTO.getLuggageCabinetId());
        }

        if (StringUtils.isNotBlank(queryDTO.getLuggageCabinetNo())) {
            wrapper.like("luggage_cabinet_no", queryDTO.getLuggageCabinetNo());
        }

        if (Objects.nonNull(queryDTO.getStatus())) {
            wrapper.eq("status", queryDTO.getStatus());
        }

        wrapper.orderBy("gmt_create", false)
                .orderBy("luggage_cabinet_id", false);

        return selectPage(queryDTO.getPage(), wrapper);
    }

    @Override
    public List<LuggageCabinet> getAllCabinets() {
        Wrapper<LuggageCabinet> wrapper = new EntityWrapper<LuggageCabinet>()
                .eq("status", LuggageCabinetStatusEnum.FREE.getCode());

        return selectList(wrapper);
    }

    /**
     * 维修寄存柜
     *
     * @param cabinetIds
     * @return
     */
    @Override
    public void repairCabinets(List<Long> cabinetIds) {

    }
}
