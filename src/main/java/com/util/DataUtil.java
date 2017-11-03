package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * 主要用于数据处理
 * 
 */
public class DataUtil {

	/**
	 * 四舍五人
	 * 
	 * @param data
	 *            数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List round45(List<Map<String, Object>> datas) {
		List<Map<String, Object>> rtDatas = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> data : datas) {
			Iterator<String> keys = data.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				Object value = data.get(key);
				if (value != null && StringUtils.isDouble(value.toString())) {
					BigDecimal bd = new BigDecimal(Double.parseDouble(value
							.toString()));
					data.put(key, bd.setScale(2, BigDecimal.ROUND_HALF_UP)
							.doubleValue());
				}
			}
			rtDatas.add(data);
		}
		return rtDatas;
	}

	/**
	 * 把request中的前台提交过来的数据转换成MAP
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> getParams(HttpServletRequest request) {
		Map<String, String[]> properties = request.getParameterMap();
		Map<String, String> returnMap = new HashMap<>();
		Iterator<Entry<String, String[]>> entries = properties.entrySet()
				.iterator();
		Map.Entry<String, String[]> entry;
		String name = "";
		while (entries.hasNext()) {
			String value = "";
			entry = (Map.Entry<String, String[]>) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value += values[i].trim() + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString().trim();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}

	/**
	 * 主要是将数据值转换成对应类型的数据,有需要请补充扩展
	 * 
	 * @param val
	 *            数据值
	 * @param clazz
	 *            参数类型
	 * @return
	 * @throws ParseException
	 *             日期格式化异常
	 */
	private static Object check(Object val, Class<?> clazz) {
		try {
			if (clazz == String.class)
				return String.valueOf(val).trim();
			if (clazz == int.class || clazz == Integer.class) {
				return Integer.parseInt(String.valueOf(val));
			} else if (clazz == double.class || clazz == Double.class) {
				return Double.parseDouble(String.valueOf(val));
			} else if (clazz == Date.class) {// 日期如果两次格式错误则抛出
				Date date = null;
				try {
					DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
					date = fmt.parse(String.valueOf(val));
				} catch (ParseException e) {
					DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					date = fmt.parse(String.valueOf(val));
				}
				return date;
			} else if (clazz == BigDecimal.class) {
				BigDecimal bd = new BigDecimal(String.valueOf(val));
				return bd;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将map中的数据填充到对象T中(如果T存在此字段)
	 * 
	 * @param map
	 * @param t
	 * @return null表示封装数据后校验完成 如果有错误则返回具体错误信息
	 * @throws Exception
	 */
	public static String replaceData(Map<?, ?> map, Object t) throws Exception {
		if (t == null)
			throw new IllegalArgumentException("填充对象不能为空");
		Class<?> clazz = t.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Object key : map.keySet()) {// 如果只匹配字段，可以使用直接获取Field的方法，不必循环全部Field
			for (Field f : fields) {
				f.setAccessible(true);
				if (f.getName().equals(key)) {// 如果字段名一致
					f.set(t, check(map.get(key), f.getType()));
					break;
				} else {// 字段名不一致时判断对应的数据库字段
					if (f.isAnnotationPresent(Column.class)) {
						Column column = f.getAnnotation(Column.class);
						if (key.equals(column.name())) {
							f.set(t, check(map.get(key), f.getType()));
							break;
						}
					} else if (f.isAnnotationPresent(JoinColumn.class)) {// 关联对象进行数据映射,只进行xxx.xxx一级映射处理，
																			// xxx.xxx.xxx等超过二级映射不处理
						// 关联对象的处理不做数据库字段的对比，只进行字段对比
						String $key = ((String) key);
						if ($key.contains(".")) {
							String[] $keys = $key.split("[.]");
							if ($keys.length == 2) {// 如果存在 xxx.属性 的key
								if (f.getName().equalsIgnoreCase($keys[0])) {//
									Object obj = f.get(t);// 关联对象
									try {
										if (obj == null)
											obj = f.getType().newInstance();
										Field $field = obj.getClass()
												.getDeclaredField($keys[1]);// 找不到此字段时直接异常
										$field.setAccessible(true);
										$field.set(
												obj,
												check(map.get(key),
														$field.getType()));

										f.set(t, obj);// 将关联对象设置回源对象
										break;
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		}
		return validator(t);
	}

	/**
	 * 校验对象
	 * 
	 * @param obj
	 * @return 返回null表示校验通过，否则有具体的字符串信息详情
	 */
	public static String validator(Object obj) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator
				.validate(obj);
		if (constraintViolations != null && !constraintViolations.isEmpty()) {
			StringBuilder msg = new StringBuilder();
			for (ConstraintViolation<?> constraintViolation : constraintViolations) {
				msg.append(constraintViolation.getMessage() + "\r\n");
			}
			return msg.toString();
		}
		return null;
	}

	/**
	 * 把数据组装到模型类中，并返回该类型的实体对象
	 * @param clzz 模型类
	 * @param data 数据
	 * @return
	 */
	public static Object getEntityInstance(Class<?> clzz, Map<String,String> data) {
		Object obj = null;
		try {
			obj = clzz.newInstance();			
			Field[] fields = clzz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String methodName = fields[i].getName();
				String value = data.get(methodName);
				if(fields[i].getModifiers()<8 && StringUtils.isNotBlank(value)){					
					if(String.class.getName().equals(fields[i].getType().getName())){
						Method m = obj.getClass().getDeclaredMethod(parSetName(methodName),String.class);
						m.invoke(obj, value);				
					}else if(Integer.class.getName().equals(fields[i].getType().getName())){
						if(value.split(",").length>1||value.split(" ").length>1){
							continue;
						}
						Method m = obj.getClass().getDeclaredMethod(parSetName(methodName),Integer.class);					
						m.invoke(obj, Integer.parseInt(value));					
					
					}else if(Timestamp.class.getName().equals(fields[i].getType().getName())){
						Method m = obj.getClass().getDeclaredMethod(parSetName(methodName),Timestamp.class);
						try {
							DateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
							m.invoke(obj, new Timestamp(sim.parse(value).getTime()));	
						} catch(ParseException e){
							try {
								DateFormat sim = new SimpleDateFormat("yyyy-MM-dd");;
								m.invoke(obj, new Timestamp(sim.parse(value).getTime()));
							} catch(ParseException e2){
								DateFormat sim = new SimpleDateFormat("yyyy/MM/dd");;
								m.invoke(obj, new Timestamp(sim.parse(value).getTime()));
							}
						}
					}else if(Date.class.getName().equals(fields[i].getType().getName())){
						Method m = obj.getClass().getDeclaredMethod(parSetName(methodName),Date.class);						
						try {
							DateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");									
							m.invoke(obj, sim.parse(value));	
						} catch(ParseException e){
							try {
								DateFormat sim = new SimpleDateFormat("yyyy-MM-dd");									
								m.invoke(obj, sim.parse(value));
							} catch(ParseException e2){
								DateFormat sim = new SimpleDateFormat("yyyy/MM/dd");									
								m.invoke(obj, sim.parse(value));
							}
						}
					}else if(BigDecimal.class.getName().equals(fields[i].getType().getName())){
						if(value.split(",").length>1||value.split(" ").length>1){
							continue;
						}
						Method m = obj.getClass().getDeclaredMethod(parSetName(methodName),BigDecimal.class);
						m.invoke(obj, BigDecimal.valueOf(Double.valueOf(value)));
					}else if(Double.class.getName().equals(fields[i].getType().getName())){
						if(value.split(",").length>1||value.split(" ").length>1){
							continue;
						}
						Method m = obj.getClass().getDeclaredMethod(parSetName(methodName),Double.class);
						m.invoke(obj, Double.valueOf(value));
					}
					
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 拼接某属性的 get方法
	 * 
	 * @param fieldName
	 * @return String
	 */
	public static String parGetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		int startIndex = 0;
		if (fieldName.charAt(0) == '_')
			startIndex = 1;
		return "get"
				+ fieldName.substring(startIndex, startIndex + 1).toUpperCase()
				+ fieldName.substring(startIndex + 1);
	}

	/**
	 * 拼接在某属性的 set方法
	 * 
	 * @param fieldName
	 * @return String
	 */
	public static String parSetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		int startIndex = 0;
		if (fieldName.charAt(0) == '_')
			startIndex = 1;
		return "set"
				+ fieldName.substring(startIndex, startIndex + 1).toUpperCase()
				+ fieldName.substring(startIndex + 1);
	}
	
	/**
	 * 获取实体所有的数据库字段名，以,号分割
	 * 
	 * @param cls
	 * @return
	 */
	public static String getEntityColumn(Class<?> cls) {
		StringBuilder sb = new StringBuilder();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Column col = fields[i].getAnnotation(Column.class);
			if (col != null)
				sb.append(col.name()).append(",");
		}
		int end = sb.length() > 0 ? sb.length() - 1 : sb.length();
		return sb.substring(0, end);
	}
	
	/**
	 * 返回逗号分隔键值对
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,String> getQueryParam(Map params){
		Map<String,String> rt = new HashMap<String,String>();
		String keys = "";
		String values = "";
		Iterator iterator = params.keySet().iterator();
		boolean isFirst = true;
		while(iterator.hasNext()){
			String key = iterator.next().toString();
			Object value = params.get(key);
			if(value!=null && value.toString().length()>0){
				if(isFirst){
					keys += key;
					values += value;
					isFirst= false;
				}else{
					keys += ","+key;
					values += ","+value;
				}
				
			}
		}
		rt.put("key", keys);
		rt.put("value", values);
		return rt;
	}
	
}
