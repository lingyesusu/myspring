package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.User_role;

@Repository
public interface User_roleDao {
	
	public List<User_role> getAll();
	public User_role getById(Integer id);
	public void update(User_role user_role);
	public void del(Integer id);
	public void add(User_role user_role);

}