package com.example.demo.chapter10.controller.advice.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by zhangxiaoyun3 on 2018/11/21.
 */
@Controller
@RequestMapping("/advice")
public class AdviceController {
    private static Log log = LogFactory.getLog(AdviceController.class);
    @GetMapping("/test")
    public String test(ModelMap model, Date date){
        log.info(model.get("project_name"));
//        log.info(DateUtils.format(date,DateUtils.ISO8601_DATE_PATTERN));
        throw new RuntimeException("异常了");
    }
}
