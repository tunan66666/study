package com.example.study.vo;

import com.example.study.consts.ResponseCodeEnum;
import lombok.Data;

@Data
public class ResponseMessage<T> {

    private int code;
    private String message;
    private T data;

    public ResponseMessage() {

    }

    public ResponseMessage(int code) {
        this.code = code;
    }

    public ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseMessage(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseMessage(T data) {
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}
