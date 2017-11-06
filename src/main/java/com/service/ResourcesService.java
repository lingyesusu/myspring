package com.service;

import java.util.List;

import com.entity.Resources;

public interface ResourcesService {
	
	public List<Resources> getAll();
	public Resources getById(Integer id);
	public void update(Resources resources);
	public void del(Integer id);
	public void add(Resources resources);

}