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

    // 默认大区目前采用和金融一致，预先占坑，防止以后会变
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
}
