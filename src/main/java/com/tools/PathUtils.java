package com.tools;

/**
 * <p>描述：oms_server工程中的Java类[PathUtils]</p>
 *
 * 类描述：系统启动时根据配置文件初始化
 *
 * @version 1.0
 **/
public class PathUtils {
	
	/**
	 * tomcat路径
	 */
	public static String TOMCAT_PATH;
	
	/**
	 * 上传使用的web路径
	 */
	public static String UPLOAD_ROOT;
	
	/**
	 * 上传使用的文件真实路径
	 */
	public static String UPLOAD_PATH;
	
	/**
	 * #发布的项目根地址
	 */
	public static String HTTP_ROOT_URL;
	
	/**
	 * #发布的项目HTTPS根地址
	 */
	public static String HTTPS_ROOT_URL;
	
	/**
	 * admin密码
	 */
	public static String ADMIN_LOGIN_KEY;
	
	/**
	 * 是否启用发货时自动标记发货
	 */
	public static String MARKSHIPPED_ISAUTO;
	
	public static void setTomcatPath(String tomcatPath) {
		//System.out.println("tomcatPath--"+tomcatPath);
		TOMCAT_PATH = tomcatPath;
	}

	public static void setHttpRootUrl(String httpRootUrl) {
		//System.out.println("httpRootUrl--"+httpRootUrl);
		HTTP_ROOT_URL = httpRootUrl;
	}

	public static void setHttpsRootUrl(String httpsRootUrl) {
		//System.out.println("httpsRootUrl--"+httpsRootUrl);
		HTTPS_ROOT_URL = httpsRootUrl;
	}

	public static void setAdminLoginKey(String adminLoginKey) {
		ADMIN_LOGIN_KEY = adminLoginKey;
	}
	
	public static void setUploadRoot(String uploadRoot) {
		UPLOAD_ROOT = uploadRoot;
	}

	public static void setUploadPath(String uploadPath) {
		UPLOAD_PATH = uploadPath;
	}

	public static void setMarkShippedIsAuto(String markShippedIsAuto) {
		MARKSHIPPED_ISAUTO = markShippedIsAuto;
	}
}
