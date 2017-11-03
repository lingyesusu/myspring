package com.service;

public interface AuthorityService {
//public interface AuthorityService extends BaseService<Resources> {

	/**
	 * 初始化权限
	 */
	public void initAuthority();
	
	/**
	 * 初始化菜单
	 */
	public void initMenu();
	
}
