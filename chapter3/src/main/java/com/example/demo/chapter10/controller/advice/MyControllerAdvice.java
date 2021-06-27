package com.example.demo.chapter10.controller.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangxiaoyun3 on 2018/11/21.
 */
@ControllerAdvice(basePackages = "com.example.demo.chapter10.*",annotations = Controller.class)
public class MyControllerAdvice {
    //日期绑定之前
    @InitBinder
    public void initDataBinder(WebDataBinder binder) {
        CustomDateEditor customDateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false);
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    //在执行控制器之前初始化数据模型
    @ModelAttribute
    public void projectModel(Model model) {
        model.addAttribute("project_name", "chapter10");
    }

    //被拦截得控制器发生异常时，用相同得视图响应
    @ExceptionHandler(value = Exception.class)
    public String exception(Model model,Exception e){
        model.addAttribute("exception_message", e.getMessage());
        return "exception";
    }

}
