package com.example.demo.chapter11.enums;

/**
 * Created by zhangxiaoyun3 on 2018/11/15.
 */
public enum Sex {
    MALE("1","MALE"),
    FEMALE("0","FEMALE"),
    ;

    private String key;
    private String value;

    Sex(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
