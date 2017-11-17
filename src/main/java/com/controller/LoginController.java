package com.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.CodeResult;
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
	
	@RequestMapping(value="/inLogin" ,method=RequestMethod.POST)
	@ResponseBody
	public Object inLogin(User user){
		System.err.println();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(true);
        System.out.print("为验证登录用户而封装的Token：");
        System.out.println(ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
        	User userAfter= (User)currentUser.getPrincipal();
        	if(!userAfter.getUsername().equalsIgnoreCase(user.getUsername())){
        		currentUser.logout();
        	}
        }
        //在调用了login方法后，SecurityManager会收到AuthenticationToken，并将其发送给已配置的Realm执行必须的认证检查
        //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
        //所以这一步在调用login(token)方法时，它会走到MyRealm.doGetAuthenticationInfo()方法中，具体验证方式详见此方法
        String error="";
        try {  
            currentUser.login(token);  
        } catch (UnknownAccountException e) {  
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {  
            error = "用户名/密码错误";
        } catch (ExcessiveAttemptsException e) {  
            // TODO: handle exception  
            error = "登录失败多次，账户锁定10分钟";  
        } catch (AuthenticationException e) {  
            // 其他错误，比如锁定，如果想单独处理请单独catch处理  
            error = "其他错误：" + e.getMessage();  
        }
        System.out.println(error);
		return new CodeResult(error);
	}
	
	@RequestMapping("/register")
	public ModelAndView register(){
		return new ModelAndView("register");
	}
	
	@RequestMapping(value="/logout" ,method=RequestMethod.GET)
	@ResponseBody
	public Object logout(){
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
		return new CodeResult<>();
	}
	
	@RequestMapping(value="/login/unauthorized" ,method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView unauthorized(){
		return new ModelAndView("unauthorized");
	}

}
