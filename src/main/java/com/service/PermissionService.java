package com.service;

import java.util.List;

import com.entity.Permission;

public interface PermissionService {
	
	public List<Permission> getAll();
	public Permission getById(Integer id);
	public void update(Permission permission);
	public void del(Integer id);
	public void add(Permission permission);

}