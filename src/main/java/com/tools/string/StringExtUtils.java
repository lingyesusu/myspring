package com.tools.string;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
/**
 * <p>描述：OSP工程中的Java类[StringUtil]</p>
 *
 * 类描述：字符串工具类
 *
 * @version 1.0
 **/
public class StringExtUtils {
	
	// 默认格式化字符串
	public static String defaultFormatString = "yyyyMMddHHmmss";
	
	/**
	 * 判断字符串是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (isEmpty(str)){
			return false;
		}
		return Pattern.compile("[0-9]*").matcher(str.trim()).matches();   
	}
	
	/**
	 * 判断字符串是否是电话号码
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str) {
		if (isEmpty(str)){
			return false;
		}
		return Pattern.compile("([0]{1}[0-9]{2,3}[0-9]{7,8}|[1]{1}[3,5,8,6]{1}[0-9]{9})").matcher(str.trim()).matches();   
	}
	
	/**
	 * 判断字符串是否是固定电话号码
	 * @param str
	 * @return
	 */
	public static boolean isTelphone(String str) {
		if (isEmpty(str)){
			return false;
		}
		return Pattern.compile("[0]{1}[0-9]{2,3}[0-9]{7,8}").matcher(str.trim()).matches();   
	}
	
	/**
	 * 判断字符串是否是固定电话号码
	 * @param str
	 * @return
	 */
	public static boolean isMobilePhone(String str) {
		if (isEmpty(str)){
			return false;
		}
		return Pattern.compile("[1]{1}[3,5,8,6]{1}[0-9]{9}").matcher(str.trim()).matches();   
	}
	
	/**
	 * 判断字符串组是否是数字
	 * @param strs
	 * @return
	 */
	public static boolean isNumeric(String... strs) {
		if (strs == null){
			return false;
		}
		for (int i = 0; i < strs.length; i++) {
			if (!isNumeric(strs[i])){
				return false;
			}
		}
		return true; 
	}
	
	/**
	 * 判断字符串是否是为double
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {   
		if (isEmpty(str)){
			return false;
		}
	    return Pattern.compile("^[-\\+]?\\d+(\\.\\d*)?|\\.\\d+$").matcher(str.trim()).matches();   
	} 
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() <= 0){
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串数组是否为空
	 * @param strs
	 * @return
	 */
	public static boolean isEmpty(String... strs) {
		if (strs == null){
			return true;
		}
		for (int i = 0; i < strs.length; i++) {
			if (isEmpty(strs[i])){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断字符串是否为null
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str == null){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断数组元素是否为空
	 * @param strs
	 * @return
	 */
	public static boolean isNull(String... strs) {
		if (strs == null){
			return true;
		}
		for (int i = 0; i < strs.length; i++) {
			if (!isNull(strs[i])){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断字符串是否符合日期格式
	 * @param formatStr
	 * @param strs
	 * @return
	 */
	public static boolean isDate(String formatStr, String... strs) {
		if (strs == null){
			return false;
		}
		for (int i = 0; i < strs.length; i++) {
			if (!isDate(strs[i], formatStr)){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 判断字符串是否符合日期格式
	 * @param str
	 * @return
	 */
	public static boolean isDate(String str) {
		return isDate(str, defaultFormatString);
	}
	
	/**
	 * 判断字符串是否符合日期格式
	 * @param str
	 * @param formatStr
	 * @return
	 */
	public static boolean isDate(String str, String formatStr) {
		if (isEmpty(str)) {
			return false;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		try {
			formatter.format(formatter.parse(str.trim()));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 去除换行符
	 * @param str
	 * @return
	 */
	public static String trimLineBreak(String str) {
		if (isEmpty(str)) {
			return str;
		}
		return str.replaceAll("\n", "").replaceAll("\r", "");
	}
	
	/**
	 * 左侧补0
	 * @param n
	 * @return
	 */
	public static String appendLeftZero(int n, int len) {
		return appendLeftZero(new Integer(n).toString(), len);
	}
	
	/**
	 * 右侧补0
	 * @param n
	 * @return
	 */
	public static String appendRightZero(int n, int len) {
		return appendRightZero(new Integer(n).toString(), len);
	}
	
	/**
	 * 左侧补0
	 * @param l
	 * @return
	 */
	public static String appendLeftZero(long l, int len) {
		return appendLeftZero(new Long(l).toString(), len);
	}
	
	/**
	 * 右侧补0
	 * @param l
	 * @return
	 */
	public static String appendRightZero(long l, int len) {
		return appendRightZero(new Long(l).toString(), len);
	}
	
	/**
	 * 左侧补0
	 * @param str
	 * @return
	 */
	public static String appendLeftZero(String str, int len) {
		String result = new String(str);
		while(result.length() < len) {   
			result = "0" + result;   
		}
		return result;
	}
	
	/**
	 * 右侧补0
	 * @param str
	 * @return
	 */
	public static String appendRightZero(String str, int len) {
		String result = new String(str);
		while(result.length() < len) {   
			result = result + "0";   
		}
		return result;
	}
	
	/**
	 * 右侧补0
	 * @param str
	 * @return
	 */
	public static String changStringArray(String[] stringArray, String split) {
		if(stringArray == null || stringArray.length <= 0 ){
			return "" ;
		}
		if(split == null) split = ",".intern() ;
		String result = "".intern() ;
		for(String temp : stringArray){
			result += (temp + split) ;
		}
		result = result.substring(0 , result.length() - 1) ;
		return result;
	}
	
	/**
	 * 根据String获取Long型数据
	 * @param value
	 * @return
	 */
	public static Long getLong(String value) {
		if (value != null) {
			try {
				return new Long(value);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 根据String获取Float型数据
	 * @param value
	 * @return
	 */
	public static Float getFloat(String value) {
		if (value != null) {
			try {
				return new Float(value);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 将字符串(以某种分割符号分割)转换为Long数组
	 * @author Darren
	 * @param value
	 * @param split
	 * @return Long[]
	 */
	public static Long[] getLongArray(String value,String split) {
		Long[] longArray=null;
		String[] stringArray=value.split(split);
		if(stringArray.length!=0){
			longArray=new Long[stringArray.length];
			for (int i=0;i<longArray.length;i++)
				longArray[i]=Long.parseLong(stringArray[i]);
		}
		return longArray;
	}

	/**
	 * 获取某一变量的字符串值
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj){
		if(obj==null){
			return "";
		}else{
			return String.valueOf(obj);
		} 
	}

	/**
	 * 将null字符串对象转换为空字符""
	 * @param input
	 * @return
	 */
	public static String trim(String input) {
		return input == null ? "" : input.trim();
	}
	
	/**
	 * 字符串处理
	 * @param str
	 * @return
	 */
	public static String splitStr(String str){
		if(str.indexOf(".0")!=-1){
			str = str.substring(0,str.indexOf(".0"));
		}
		return str;
	}
	
	/**
	 * 对象转换成字符串
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		if(obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}
	
	/**
	 * 处理字符串，不足补0位
	 * @param input
	 * @param len
	 * @param padChar
	 * @return
	 */
	public static String lpad(String input, int len, String padChar) {
		if (input.length() >= len) {
			return input;
		}
		//
		StringBuffer sb = new StringBuffer(input);
		for (int i = 0, j = len - input.length(); i < j; i++) {
			sb.insert(0, padChar);
		}
		return sb.toString();
	}

}