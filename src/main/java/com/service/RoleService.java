package com.service;

import java.util.List;

import com.entity.Role;

public interface RoleService {
	
	public List<Role> getAll();
	public Role getById(Integer id);
	public void update(Role role);
	public void del(Integer id);
	public void add(Role role);

}