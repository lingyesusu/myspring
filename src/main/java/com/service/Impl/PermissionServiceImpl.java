package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.PermissionDao;
import com.entity.Permission;
import com.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	@Resource
	private PermissionDao permissionDao;

	@Override
	public List<Permission> getAll() {
		return permissionDao.getAll();
	}

	@Override
	public Permission getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Permission permission) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Permission permission) {
		// TODO Auto-generated method stub
		
	}

}
