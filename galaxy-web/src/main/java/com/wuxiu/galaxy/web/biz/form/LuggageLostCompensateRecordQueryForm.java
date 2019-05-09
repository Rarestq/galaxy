package com.wuxiu.galaxy.web.biz.form;

import com.wuxiu.galaxy.api.common.page.PageInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 行李遗失赔偿记录参数查询表单
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:30
 */
@ApiModel("行李遗失赔偿记录参数查询表单")
@Data
public class LuggageLostCompensateRecordQueryForm extends PageInfo {

    private static final long serialVersionUID = -7194370096686614596L;

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
