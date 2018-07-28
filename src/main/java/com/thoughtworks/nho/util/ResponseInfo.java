package com.thoughtworks.nho.util;

public class ResponseInfo{
    private String code;
    private String message;
    private Object data;
    public ResponseInfo(String code,String message){
        this.code = code;
        this.message =message;
    }

    public ResponseInfo(String code,Object data){
        this.code =code;
        this.data =data;
    }



}
