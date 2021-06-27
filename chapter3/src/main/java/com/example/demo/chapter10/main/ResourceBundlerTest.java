package com.example.demo.chapter10.main;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by zhangxiaoyun3 on 2018/11/16.
 * java原生版本得国际化 其实原生得也很简单
 */
public class ResourceBundlerTest {
    public static void main(String[] args) {
//        Locale[] availableLocales = Locale.getAvailableLocales();
//        for (Locale availableLocale : availableLocales) {
//            System.out.println(availableLocale.getDisplayLanguage()+":"+availableLocale.getDisplayName());
//        }
        Locale.setDefault(Locale.US);
        ResourceBundle bundle = ResourceBundle.getBundle("international", Locale.getDefault());
        System.out.println(bundle.getString("msg"));
    }
}
