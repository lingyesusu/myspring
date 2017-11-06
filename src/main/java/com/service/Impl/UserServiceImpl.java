package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;
import com.service.UserService;

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
	public Object login(User user) {
		User getUser = userDao.login(user.getName(),user.getPassword());
		if(null!=getUser){
			return new CodeResult();
		}
		return null;
	}

}
