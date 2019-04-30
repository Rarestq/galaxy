package com.wuxiu.galaxy.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * 行李计费基础参数对象
 *
 * @author: wuxiu
 * @date: 2019/4/21 21:14
 */
@Getter
@Setter
@ToString
public class LuggageFeeBaseCalculationParamDTO {

    /**
     * 计费单位ID
     */
    private Integer calculationUnitsId;

    /**
     * 每个计费单位的金额
     */
    private String feePerUnit;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LuggageFeeBaseCalculationParamDTO that = (LuggageFeeBaseCalculationParamDTO) o;

        return Objects.equals(getCalculationUnitsId(), that.getCalculationUnitsId()) &&
                Objects.equals(getFeePerUnit(), that.getFeePerUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCalculationUnitsId(), getFeePerUnit());
    }
}
