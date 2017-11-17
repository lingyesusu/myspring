package com.service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;
import com.shiro.UserRealm;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	
	public List<User> getAll(){
		return userDao.getAll();
		
	}

	@Override
	public User getById(Integer id) {
		User user = userDao.getById(id);
		return user;
	}

	@Override
	public void update(User user) {
		userDao.updateById(user);
		
	}

	@Override
	public void del(Integer id) {
		userDao.delById(id);
		
	}

	@Override
	public void add(User user) {
		userDao.add(user);
		
	}

	@Override
	public Object login(String username) {
		User user = null;
		if(username.equals("user")){
			user = new User();
			user.setUsername("user");
			user.setPassword("123456");
			user.setAge(1);
			user.setDept_id(1);
			user.setName("user");
			user.setStatus(1);
			user.setId(1);
		}else{
			user = new User();
			user.setUsername("admin");
			user.setPassword("123456");
			user.setAge(10);
			user.setDept_id(2);
			user.setName("admin");
			user.setStatus(1);
			user.setId(2);
		}
		return user;
	}

	@Override
	public Set<String> getRolesByName(String username) {
		Set<String> roles=new HashSet<String>();
		if(username.equals("user")){
			roles.add("user");
			roles.add("throw");
		}else{
			roles.add("admin");
			roles.add("throw");
		}
		return roles;
	}

	@Override
	public Set<String> getPermissionsByName(String username) {
		Set<String> permissions=new HashSet<String>();
		if(username.equals("user")){
			permissions.add("throwpermission");
			permissions.add("userpermission");
		}else{
			permissions.add("throwpermission");
			permissions.add("adminpermission");
		}
		return permissions;
	}

}
