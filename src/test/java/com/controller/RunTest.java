package com.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entity.User;
import com.service.DeptService;
import com.service.PermissionService;
import com.service.ResourcesService;
import com.service.RoleService;
import com.service.Role_permissionService;
import com.service.UserService;
import com.service.User_roleService;
import com.shiro.cache.impl.CustomShiroCacheManager;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:applicationContext-*.xml"})  
public class RunTest {
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
	@Resource
	private CustomShiroCacheManager customShiroCacheManager;
	
	@Test
	public void index(){
//		userService.del(1);
//		User user=new User();
//		user.setAge(1);
//		user.setDept_id(1);
//		user.setName("1");
//		user.setPassword("1");
//		user.setStatus(1);
//		user.setUsername("1");
//		user.setId(1);
//		userService.add(user);
//		User user2 = userService.getById(1);
//		System.out.println(user2.toString());
//		user2.setName("2");
//		userService.update(user2);
//		List<User> all = userService.getAll();
//		for (User user1 : all) {
//			System.out.println(user1);
//		}
	}

}
