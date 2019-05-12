package com.wuxiu.galaxy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统计结果
 *
 * @author: wuxiu
 * @date: 2019/5/12 19:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsBaseDTO implements Serializable {

    private static final long serialVersionUID = 7417733103270658677L;

    /**
     * 按Key统计，key的code码
     */
    private String keyCode;

    /**
     * 统计结果
     */
    private String feeValue;
}
