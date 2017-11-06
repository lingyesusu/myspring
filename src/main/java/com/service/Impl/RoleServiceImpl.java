package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.RoleDao;
import com.entity.Role;
import com.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleDao roleDao;
	
	public List<Role> getAll(){
		return roleDao.getAll();
		
	}

	@Override
	public Role getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Role role) {
		// TODO Auto-generated method stub
		
	}

}
