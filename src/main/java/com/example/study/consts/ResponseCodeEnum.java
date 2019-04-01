package com.example.study.consts;

public enum ResponseCodeEnum {

    SUCCESS(0, "success"),

    PARAM_NOT_NULL(1001, "参数不能为空");


    private int code;
    private String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
