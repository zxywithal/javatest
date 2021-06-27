package com.company;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.StringJoiner;

public class CommonProxy implements InvocationHandler {
    private Common common;
    private String proxyName;

    public CommonProxy(Common common,String proxyName) {
        this.common = common;
        this.proxyName = proxyName;
    }


    public static Object wrap(Common common,String proxyName) {
        Class<?>[] interfaces = common.getClass().getInterfaces();
        return Proxy.newProxyInstance(common.getClass().getClassLoader(), interfaces, new CommonProxy(common,proxyName));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        StringJoiner sj = new StringJoiner(":");
        sj.add("proxy name " + proxyName);
        sj.add("method Name " + method.getName());
        sj.add("proxy info " + proxy.getClass().getName());
        sj.add("args "+args);
        System.out.println(sj.toString());
        return method.invoke(common, args);
    }
}
