package com.controller;

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
	
	@RequestMapping("/login")
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	
	@RequestMapping("/inLogin")
	@ResponseBody
	public Object inLogin(User user){
		return userService.login(user);
	}
	
	@RequestMapping("/register")
	public ModelAndView register(){
		return new ModelAndView("register");
	}

}
