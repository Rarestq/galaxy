package com.wuxiu.galaxy.dal.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理员数据转换对象
 *
 * @author: wuxiu
 * @date: 2019/4/15 15:07
 */
@Data
public class AdminDTO implements Serializable {

    private static final long serialVersionUID = 7053393664333430249L;

    /**
     * 管理员id(主键)
     */
    private Long adminId;
    /**
     * 管理员编号(随机生成)
     */
    private String adminNo;
    /**
     * 管理员姓名
     */
    private String adminName;
    /**
     * 管理员电话
     */
    private String adminPhone;
    /**
     * 管理员性别(0-男，1-女)
     */
    private Integer gender;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
}
