package com.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.service.AuthorityService;
import com.tools.PropertiesUtils;
import com.tools.SpringUtils;


public class WebInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 生成一个全局版本号
		String v = PropertiesUtils.getProperties("source.version", "1.0");
		sce.getServletContext().setAttribute("v", v);
		AuthorityService authorityService = (AuthorityService) SpringUtils.getBean("authorityServiceImpl");
		authorityService.initAuthority();
		authorityService.initMenu();
		sce.getServletContext().setAttribute("title", "ERP_GZ");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
