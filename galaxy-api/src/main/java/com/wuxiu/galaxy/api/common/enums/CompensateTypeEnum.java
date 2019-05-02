package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 赔偿类型枚举
 *
 * @author: wuxiu
 * @date: 2019/5/2 09:10
 */
@Getter
@AllArgsConstructor
@ToString
public enum CompensateTypeEnum {

    // 赔偿方式及赔偿金额
    COMPENSATE_COMMON_LUGGAGE(1, "普通物件", "50元/件"),
    COMPENSATE_FRAGILE_LUGGAGE(2, "易碎物件", "100元/件"),
    COMPENSATE_VALUABLE_LUGGAGE(3, "贵重物件", "200元/件"),
    ;

    private Integer code;
    private String desc;
    private String feeValueDesc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static CompensateTypeEnum valueOf(Integer code) {
        for (CompensateTypeEnum compensateTypeEnum : CompensateTypeEnum.values()) {
            if (compensateTypeEnum.getCode().equals(code)) {
                return compensateTypeEnum;
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
        CompensateTypeEnum[] arr = values();
        for (CompensateTypeEnum compensateTypeEnum : arr) {
            if (compensateTypeEnum.getCode().equals(code)) {
                return compensateTypeEnum.getDesc();
            }
        }
        return null;
    }

    /**
     * 通过 code 得到赔偿金额
     *
     * @param code 枚举的 code
     * @return
     */
    public static String getFeeDescByCode(Integer code) {
        CompensateTypeEnum[] arr = values();
        for (CompensateTypeEnum compensateTypeEnum : arr) {
            if (compensateTypeEnum.getCode().equals(code)) {
                return compensateTypeEnum.getFeeValueDesc();
            }
        }
        return null;
    }
}
