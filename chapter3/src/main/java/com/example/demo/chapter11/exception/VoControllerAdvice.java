package com.example.demo.chapter11.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by zhangxiaoyun3 on 2018/11/26.
 */
@ControllerAdvice(basePackages = "com.example.demo.chapter11.*",
        annotations = {Controller.class, RestController.class})
public class VoControllerAdvice {
    @ResponseBody
    @ExceptionHandler(NotFundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HashMap<String, String> exception(HttpServletRequest request, NotFundException e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("code", e.getCode());
        map.put("msg", e.getMsg());
        return map;
    }
}
