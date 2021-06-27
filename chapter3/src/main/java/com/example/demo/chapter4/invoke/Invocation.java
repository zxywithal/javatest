package com.example.demo.chapter4.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhangxiaoyun3 on 2018/10/26.
 */
public class Invocation {
    private Object target;
    private Method method;
    private Object[] params;

    public Invocation(Object target, Method method, Object[] params) {
        this.target = target;
        this.method = method;
        this.params = params;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, params);
    }
}
