package com.authority;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;

public class AuthorityUtils {

	private static final String packageName = "com.controller";
	
	/**
	 * 获取controller下所有的方法的角色和权限
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> getAllAuthority() {
		Set<Class<?>> classes = new LinkedHashSet<>();
		String packageDir = packageName.replace(".", "/");
		final boolean isRecursive = true;
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Enumeration<URL> dirs = loader.getResources(packageDir);
			URL url = dirs.nextElement();
			String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
			FileFilter cff = new FileFilter() {
				@Override
				public boolean accept(File file) {
					// 过滤规则(是否递归查找子目录、class文件)
					return (isRecursive && file.isDirectory())
							|| (file.getName().endsWith(".class") && file.getName().indexOf("$") == -1);
				}
			};
			findAllClass(packageName, filePath, cff, loader, classes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();				
		for (Class<?> cls : classes) {
			RequestMapping parMapping = cls.getAnnotation(RequestMapping.class);
			if(parMapping==null){
				continue;
			}
			String parURL = parMapping.value()==null?"":parMapping.value()[0];					
			Method[] methods = cls.getDeclaredMethods();
			for (Method method : methods) {	
				Map<String, Object> result = new HashMap<>();
				RequestMapping childMapping = method.getAnnotation(RequestMapping.class);
				if(childMapping==null){
					continue;
				}
				String childURL = childMapping.value()==null?"":childMapping.value()[0];				
				String url = parURL+childURL;				
				result.put("url", url);
				RequiresRoles roleManage = method.getAnnotation(RequiresRoles.class);				
				if (roleManage != null){
					List<String> roles = Arrays.asList(roleManage.value());
					result.put("roles", roles);
				}
				
				RequiresPermissions permissionManage = method.getAnnotation(RequiresPermissions.class);
				if (permissionManage != null){
					List<String> permissions = Arrays.asList(permissionManage.value());
					result.put("permissions", permissions);
				}
				
				RequiresMenu menuManage = method.getAnnotation(RequiresMenu.class);				
				if (menuManage != null){				
					result.put("menu", menuManage.value());
					result.put("name", menuManage.type());
				}
				resultList.add(result);
			}
			
		}
		return resultList;
	}

	/**
	 * 获取controller下所有的方法的权限
	 * 
	 * @return
	 */
	public static List<Map<String, String>> getAllPermission() {
		Set<Class<?>> classes = new LinkedHashSet<>();
		String packageDir = packageName.replace(".", "/");
		final boolean isRecursive = true;
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Enumeration<URL> dirs = loader.getResources(packageDir);
			URL url = dirs.nextElement();
			String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
			FileFilter cff = new FileFilter() {
				@Override
				public boolean accept(File file) {
					// 过滤规则(是否递归查找子目录、class文件)
					return (isRecursive && file.isDirectory())
							|| (file.getName().endsWith(".class") && file.getName().indexOf("$") == -1);
				}
			};
			findAllClass(packageName, filePath, cff, loader, classes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();	
		List<String> compare  = new ArrayList<String>();
		for (Class<?> cls : classes) {				
			Method[] methods = cls.getDeclaredMethods();			
			for (Method method : methods) {	
				Map<String, String> result = new HashMap<>();				
				RequiresPermissions permissionManage = method.getAnnotation(RequiresPermissions.class);
				if (permissionManage != null){
					List<String> permissionList = Arrays.asList(permissionManage.value());
					String permissions = "";
					boolean isFirst = true;
					for(String permission:permissionList){
						if(isFirst){
							permissions = permission;
							isFirst = false;
						}else{
							permissions = permissions + ","+permission;
						}
					}				
					result.put("code", permissions);
					//去重复
					if(compare.contains(permissions)){
						continue;
					}else{
						compare.add(permissions);
					}
				}else{
					continue;
				}
				
				RequiresMenu menuManage = method.getAnnotation(RequiresMenu.class);				
				if (menuManage != null){				
					result.put("menuUrl", menuManage.value());
					result.put("name", menuManage.type());					
				}else{
					continue;
				}
				resultList.add(result);
			}
			
		}
		return resultList;
	}
	
	/**
	 * 获取菜单
	 * 
	 * @return
	 */
	public static List<Map<String, String>> getAllMenu() {
		Set<Class<?>> classes = new LinkedHashSet<>();
		String packageDir = packageName.replace(".", "/");
		final boolean isRecursive = true;
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Enumeration<URL> dirs = loader.getResources(packageDir);
			URL url = dirs.nextElement();
			String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
			FileFilter cff = new FileFilter() {
				@Override
				public boolean accept(File file) {
					// 过滤规则(是否递归查找子目录、class文件)
					return (isRecursive && file.isDirectory())
							|| (file.getName().endsWith(".class") && file.getName().indexOf("$") == -1);
				}
			};
			findAllClass(packageName, filePath, cff, loader, classes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();	
		List<String> compare  = new ArrayList<String>();
		for (Class<?> cls : classes) {				
			Method[] methods = cls.getDeclaredMethods();			
			for (Method method : methods) {	
				Map<String, String> result = new HashMap<>();				
				RequiresMenu menuManage = method.getAnnotation(RequiresMenu.class);				
				if (menuManage != null){		
					String menuUrl = menuManage.value();
					if(compare.contains(menuUrl)){
						continue;
					}else{
						compare.add(menuUrl);
						result.put("menuUrl", menuUrl);
						result.put("name", "未命名菜单");
					}										
				}else{
					continue;
				}
				resultList.add(result);
			}
			
		}
		return resultList;
	}
	
	/**
	 * 递归查找指定包下所有的class
	 * 
	 * @param packageName
	 *            指定的包名
	 * @param filePath
	 *            指定包的物理路径
	 * @param fileFilter
	 *            自定义文件过滤
	 * @param loader
	 *            类加载器
	 * @param classes
	 *            保存结果的集合
	 * @throws ClassNotFoundException
	 */
	private static void findAllClass(String packageName, String filePath, FileFilter fileFilter, ClassLoader loader,
			Set<Class<?>> classes) {
		File dir = new File(filePath);
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		File[] dirfiles = dir.listFiles(fileFilter);
		for (File file : dirfiles) {
			if (file.isDirectory()) {
				findAllClass(packageName + "." + file.getName(), file.getAbsolutePath(), fileFilter, loader, classes);
			} else {
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					// 衍生的问题:Class.forName与ClassLoader.loadClass的区别
					classes.add(loader.loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
				}
			}
		}
	}

}
