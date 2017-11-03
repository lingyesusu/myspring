package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public interface UserDao {
	
	public List<User> getAll();

}
