package com.company;

public class CommonImpl implements Common{
    private int count = 0;
    @Override
    public void method1() {
        count+=1;
        System.out.println("CommonImpl.method1 count ["+count+']');
    }

    @Override
    public void method2() {
        System.out.println("CommonImpl.method2");
    }
}
