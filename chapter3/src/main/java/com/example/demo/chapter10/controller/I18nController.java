package com.example.demo.chapter10.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/**
 * Created by zhangxiaoyun3 on 2018/11/16.
 */
@Controller
@RequestMapping("/i18n")
public class I18nController {
    private static Log log = LogFactory.getLog(I18nController.class);
    @Autowired
    private MessageSource messageSource;
    @GetMapping("/page")
    public String page(){
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage("msg", null, locale);
        log.info("msg "+msg);
        return "i18n/internationalization";
    }
}
