package org.mybatis.example.enums;

/**
 * Created by zhangxiaoyun3 on 2018/12/17.
 */
public enum Sex {
    FEMAIL("FEMAIL", "女"),
    MAIL("MAIL", "男"),
    ;
    private Sex(String desc,String code) {
        this.desc = desc;
        this.code = code;
    }

    public String code;
    public String desc;
}
