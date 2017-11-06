package com.service;

import java.util.List;

import com.entity.Role_permission;

public interface Role_permissionService {
	
	public List<Role_permission> getAll();
	public Role_permission getById(Integer id);
	public void update(Role_permission role_permission);
	public void del(Integer id);
	public void add(Role_permission role_permission);

}