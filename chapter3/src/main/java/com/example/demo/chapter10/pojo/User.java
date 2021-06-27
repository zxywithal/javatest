package com.example.demo.chapter10.pojo;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by zhangxiaoyun3 on 2018/10/31.
 */
@Alias("user")
public class User implements Serializable{
    private int id;
    private String userName;
    private String sex;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
