package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.DeptDao;
import com.entity.Dept;
import com.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {
	@Resource
	private DeptDao deptDao;
	
	public List<Dept> getAll(){
		return deptDao.getAll();
		
	}

	@Override
	public Dept getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Dept dept) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Dept dept) {
		// TODO Auto-generated method stub
		
	}

}
