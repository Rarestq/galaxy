package com.wuxiu.galaxy.dal.common.dto;

import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuxiu.galaxy.api.common.constants.CommonConstant;
import com.wuxiu.galaxy.dal.domain.LuggageLostRegistrationRecord;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 行李遗失登记记录查询参数对象
 *
 * @author: wuxiu
 * @date: 2019/4/26 20:33
 */
@Data
public class LuggageLostRegisterRecordQueryDTO implements Serializable {

    private static final long serialVersionUID = 5697799673656389828L;

    Page<LuggageLostRegistrationRecord> page;

    /**
     * 行李所属者姓名
     */
    private String depositorName;

    /**
     * 遗失登记记录编号
     */
    private String lostRecordNo;

    /**
     * 遗失记录状态(0-遗失，1-已赔偿)
     */
    private Integer status;

    /**
     * 行李遗失登记时间
     */
    @JsonFormat(pattern = CommonConstant.TIME_PATTERN)
    private LocalDateTime lostTime;
}
