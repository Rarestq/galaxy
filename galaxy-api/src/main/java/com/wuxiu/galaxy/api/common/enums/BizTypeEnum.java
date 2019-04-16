package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 业务类型枚举
 *
 * @author: wuxiu
 * @date: 2019/4/13 15:32
 */
@Getter
@AllArgsConstructor
@ToString
public enum BizTypeEnum {

    // todo:业务类型枚举值
    DEFAULT(1, "默认"),
    FINANCE(1, "金融"),
    LOGISTICS(2, "物流"),
    WMS_VIDEO(3, "仓储监控"),
    WMS(4, "仓储");

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static BizTypeEnum valueOf(Integer code) {
        for (BizTypeEnum bizTypeEnum : BizTypeEnum.values()) {
            if (bizTypeEnum.getCode().equals(code)) {
                return bizTypeEnum;
            }
        }
        return null;
    }

    /**
     * 通过 code 得到枚举描述
     *
     * @param code 枚举的 code
     * @return
     */
    public static String getDescByCode(Integer code) {
        BizTypeEnum[] arr = values();
        for (BizTypeEnum bizTypeEnum : arr) {
            if (bizTypeEnum.getCode().equals(code)) {
                return bizTypeEnum.getDesc();
            }
        }
        return null;
    }
}
