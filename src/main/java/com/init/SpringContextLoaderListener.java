package com.init;

import java.math.BigDecimal;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.service.AuthorityService;
import com.util.SpringContextUtils;

/**
 *
 * 类描述：系统spring context loader listener
 *
 **/
public class SpringContextLoaderListener extends ContextLoaderListener {

	// 日志处理
	private static final Logger logger = LoggerFactory.getLogger(SpringContextLoaderListener.class);

	public void contextInitialized(ServletContextEvent event) {
		
		super.contextInitialized(event);
		
		ServletContext context = event.getServletContext();
		
		// 获取web环境下的ApplicationContext
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		
		SpringContextUtils.setContext(ctx);
		
		SpringContextUtils.setSvltContext(context);

		// 加载所有启动项
		initStartup(context, ctx);

		logger.info("初始化SpringContextLoaderListener完成...");
	}

	/**
	 * 加载所有启动项
	 * @param context
	 * @param ctx
	 */
	private void initStartup(ServletContext context, ApplicationContext ctx) {
		
		// 中国省份
		context.setAttribute("chinaArea", context.getAttribute("CHINA_AREA"));
		
		AuthorityService authorityService = (AuthorityService) ctx.getBean("authorityServiceImpl");
		authorityService.initAuthority();
		authorityService.initMenu();
		
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);   
		ConvertUtils.register(new LongConverter(null), Long.class);  
	    ConvertUtils.register(new ShortConverter(null), Short.class);  
	    ConvertUtils.register(new IntegerConverter(null), Integer.class);  
	    ConvertUtils.register(new DoubleConverter(null), Double.class);  
	    ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
	}
}