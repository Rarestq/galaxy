package com.wuxiu.galaxy.dal.common.dto;

import com.baomidou.mybatisplus.plugins.Page;
import com.wuxiu.galaxy.dal.domain.LuggageCabinet;
import lombok.Data;

import java.io.Serializable;

/**
 * 行李寄存柜查询参数
 *
 * @author: wuxiu
 * @date: 2019/5/15 13:45
 */
@Data
public class CabinetQueryDTO implements Serializable {

    private static final long serialVersionUID = -1130592876609114868L;

    /**
     * 分页条件
     */
    Page<LuggageCabinet> page;
    /**
     * 行李柜主键id
     */
    private Long luggageCabinetId;
    /**
     * 行李柜编号
     */
    private String luggageCabinetNo;
    /**
     * 行李柜状态(0-空闲、1-已占用、2-逾期占用、3-维修中)
     */
    private Integer status;
}
