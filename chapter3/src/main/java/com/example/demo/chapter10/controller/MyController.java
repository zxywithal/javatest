package com.example.demo.chapter10.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxiaoyun3 on 2018/11/14.
 */
@Controller
@RequestMapping("/my")
public class MyController {

    //最普通的同名映射
    @ResponseBody
    @RequestMapping("/no/antotation")
    public Map<String,Object> noAnotation(Long valLong,int valInt,String valStr){
        HashMap<String, Object> map = getStringObjectHashMap(valLong, valInt, valStr);
        return map;
    }
    //将前台参数和后台变量名做映射转化
    @ResponseBody
    @RequestMapping("/requestParam")
    public Map<String,Object> requestparam(@RequestParam("val_long") Long valLong, @RequestParam("val_int") int valInt, @RequestParam(value = "var_str",required=false) String valStr){
        HashMap<String, Object> map = getStringObjectHashMap(valLong, valInt, valStr);
        return map;
    }

    //传递数组
    @ResponseBody
    @RequestMapping("/requestArray")
    public Map<String,Object> requestArray(@RequestParam("val_long") Long[] longarr, @RequestParam("val_int") int[] intarr, @RequestParam(value = "var_str",required=false) String strarr){
        HashMap<String, Object> map = getStringObjectHashMap(longarr, intarr, strarr);
        return map;
    }

    @GetMapping("/format/form")
    public String showFormat(){
        return "/format/formatter";
    }
    @ResponseBody
    @RequestMapping("/format/commit")
    public Map<String,Object> format(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date,
                                     @NumberFormat(pattern = "#,###.##") Double number){
        HashMap<String, Object> map = new HashMap<>();
        map.put("date", date);
        map.put("number", number);
        return map;
    }





    private HashMap<String, Object> getStringObjectHashMap(@RequestParam("val_long") Object valLong, @RequestParam("val_int") Object valInt, @RequestParam("var_str") Object valStr) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("valLong",valLong);
        map.put("valInt",valInt);
        map.put("valStr",valStr);
        return map;
    }
}
