package com.tools;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>描述：OSP工程中的Java类[UniKeyHashMap]</p>
 *
 * 类描述：自动相加重复的key则对应的value
 * 
 * 修改说明：HashMap是非线程安全的，尝试更换为ConcurrentHashMap试运行
 *
 * @version 1.0
 **/
@SuppressWarnings("rawtypes")
public class AutoAddHashMap extends ConcurrentHashMap {
	
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		if (!this.containsKey(key)){
			return super.put(key, value);
		}else if(value instanceof Long){//Long类型
			Long preVal = (Long)super.get(key);
			super.put(key, preVal+(Long)value);//简单相加
			return null;
		}else if(value instanceof String){//String类型
			String preVal = (String)super.get(key);
			super.put(key, preVal+(Long)value);//简单相加
			return null;
		}// more...
		return null;
	}
}
