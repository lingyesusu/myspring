package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.ResourcesDao;
import com.entity.Resources;
import com.service.ResourcesService;

@Service
public class ResourcesServiceImpl implements ResourcesService {
	@Resource
	private ResourcesDao resourcesDao;
	
	public List<Resources> getAll(){
		return resourcesDao.getAll();
		
	}

	@Override
	public Resources getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Resources resources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Resources resources) {
		// TODO Auto-generated method stub
		
	}

}
