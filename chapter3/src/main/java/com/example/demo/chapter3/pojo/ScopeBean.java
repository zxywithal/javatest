package com.example.demo.chapter3.pojo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by zhangxiaoyun3 on 2018/10/25.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ScopeBean {
}
