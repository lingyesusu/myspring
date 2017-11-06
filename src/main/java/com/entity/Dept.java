package com.entity;

public class Dept {
	
	private Integer id;
	private String department;
	private Integer parent_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	
	@Override
	public String toString() {
		return "Dept [id=" + id + ", department=" + department + ", parent_id="
				+ parent_id + "]";
	}

}
