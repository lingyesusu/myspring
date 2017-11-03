package com.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;

/**
 * <p>描述：OSP工程中的Java类[SpringContextUtil]</p>
 *
 * 类描述：获得spring context
 *
 * @version 1.0
 **/
public class SpringContextUtils {
	
	/**
	 * Servlet context
	 */
	private static ServletContext svltContext;
	
	/**
	 * Spring  ApplicationContext
	 */
	private static ApplicationContext context;

	/**
	 * 获得context
	 * @return
	 */
	public static synchronized ApplicationContext getContext() {
		return context;
	}
	
	/**
	 * 获得bean
	 * @param beanName
	 * @return
	 */
	public static synchronized Object getBean(String beanName) {
		return context.getBean(beanName);
	}
	
	/**
	 * context 赋值
	 * @param applicationContext
	 */
	public static void setContext(ApplicationContext applicationContext) {
		if(context == null){
			context = applicationContext;
		}
	}

	/**
	 * 获得servletContext
	 * @return
	 */
	public static synchronized ServletContext getSvltContext() {
		return svltContext;
	}

	/**
	 * servletContext 赋值
	 * @param servletContext
	 */
	public static void setSvltContext(ServletContext servletContext) {
		if(svltContext == null){
			svltContext = servletContext;
		}
	}
}