package com.example.demo.chapter10.controller;

import com.example.demo.chapter10.enums.Sex;
import com.example.demo.chapter10.pojo.User;
import com.example.demo.chapter10.service.UserService;
import com.example.demo.chapter10.view.PdfView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;


/**
 * 测试返回pdf视图
 */
@Controller
@RequestMapping("/uerController")
public class UserController {

	@Autowired
	private PdfView pdfView;
	@Autowired
	private UserService userService = null;

	//通过url传递参数
	@GetMapping("/{xx}")
	@ResponseBody
	public User get(@PathVariable("xx") long id) {
		return userService.getUser(id);
	}

	// 展示用户详情
	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/add");
		return mv;
	}

	/**
	 * 从客户端过来为json，且json数据在http报文体
	 * json字段和对象属性名称保持一致
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public User insert(@RequestBody User user) {
		User user1 = userService.insertUser(user);
		return user1;
	}

	/**
	 * 访问路径 http://127.0.0.1:8090/uerController/convert?sex=FEMALE
	 *
	 * @param sex
	 * @return
	 */
	@RequestMapping("/convert")
	@ResponseBody
	public Sex getSex(Sex sex) {
		return sex;
	}

	@RequestMapping("/export/pdf")
	public ModelAndView getUserList(String userName,String note,ModelAndView modelAndView){
		List<User> users = userService.findUsers(userName, note);
		modelAndView.addObject("userList",users);
		modelAndView.setView(pdfView);
		return  modelAndView;
	}
}
