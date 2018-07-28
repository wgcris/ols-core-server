package com.thoughtworks.nho.util;

public enum ResultCode {
    USER_NAME_ERROR("-1"),
    USER_PWD_ERROR("-1"),
    CUSTOMIZE_ERROR("-1"),
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
