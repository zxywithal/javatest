package com.example.demo.chapter11.exception;

/**
 * Created by zhangxiaoyun3 on 2018/11/26.
 */
public class NotFundException extends RuntimeException {
    private String code;
    private String msg;

    public NotFundException() {
    }

    public NotFundException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
