package com.entity;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable{
	
	private String username;
	private String password;
	private Integer dept_id;
	private Integer age;
	private Integer id;
	private String name;
	private Integer status;
	private Set<Role> roles;
	
	public User(User user) {
		this.username = user.username;
		this.password = user.password;
		this.dept_id = user.dept_id;
		this.age = user.age;
		this.id = user.id;
		this.name = user.name;
		this.status = user.status;
		this.roles = user.roles;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", dept_id=" + dept_id + ", age="
				+ age + ", id=" + id + ", name=" + name + ", status=" + status
				+ "]";
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
