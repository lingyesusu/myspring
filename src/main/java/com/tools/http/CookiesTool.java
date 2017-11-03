package com.tools.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>描述：OSP工程中的Java类[CookiesTool]</p>
 * 类描述：cookes工具类
 *
 * @version 1.0
 **/
public class CookiesTool {

	/**
	 * 保存cookie
	 * @param cookie  需要保存的cookie
	 * @param response  响应对象
	 */
	public static void saveCookies(Cookie cookie, HttpServletResponse response) {
		try {
			cookie.setValue(URLEncoder.encode(cookie.getValue(), "utf-8"));
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取cookie
	 * @param name cookie的名称
	 * @param request  请求对象
	 * @return
	 */
	public static Cookie getCookies(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(name)) {
					cookie = c;
				}
			}
			if (cookie != null) {
				try {
					cookie.setValue(URLDecoder.decode(cookie.getValue(),
							"utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return cookie;
	}

	/**
	 * 删除cookie
	 * @param cookie 需要删除的某个cookie
	 * @param response 响应对象
	 */
	public static void deleteCookie(Cookie cookie, HttpServletResponse response) {
		if (cookie != null) {
			cookie.setMaxAge(0);
			saveCookies(cookie, response);
		}
	}
}