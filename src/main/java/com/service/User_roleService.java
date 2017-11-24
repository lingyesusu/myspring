package com.service;

import java.util.List;
import java.util.Set;

import com.entity.Role;
import com.entity.User_role;

public interface User_roleService {
	
	public List<User_role> getAll();
	public User_role getById(Integer id);
	public void update(User_role user_role);
	public void del(Integer id);
	public void add(User_role user_role);
	public Set<Role> getByUserId(Integer id);

}