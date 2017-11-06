package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.Role_permissionDao;
import com.entity.Role_permission;
import com.service.Role_permissionService;

@Service
public class Role_permissionServiceImpl implements Role_permissionService {
	@Resource
	private Role_permissionDao role_permissionDao;

	@Override
	public List<Role_permission> getAll() {
		// TODO Auto-generated method stub
		return role_permissionDao.getAll();
	}

	@Override
	public Role_permission getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Role_permission role_permission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Role_permission role_permission) {
		// TODO Auto-generated method stub
		
	}

}
