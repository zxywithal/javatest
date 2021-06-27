package com.example.demo.chapter4;

import com.example.demo.chapter4.intercept.Inteceptor;
import com.example.demo.chapter4.intercept.MyInterceptor;
import com.example.demo.chapter4.proxy.ProxyBean;
import com.example.demo.chapter4.service.HelloService;
import com.example.demo.chapter4.service.impl.SayHelloImpl;

/**
 * Created by zhangxiaoyun3 on 2018/10/26.
 */
public class AopTest {
    public static void main(String[] args) {
        HelloService helloService = new SayHelloImpl();
        Inteceptor inteceptor = new MyInterceptor();
        HelloService proxyBean = (HelloService)ProxyBean.getProxyBean(helloService, inteceptor);
        proxyBean.sayHello(null);
    }
}
