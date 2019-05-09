package com.wuxiu.galaxy.dal.common.dto;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.dal.domain.LuggageLostCompensationRecord;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 行李遗失赔偿记录查询参数
 *
 * @author: wuxiu
 * @date: 2019/4/28 09:37
 */
@ApiModel("行李遗失赔偿记录查询参数")
@Data
public class LostCompensateRecordQueryDTO implements Serializable {

    private static final long serialVersionUID = -4338941191519603214L;

    /**
     * 分页条件
     */
    Page<LuggageLostCompensationRecord> page;
    /**
     * 赔偿记录主键id
     */
    private Long luggageLostCompensationRecordId;
    /**
     * 管理员姓名
     */
    private String adminName;
    /**
     * 赔偿对象姓名
     */
    private String depositorName;
    /**
     * 行李类型
     */
    private String luggageTypeDesc;
}
