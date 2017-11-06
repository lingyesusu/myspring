package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Role_permission;

@Repository
public interface Role_permissionDao {
	
	public List<Role_permission> getAll();
	public Role_permission getById(Integer id);
	public void updateById(Role_permission role_permission);
	public void delById(Integer id);
	public void add(Role_permission role_permission);

}
