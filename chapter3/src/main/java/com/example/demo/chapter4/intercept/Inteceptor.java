package com.example.demo.chapter4.intercept;

import com.example.demo.chapter4.invoke.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by zhangxiaoyun3 on 2018/10/26.
 */
public interface Inteceptor {
    /**
     * 事前方法
     * @return
     */
    public boolean before();

    /**
     * 是否方法
     */
    public void after();

    /**
     * 取代原有时间方法
     * @param invocation
     * @return
     */
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    //时间没有发生异常执行
    public void afterReturning();
    //当事件发生异常后执行
    public void afterThrowing();

    //是否是用arount替换原有方法
    boolean useAround();
}
