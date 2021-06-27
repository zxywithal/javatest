package com.example.demo.chapter5.enumeration;

/**
 * Created by zhangxiaoyun3 on 2018/10/31.
 */
public enum SexEnum {
    MAIL(1, "男"),
    FEMAIL(2, "女"),
    ;
    private int code;
    private String desc;

    SexEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
