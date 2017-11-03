package com.tools.json;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.JSONArray;
/**
 * <p>JSON处理工具类</p>
 * @version 1.0
 **/
public class JSONUtils {
	
	/**
	 * 默认的JsonConfig配置
	 * @return
	 */
	private static JsonConfig getJsonConfig() {
		JsonConfig config = new JsonConfig();
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); 
		//config.setExcludes(new String[]{"handler", "hibernateLazyInitializer"}); 
		return config;
	}

	/**
	 * 将对象转换为json，使用默认的JsonConfig配置
	 * @param obj
	 * @return
	 */
	public static String fromObject(Object obj) {
		return fromObject(obj, getJsonConfig());
	}
	
	/**
	 * 将对象转换为json，自定义JsonConfig配置
	 * @param obj
	 * @param config
	 * @return
	 */
	public static String fromObject(Object obj, JsonConfig config) {
		JSONArray json = JSONArray.fromObject(obj, config);
		return String.valueOf(json);
	}
	
}
