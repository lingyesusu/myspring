package com.tools;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

public class PropertiesUtils {

	private static Logger logger = Logger.getLogger(PropertiesUtils.class);

	private static PropertiesConfiguration config;

	static {
		try {
			URL rootUrl = PropertiesUtils.class.getResource("/");
			String url = rootUrl.toURI().getPath() + "/config.properties";
			// AbstractConfiguration.setDefaultListDelimiter('-');
			config = new PropertiesConfiguration();
			config.setEncoding("UTF-8");
			config.load(url);
			config.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("config.properties配置文件加载失败");
		}
	}

	public static String getProperties(String key) {
		return config.getString(key, "");
	}

	public static String getPropertiesAll(String key) {
		StringBuilder str = new StringBuilder();
		String[] vals = PropertiesUtils.getPropertiesArray(key);
		if (vals != null) {
			for (String val : vals) {
				str.append(val).append(",");
			}
			if (str.length() > 0)
				str.deleteCharAt(str.length() - 1);
		}
		return str.toString();
	}

	public static String getProperties(String key, String defaultVal) {
		return config.getString(key, defaultVal);
	}

	public static String[] getPropertiesArray(String key) {
		return config.getStringArray(key);
	}

	public static List<Object> getPropertiesList(String key) {
		return config.getList(key);
	}

	/**
	 * 根据配置文件的键，获取数据成对的数据
	 * 
	 * @param prop_key
	 * @return
	 */
	public static Map<Integer, String> getPropData(String prop_key) {
		Map<Integer, String> results = new HashMap<Integer, String>();
		String[] tmp_all = getPropertiesArray(prop_key);
		Arrays.sort(tmp_all);
		for (String str : tmp_all) {
			String[] tmp = str.split("-");
			results.put(new Integer(tmp[0]), tmp[1]);
		}
		return results;
	}

	/**
	 * 根据配置文件的键，获取数据（车位要求、保险类型）
	 * 
	 * @param prop_key
	 * @return
	 */
	public static TreeMap<String, String> getPropDataByString(String prop_key) {
		TreeMap<String, String> results = new TreeMap<String, String>();
		String[] tmp_all = getPropertiesArray(prop_key);
		Arrays.sort(tmp_all);
		for (String str : tmp_all) {
			String[] tmp = str.split("-");
			results.put(tmp[0], tmp[1]);
		}
		return results;
	}

}
