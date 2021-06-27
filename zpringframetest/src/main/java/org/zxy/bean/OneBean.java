package org.zxy.bean;

import java.util.Properties;

public class OneBean {

    private String id;
    private Properties properties;
    public OneBean(){
        System.out.println("wwwwwwwwwww");

    }
    public static OneBean getInstance(){
        System.out.println("getInstance");
        OneBean oneBean = new OneBean();
        System.out.println(oneBean.getClass().getClassLoader());
        return oneBean;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void init(){

    }
    public void destory(){

    }

    @Override
    public String toString() {
        return "OneBean{" +
                "id='" + id + '\'' +
                ", properties=" + properties +
                '}';
    }
}
