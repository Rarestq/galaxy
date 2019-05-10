package com.wuxiu.galaxy.api.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 费用类型枚举类
 *
 * @author: wuxiu
 * @date: 2019/5/9 22:49
 */
@Getter
@AllArgsConstructor
@ToString
public enum FeeTypeEnum {

    // 费用类型枚举 费用类型(0-寄存费用,1-逾期费用,2-赔偿费用)
    STORAGE_FEE(0, "寄存费用"),
    OVERDUE_FEE(1, "逾期费用"),
    COMPENSATE_FEE(2, "赔偿费用"),
    ;

    private Integer code;
    private String desc;

    /**
     * 将code包装成枚举类型
     *
     * @param code
     * @return
     */
    public static FeeTypeEnum valueOf(Integer code) {
        for (FeeTypeEnum feeTypeEnum : FeeTypeEnum
                .values()) {
            if (feeTypeEnum.getCode().equals(code)) {
                return feeTypeEnum;
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
        FeeTypeEnum[] arr = values();
        for (FeeTypeEnum feeTypeEnum : arr) {
            if (feeTypeEnum.getCode().equals(code)) {
                return feeTypeEnum.getDesc();
            }
        }
        return null;
    }
}
