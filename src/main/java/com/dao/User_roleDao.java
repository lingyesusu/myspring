package com.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.entity.Role;
import com.entity.User_role;

@Repository
public interface User_roleDao {
	
	public List<User_role> getAll();
	public User_role getById(Integer id);
	public void updateById(User_role user_role);
	public void delById(Integer id);
	public void add(User_role user_role);
	public Set<Role> getByUserId(Integer id);

}
