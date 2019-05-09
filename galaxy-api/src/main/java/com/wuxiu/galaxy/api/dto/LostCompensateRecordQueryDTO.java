package com.wuxiu.galaxy.api.dto;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 行李遗失赔偿记录查询参数
 *
 * @author: wuxiu
 * @date: 2019/4/28 09:37
 */
@ApiModel("行李遗失赔偿记录查询参数")
@Data
public class LostCompensateRecordQueryDTO extends PageInfo {

    private static final long serialVersionUID = -4338941191519603214L;

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
