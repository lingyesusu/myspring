package com.tools.json;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * <p>类描述: 为google gson api 设置的辅助工具类
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GsonHelper {

	private final static Gson GSON_BUILD = new Gson();
	
	/**
	 * 转jsonArray字符串为对象
	 * @param jsonArray_str
	 * @return
	 */
	public static Map jsonArray2Obj(String jsonArray_str){
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(jsonArray_str);
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
		return json2ObjTf(jsonObject.toString());
	}
	
	/**
	 * 转换对象为json数据
	 * @param obj
	 * @return
	 */
	public static String obj2Json(Object obj) {
		return GSON_BUILD.toJson(obj);
	}

	/**
	 * 转换json数据为对象
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T json2Obj(String json, Class<T> classOfT) {
		return GSON_BUILD.fromJson(json, classOfT);
	}

	/**
	 * 转换json数据为对象列表
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> List<T> json2List(String json, Class<T> classOfT) {
		return GSON_BUILD.fromJson(json, new TypeToken<List<T>>() {
		}.getType());
	}

	/**
	 * 转换json数据为对象
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static Map json2Obj(String json) {
		if (StringUtils.isNotEmpty(json)) {
			return json2Obj(json, Map.class);
		} else {
			return null;
		}
	}

	/**
	 * 转换json数据为对象(自动将double转回Long)
	 * @param json
	 * @return
	 */
	public static Map json2ObjTf(String json) {
		Map res = json2Obj(json);
		for (Iterator ite = res.entrySet().iterator(); ite.hasNext();) {
			Map.Entry entry = (Map.Entry) ite.next();
			String key = entry.getKey().toString();
			if (Double.class.equals(entry.getValue().getClass())) {
				res.put(key, ((Double) entry.getValue()).longValue());
			}
		}
		return res;

	}

	/**
	 * 转换json数据为对象列表
	 * @param json
	 * @return
	 */
	public static List<Map> json2List(String json) {
		if (StringUtils.isNotEmpty(json)) {
			return json2List(json, Map.class);
		} else {
			return null;
		}
	}

	/**
	 * 转换json数据为对象列表(自动将double转回Integer)
	 * @param json
	 * @return
	 */
	public static List<Map> json2ListTf(String json) {
		List<Map> res = json2List(json);
		for (Map map : res) {
			for (Iterator ite = map.entrySet().iterator(); ite.hasNext();) {
				Map.Entry entry = (Map.Entry) ite.next();
				String key = entry.getKey().toString();
				if (Double.class.equals(entry.getValue().getClass())) {
					map.put(key, ((Double) entry.getValue()).intValue());
				}
			}
		}
		return res;
	}

}