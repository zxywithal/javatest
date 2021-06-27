package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.chapter8.pojo.User;

/**
 * Created by zhangxiaoyun3 on 2019/2/13.
 */
public class Test {
    public static void main(String[] args) {
        String jsonStr = "{\"id\":6,\"userName\":\"user_name_6\",\"note\":\"note_6\"}";
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println(user);
    }
}
