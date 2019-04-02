package com.wuxiu.galaxy.common.enums;


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
