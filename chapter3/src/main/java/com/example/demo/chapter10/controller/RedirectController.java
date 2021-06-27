package com.example.demo.chapter10.controller;

import com.example.demo.chapter10.enums.Sex;
import com.example.demo.chapter10.pojo.User;
import com.example.demo.chapter10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by zhangxiaoyun3 on 2018/11/16.
 */
@Controller
@RequestMapping("/redirect")
public class RedirectController {
    @Autowired
    private UserService userService;
    @GetMapping("/show")
    public String show(long id,Model view){
        User user = userService.getUser(id);
        view.addAttribute("user", user);
        return "/data/user";
    }
    @GetMapping("/redirect1")
    public String redirect1(String userName, String note, Sex sex){
        User user = new User();
        user.setSex(sex.toString());
        user.setNote(note);
        user.setUserName(userName);
        userService.insertUser(user);
        return "redirect:/redirect/show?id="+user.getId();
    }

    @GetMapping("/redirect2")
    public ModelAndView redirect2(String userName, String note, Sex sex,ModelAndView view){
        User user = new User();
        user.setSex(sex.toString());
        user.setNote(note);
        user.setUserName(userName);
        userService.insertUser(user);
        view.setViewName("redirect:/redirect/show?id="+user.getId());
        return view;
    }
    @GetMapping("/redirect3")
    public ModelAndView redirect3(String userName, String note, Sex sex, ModelAndView view, RedirectAttributes ra){
        User user = new User();
        user.setSex(sex.toString());
        user.setNote(note);
        user.setUserName(userName);
        userService.insertUser(user);
        ra.addFlashAttribute(user);
        view.setViewName("redirect:/redirect/showObj");
        return view;
    }

    @GetMapping("/showObj")
    public String showObj(User user,Model view){
        view.addAttribute("user", user);
        return "/data/user";
    }
}
