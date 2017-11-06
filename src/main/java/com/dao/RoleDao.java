package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Role;

@Repository
public interface RoleDao {
	
	public List<Role> getAll();
	public Role getById(Integer id);
	public void updateById(Role role);
	public void delById(Integer id);
	public void add(Role role);

}
