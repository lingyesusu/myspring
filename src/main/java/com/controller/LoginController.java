package com.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entity.User;
import com.service.UserService;

@Controller
public class LoginController {
	@Resource
	private UserService userService;
	
	@RequestMapping("index")
	public ModelAndView index(){
		List<User> all = userService.getAll();
		for (User user : all) {
			System.out.println(user);
		}
		return new ModelAndView("index");
		
	}
	
	@RequestMapping("login")
	public ModelAndView login(){
		List<User> all = userService.getAll();
		for (User user : all) {
			System.out.println(user);
		}
		return new ModelAndView("login");
		
	}
	
	@RequestMapping("inLogin")
	@ResponseBody
	public Object inLogin(User user){
		System.out.println(user.toString());
		return "";
	}
	
	@RequestMapping("register")
	public ModelAndView register(){
		return new ModelAndView("register");
		
	}

}
