package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Permission;

@Repository
public interface PermissionDao {
	
	public List<Permission> getAll();
	public Permission getById(Integer id);
	public void update(Permission permission);
	public void del(Integer id);
	public void add(Permission permission);

}
