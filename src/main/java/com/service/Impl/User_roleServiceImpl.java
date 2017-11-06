package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.User_roleDao;
import com.entity.User_role;
import com.service.User_roleService;

@Service
public class User_roleServiceImpl implements User_roleService {
	@Resource
	private User_roleDao user_roleDao;
	
	public List<User_role> getAll(){
		return user_roleDao.getAll();
		
	}

	@Override
	public User_role getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User_role user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(User_role user) {
		// TODO Auto-generated method stub
		
	}

}
