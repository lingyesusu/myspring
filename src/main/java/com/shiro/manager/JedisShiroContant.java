package com.shiro.manager;

import com.shiro.filter.KickoutSessionControlFilter;

public class JedisShiroContant {
	//缓存
	public static final int REDIS_CACHE_DB_INDEX = 0;
	
	public static final int REDIS_SHIRO_CACHE_DB_INDEX = 1;
	
	public static final int REDIS_SHIRO_SESSION_DB_INDEX = 1;
    
	public static final String REDIS_SHIRO_CACHE = "shiro-demo-cache:";

	public static final String REDIS_SHIRO_SESSION_STATUS ="sojson-online-status";
	
	public static final String REDIS_SHIRO_SESSION = "sojson-shiro-demo-session:";
	
	public static final String REDIS_SHIRO_ALL = "*sojson-shiro-demo-session:*";
	
	public static final String REDIS_SHIRO_CACHE_PASSWORDRETRY = "passwordRetryCache:";
	//在线用户
	public final static String REDIS_SHIRO_ONLINE_USER = KickoutSessionControlFilter.class.getCanonicalName()+ "_online_user";
	//踢出状态，true标示踢出
	public final static String REDIS_SHIRO_KICKOUT_STATUS = KickoutSessionControlFilter.class.getCanonicalName()+ "_kickout_status";
    
    public static final int REDIS_SHIRO_SESSION_VAL_TIME_SPAN = 18000;

}
