package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public interface UserDao {
	
	public List<User> getAll();
	public User getById(Integer id);
	public void updateById(User user);
	public void delById(Integer id);
	public void add(User user);
	public User login(String name);

}
