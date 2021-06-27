package com.example.demo.chapter3.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiaoyun3 on 2018/9/12.
 */
//@Component()
public class User {
    @Value("22")
    private long id;
    @Value("user_name_1")
    private String userNmae;
    @Value("note_1")
    private String note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserNmae() {
        return userNmae;
    }

    public void setUserNmae(String userNmae) {
        this.userNmae = userNmae;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
