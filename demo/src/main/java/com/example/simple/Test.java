package com.example.simple;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by zhangxiaoyun3 on 2019/1/29.
 */
public class Test {
    public static float threehalfs = 1.5F;
    public static void main(String[] args) {
        String order = "1111";
        Test test = new Test();
        try {
            test.tt(order);
        } catch (Exception e) {
            System.out.println(order);
            e.printStackTrace();
        }
    }

    public void tt(String order) throws Exception {
        try{
            throw new Exception("www");
        }catch (Exception e){
            order = "2222";
            throw e;
        }
    }
}
