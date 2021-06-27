package com.example.demo.chapter3.pojo;

import com.example.demo.chapter3.pojo.definition.Animal;
import org.springframework.stereotype.Component;

/**
 * Created by zhangxiaoyun3 on 2018/10/25.
 */
@Component
public class Cat implements Animal {
    @Override
    public void use() {
        System.out.println("猫" + Cat.class.getSimpleName() + "是用来捉老鼠的");
    }
}
