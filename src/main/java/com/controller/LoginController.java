package com.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(true);
        System.out.print("为验证登录用户而封装的Token：");
        System.out.println(ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        //在调用了login方法后，SecurityManager会收到AuthenticationToken，并将其发送给已配置的Realm执行必须的认证检查
        //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
        //所以这一步在调用login(token)方法时，它会走到MyRealm.doGetAuthenticationInfo()方法中，具体验证方式详见此方法
        currentUser.login(token);
		return null;
	}
	
	@RequestMapping("/register")
	public ModelAndView register(){
		return new ModelAndView("register");
	}

}
