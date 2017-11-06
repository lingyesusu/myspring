package com.service;

import java.util.List;

import com.entity.User;

public interface UserService {
	
	public List<User> getAll();
	public User getById(Integer id);
	public void update(User user);
	public void del(Integer id);
	public void add(User user);

}