package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Dept;

@Repository
public interface DeptDao {
	
	public List<Dept> getAll();
	public Dept getById(Integer id);
	public void update(Dept dept);
	public void delById(Integer id);
	public void add(Dept dept);

}
