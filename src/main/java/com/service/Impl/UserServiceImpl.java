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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		
	}

}
