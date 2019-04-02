package com.shi.lissandra.common.enums;

/**
 * All rights Reserved, Designed By www.maihaoche.com
 *
 * @Package com.shi.lissandra.common.enums
 * @Author: Wuer（wuer@maihaoche.com）
 * @Date: 2019/4/1 16:41
 * @Copyright: 2017-2020 www.maihaoche.com Inc. All rights reserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目
 * @Description:
 */

public enum WalletOrderStateEnum {

    APPROVAL(0),
    NOT_APPROVAL(1),
    WAIT_APPROVAL(2);

    private Integer code;

    private WalletOrderStateEnum(Integer code){
        this.code = code;
    }

    public Integer getCode(){
        return this.code;
    }

}
