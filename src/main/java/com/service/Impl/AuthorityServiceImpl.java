package com.service.Impl;

import org.springframework.stereotype.Service;
import com.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Override
	public void initAuthority() {
		System.out.println("initAuthority");
		
	}

	@Override
	public void initMenu() {
		System.out.println("initMenu");
		
	}
	
}
//@Service
//public class AuthorityServiceImpl extends BaseServiceImpl<Resources> implements AuthorityService {
//	
//	@Resource
//	private ResourcesDao resourcesDao;
//	@Resource
//	private RoleDao roleDao;
//	@Resource
//	private RolePermissionDao rolePermissionDao;
//	@Resource
//	private PermissionDao permissionDao;	
//
//	
//	@Resource
//	public void setBaseDao(ResourcesDao resourcesDao) {
//		super.setBaseDao(resourcesDao);
//	}
//
//	@Override
//	public void initAuthority() {
//		List<Map<String,String>> permList = AuthorityUtils.getAllPermission();
//		//删除数据库中多余的数据
//		List<Permission> tbPermList = permissionDao.getAll(null, null, false);				
//		for(Permission tbPerm:tbPermList){
//			String tbCode = tbPerm.getCode();
//			String tbMenuUrl = tbPerm.getMenuUrl();
//			
//			boolean isDel = true;
//			for(Map<String,String> perm:permList){
//				String code = perm.get("code");
//				String menuUrl = perm.get("menuUrl");
//				if(tbCode.equals(code) && tbMenuUrl.equals(menuUrl)){
//					isDel = false;
//					break;
//				}
//			}
//			if(isDel){
//				permissionDao.delete(tbPerm.getId());
//				String[] propName = {"permissionId"};
//				Object[] propValue = {tbPerm.getId()};
//				rolePermissionDao.deleteByProperties(propName, propValue);
//			}
//			
//		}
//		
//		//插入在数据库中没有的数据
//		tbPermList = permissionDao.getAll(null, null, false);	
//		
//		for(Map<String,String> perm:permList){
//			String code = perm.get("code");
//			String menuUrl = perm.get("menuUrl");
//			String name = perm.get("name");
//			
//			boolean isSave = true;
//			for(Permission tbPerm:tbPermList){
//				String tbCode = tbPerm.getCode();
//				String tbMenuUrl = tbPerm.getMenuUrl();
//				if(tbCode.equals(code) && tbMenuUrl.equals(menuUrl)){
//					isSave = false;
//					break;
//				}
//			}
//			if(isSave){
//				Permission permission = new Permission();
//				permission.setCode(code);
//				permission.setMenuUrl(menuUrl);
//				permission.setName(name);
//				permissionDao.save(permission);
//			}
//			
//		}					
//	}
//
//	@Override
//	public void initMenu() {
//		List<Map<String,String>> menuList = AuthorityUtils.getAllMenu();
//		
//		QueryParam params = new QueryParam();
//		params.add("level!=", 1);
//		params.add("status", 1);
//		List<Resources>  resourceList = resourcesDao.getList(params, null, null, null, null, false);
//			
//		for(Resources resources:resourceList){
//			String url = resources.getUrl();
//			
//			boolean isDel = true;
//			for(Map<String,String> menu:menuList){
//				String menuUrl = menu.get("menuUrl");			
//				if(url.equals(menuUrl)){
//					isDel = false;
//					break;
//				}
//			}
//			if(isDel){
//				resourcesDao.delete(resources.getId());
//			}			
//		}
//		
//		resourceList = resourcesDao.getList(params, null, null, null, null, false);			
//		for(Map<String,String> menu:menuList){
//			String menuUrl = menu.get("menuUrl");
//			String name = menu.get("name");
//			
//			boolean isSave = true;
//			for(Resources resources:resourceList){
//				String url = resources.getUrl();			
//				if(url.equals(menuUrl)){
//					isSave = false;
//					break;
//				}
//			}
//			if(isSave){
//				Resources resources = new Resources();
//				resources.setLevel(2);
//				resources.setStatus(1);
//				resources.setParentId(3);
//				resources.setSort(1);
//				resources.setUrl(menuUrl);
//				resources.setName(name);
//				resourcesDao.save(resources);
//			}
//			
//		}
//	}
//
//	
//}
