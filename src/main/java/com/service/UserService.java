package com.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.entity.User;

public interface UserService {
	
	public List<User> getAll();
	public User getById(Integer id);
	public void update(User user);
	public void del(Integer id);
	public void add(User user);
	public Object login(String username);
	public Set<String> getRolesByName(String username);
	public Set<String> getPermissionsByName(String username);
	
	public Integer callProcedure(Map map);
	public List<User> callProcedureList(Map map);
	
	public Integer callFunction(Map map);

}