package com.example.pojo;

import java.io.Serializable;

/**
 * Created by zhangxiaoyun3 on 2019/2/22.
 */
public class UserPo implements Serializable {
    private int id;
    private String userName;
    private int level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
