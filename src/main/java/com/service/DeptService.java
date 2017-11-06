package com.service;

import java.util.List;

import com.entity.Dept;

public interface DeptService {
	
	public List<Dept> getAll();
	public Dept getById(Integer id);
	public void update(Dept dept);
	public void del(Integer id);
	public void add(Dept dept);

}
