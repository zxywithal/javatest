package com.company;

public class Main {

    public static void main(String[] args) {
        /**
         * 对代理的理解
         * 1.同一个接口对象可以有多个代理对象比如：代理Common proxy1  然后用Proxy.newProxyInstance在代理一次proxy1生成proxy2
         * 2.在用proxy执行common接口方法的时候proxy1和proxy2的invoke方法会递归执行，但是代理的具体方法只会执行一次
         */
        CommonImpl impl = new CommonImpl();
        Common proxy_one = (Common) CommonProxy.wrap(impl, "proxy one");
        Common proxy_two = (Common) CommonProxy.wrap(proxy_one, "proxy two");

        proxy_two.method1();

    }
}
