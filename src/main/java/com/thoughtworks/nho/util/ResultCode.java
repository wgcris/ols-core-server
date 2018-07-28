package com.thoughtworks.nho.util;

public enum ResultCode {
    USER_EXISTED("-1"),
    USER_NOT_EXISTED("-1"),
    INVALID_CREDENTIAL("-1"),
    SYSTEM_EXCEPTION("-1"),
    SUCCESS("0");

    private String code;
    ResultCode(String code){
        this.code =code;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

}
