package com.example.demo.chapter4.proxy;

import com.example.demo.chapter4.intercept.Inteceptor;
import com.example.demo.chapter4.invoke.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.springframework.cglib.proxy.Proxy.*;

/**
 * Created by zhangxiaoyun3 on 2018/10/26.
 */
public class ProxyBean implements InvocationHandler {

    private Object target;
    private Inteceptor inteceptor;
    private Invocation invocation;

    public static Object getProxyBean(Object target,Inteceptor inteceptor){
        ProxyBean handler = new ProxyBean();
        handler.target = target;
        handler.inteceptor = inteceptor;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),handler);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        boolean isException = false;
        try{
            inteceptor.before();

            if (inteceptor.useAround()) {
                invocation = new Invocation(target, method, args);
                obj = inteceptor.around(invocation);
            }else{
                obj = method.invoke(target, args);
            }
        }catch (Exception e){
            isException = true;
        }
        inteceptor.after();
        if (isException) {
            inteceptor.afterThrowing();
        } else {
            inteceptor.afterReturning();
            return obj;
        }
        return obj;
    }
}
