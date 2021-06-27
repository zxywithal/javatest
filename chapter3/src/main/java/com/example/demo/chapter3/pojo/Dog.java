package com.example.demo.chapter3.pojo;

import com.example.demo.chapter3.pojo.definition.Animal;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiaoyun3 on 2018/10/25.
 */
@Component
public class Dog implements Animal {
    @Override
    public void use() {
        System.out.println("狗" + Dog.class.getSimpleName() + "是用来看门的");
    }
}
