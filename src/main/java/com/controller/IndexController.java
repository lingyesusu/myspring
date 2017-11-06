package com.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.authority.AuthorityContant;
import com.authority.RequiresMenu;
import com.entity.Dept;
import com.entity.User;
import com.service.DeptService;
import com.service.PermissionService;
import com.service.ResourcesService;
import com.service.RoleService;
import com.service.Role_permissionService;
import com.service.UserService;
import com.service.User_roleService;

@Controller
public class IndexController {
	@Resource
	private UserService userService;
	@Resource
	private DeptService deptService;
	@Resource
	private PermissionService permissionService;
	@Resource
	private ResourcesService resourcesService;
	@Resource
	private Role_permissionService role_permissionService;
	@Resource
	private RoleService roleService;
	@Resource
	private User_roleService user_roleService;
	
	@RequiresMenu(value="/index", type=AuthorityContant.QUERY)
	@RequiresPermissions(value="index:query")
	@RequestMapping("/index")
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	
	@RequiresMenu(value="/user", type=AuthorityContant.QUERY)
	@RequiresPermissions(value="index:query")
	@ResponseBody
	@RequestMapping("/user")
	public Object user(){
		User user=new User();
		user.setAge(1);
		user.setDept_id(1);
		user.setName("1");
		user.setPassword("1");
		user.setRole_id(1);
		user.setStatus(1);
		user.setUsername("1");
		userService.add(user);
		User user2 = userService.getById(1);
		System.out.println(user2.toString());
		user2.setName("2");
		userService.update(user2);
		List<User> all = userService.getAll();
		for (User user1 : all) {
			System.out.println(user1);
		}
		return null;
	}
	
	@RequiresMenu(value="/dept", type=AuthorityContant.QUERY)
	@RequiresPermissions(value="index:query")
	@ResponseBody
	@RequestMapping("/dept")
	public Object dept(){
		Dept dept=new Dept();
		dept.setDepartment("1");
		dept.setId(1);
		dept.setParent_id(1);
		deptService.add(dept);
		Dept dept2 = deptService.getById(1);
		System.out.println(dept2.toString());
		dept2.setDepartment("2");
		deptService.update(dept2);
		List<Dept> all = deptService.getAll();
		for (Dept dept3 : all) {
			System.out.println(dept3);
		}
		return null;
	}

}
