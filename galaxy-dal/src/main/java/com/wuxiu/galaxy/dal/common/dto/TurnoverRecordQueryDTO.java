package com.wuxiu.galaxy.dal.common.dto;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.dal.domain.TurnoverRecord;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 营业额记录查询对象
 *
 * @author: wuxiu
 * @date: 2019/4/23 10:14
 */
@Data
public class TurnoverRecordQueryDTO implements Serializable {

    private static final long serialVersionUID = 6066006292822146828L;

    /**
     * 分页参数
     */
    Page<TurnoverRecord> page;

    /**
     * 行李类型id(下拉框)
     */
    private Long luggageTypeId;
    /**
     * 管理员姓名(模糊搜索)
     */
    private String adminName;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

}
