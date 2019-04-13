package com.wuxiu.galaxy.common.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageRequestDTO implements Serializable {

    /**
     * 店铺名称查询
     */
    private String userName;
    /**
     * 店铺名称查询
     */
    private String productManufactureName;
    /**
     * 店铺id查询
     */
    private Long userId;
    /**
     * 商品名称查询
     */
    private String productName;
    /**
     * 是否上架，0-未上架，1-已上架，2-未处理(默认)
     */
    private Integer isShelf;
    /**
     * 是否通过注册审核，0-已通过，1-未通过，2-未处理(默认)
     */
    private Integer isApproval;
    /**
     * 是否通过钱包流水审核，0-已通过，1-未通过，2-未处理(默认)
     */
    private Integer walletOrderState;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     *当前页码
     */
    private Integer pageCurrent;
    /**
     *当前页上的记录数
     */
    private Integer pageSize;
    /**
     *总记录数
     */
    private Integer pageCount;

}
