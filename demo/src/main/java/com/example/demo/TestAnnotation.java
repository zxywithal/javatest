package com.example.demo;

import java.lang.annotation.*;

/**
 * Created by zhangxiaoyun3 on 2018/9/10.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface TestAnnotation {
    int id() default -1;
    String msg() default "hi";


}

