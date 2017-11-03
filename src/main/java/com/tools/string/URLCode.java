package com.tools.string;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>描述 :【OSP】工程中的Java类【URLCode.java】</p>

 * <p>功能概述 :    中文转码         </p>
 * @version V1.0
 */
public class URLCode {

	public static String defaultEnCode = "UTF-8";

	/**
	 * 传中文编码Method
	 * @param url
	 * @return
	 */
	public static String encode(String url){
		if(StringUtils.isBlank(url)){
			return "";
		}
		try {
			return URLEncoder.encode(url, defaultEnCode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	/**
	 * 传中文解码Method
	 * @param url
	 * @return
	 */
	public static String decode(String url) {
		try {
			return java.net.URLDecoder.decode(url, defaultEnCode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
     * URL编码 (符合FRC1738规范)
     * @param input 待编码的字符串
     * @param charset 字符编码
     * @return 编码后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String encodeURL(String input, String charset) throws UnsupportedEncodingException {
    	return URLEncoder.encode(input, charset).replace("+", "%20").replace("*", "%2A");
    }
    
}
