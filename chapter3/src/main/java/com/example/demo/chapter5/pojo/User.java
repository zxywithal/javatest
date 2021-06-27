package com.example.demo.chapter5.pojo;

import com.example.demo.chapter5.enumeration.SexEnum;
import com.example.demo.chapter5.typehandler.SexTypeHandler;
import org.apache.ibatis.type.Alias;

import javax.servlet.annotation.HandlesTypes;
import java.io.Serializable;

/**
 * Created by zhangxiaoyun3 on 2018/10/31.
 */
@Alias("user")
public class User implements Serializable {
    private int id;
    private String userName;
    private SexEnum sexEnum;
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

    public SexEnum getSexEnum() {
        return sexEnum;
    }

    public void setSexEnum(SexEnum sexEnum) {
        this.sexEnum = sexEnum;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
