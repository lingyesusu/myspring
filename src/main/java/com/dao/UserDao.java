package com.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public interface UserDao {
	
	public List<User> getAll();
	public User getById(Integer id);
	public void updateById(User user);
	public void delById(Integer id);
	public void add(User user);
	public User login(String name);
	
	//查询存储过程的方法
    public void callProcedure(Map map);
    public List<User> callProcedureList(Map map);
    //查询函数的方法
    public void callFunction(Map map);

}
