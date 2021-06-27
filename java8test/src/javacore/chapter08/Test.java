package javacore.chapter08;


import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        List<String> list = new ArrayList<>();
        System.out.println(list.getClass().getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
}