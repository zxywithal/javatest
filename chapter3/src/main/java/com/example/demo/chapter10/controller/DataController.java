package com.example.demo.chapter10.controller;

import com.example.demo.chapter10.pojo.User;
import com.example.demo.chapter10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangxiaoyun3 on 2018/11/15.
 */
@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private UserService userService;

    /**
     * spring会把model的数据和试图data/user做绑定
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/model")
    public String useModel(Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "data/user";
    }

    @GetMapping("/modelMap")
    public ModelAndView userModelView(Long id, ModelMap modelMap) {
        User user = userService.getUser(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("data/user");
        modelMap.put("user", user);
        return modelAndView;
    }

    @GetMapping("/modelAndView")
    public ModelAndView usermodelAndView(Long id, ModelAndView modelAndView) {
        User user = userService.getUser(id);
        modelAndView.setViewName("data/user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
