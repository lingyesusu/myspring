package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Resources;

@Repository
public interface ResourcesDao {
	
	public List<Resources> getAll();
	public Resources getById(Integer id);
	public void update(Resources resources);
	public void del(Integer id);
	public void add(Resources resources);

}
