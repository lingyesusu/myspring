package com.tools;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class StringUtils {

	public static final String EMPTY = "";

	public StringUtils() {
		super();
	}

	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public static boolean isNotEmpty(CharSequence cs) {
		return !StringUtils.isEmpty(cs);
	}

	public static Integer[] arrayToInteger(String[] strs) {
		if (strs == null) {
			return null;
		}
		Integer[] tointarray = new Integer[strs.length];
		for (int i = 0; i < strs.length; i++) {
			tointarray[i] = Integer.parseInt(strs[i]);
		}
		return tointarray;
	}

	// 传入的CharSequence是String的接口，同样StringBuffer这些也是，可适用这里。Sequence的英语是序列的意思。
	public static boolean isBlank(CharSequence cs) {
		// 标记字符长度，
		int strLen;
		// 字符串不存在或者长度为0
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			// 判断空格，回车，换行等，如果有一个不是上述字符，就返回false
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	// 这个是isNotBlank()
	public static boolean isNotBlank(CharSequence cs) {
		return !StringUtils.isBlank(cs);
	}

	/**
	 * <p>
	 * Gets the substring before the first occurrence of a separator. The
	 * separator is not returned.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> string input will return <code>null</code>. An empty
	 * ("") string input will return the empty string. A <code>null</code>
	 * separator will return the input string.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substringBefore(null, *)      = null
	 * StringUtils.substringBefore("", *)        = ""
	 * StringUtils.substringBefore("abc", "a")   = ""
	 * StringUtils.substringBefore("abcba", "b") = "a"
	 * StringUtils.substringBefore("abc", "c")   = "ab"
	 * StringUtils.substringBefore("abc", "d")   = "abc"
	 * StringUtils.substringBefore("abc", "")    = ""
	 * StringUtils.substringBefore("abc", null)  = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to get a substring from, may be null
	 * @param separator
	 *            the String to search for, may be null
	 * @return the substring before the first occurrence of the separator,
	 *         <code>null</code> if null String input
	 * @since 2.0
	 */
	public static String substringBefore(String str, String separator) {
		if (isEmpty(str) || separator == null) {
			return str;
		}
		if (separator.length() == 0) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	/**
	 * <p>
	 * Gets the substring after the first occurrence of a separator. The
	 * separator is not returned.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> string input will return <code>null</code>. An empty
	 * ("") string input will return the empty string. A <code>null</code>
	 * separator will return the empty string if the input string is not
	 * <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substringAfter(null, *)      = null
	 * StringUtils.substringAfter("", *)        = ""
	 * StringUtils.substringAfter(*, null)      = ""
	 * StringUtils.substringAfter("abc", "a")   = "bc"
	 * StringUtils.substringAfter("abcba", "b") = "cba"
	 * StringUtils.substringAfter("abc", "c")   = ""
	 * StringUtils.substringAfter("abc", "d")   = ""
	 * StringUtils.substringAfter("abc", "")    = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to get a substring from, may be null
	 * @param separator
	 *            the String to search for, may be null
	 * @return the substring after the first occurrence of the separator,
	 *         <code>null</code> if null String input
	 * @since 2.0
	 */
	public static String substringAfter(String str, String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (separator == null) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == -1) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}

	/**
	 * 判断字符串是不是自然數
	 * 
	 * @param str
	 *            要判斷的字符串
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/* 左右空格都去掉 */
	public static String leftANDrightTrim(String str) {
		if (str == null || str.equals("")) {
			return str;
		} else {
			// return leftTrim(rightTrim(str));
			return str.replaceAll("^[　 ]+|[　 ]+$", "");
		}
	}

	/**
	 * 判断是否是手机号
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 内部系统验证KEY生成
	 * 
	 * @param key
	 * @return
	 */
	public static String getKey(String key) {
		String passwd = "";
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		int offset = getOffset();
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyHH");
		String tmp = sdf.format(cal.getTime());
		for (int i = 0; i < tmp.length(); i++) {
			int index = Integer.valueOf(String.valueOf(tmp.charAt(i))) + offset;
			index = index < key.length() ? index : index % key.length();
			passwd += key.charAt(index);
		}
		int last = cal.get(Calendar.MINUTE) / 10 + offset;
		last = last < key.length() ? last : last % key.length();
		passwd += key.charAt(last);
		return passwd;
	}

	private static int getOffset() {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 检测是否为纯数字(可包含空格)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (str != null && str.replaceAll("^\\s*\\d+\\s*$", "").equals(""))
			return true;
		return false;
	}

	/**
	 * 检测是否为数字或浮点类型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		if (str != null
				&& str.replaceAll("^\\s*\\d+(\\.\\d+)?\\s*$", "").equals(""))
			return true;
		return false;
	}

	/**
	 * 返回空表示异常
	 * 
	 * @param xml
	 * @param encoding
	 * @return
	 */
	public static String formatXML(String xml, String encoding) {
		XMLWriter xw = null;
		try {
			if (xml == null || xml.equals(""))
				return null;
			Document document = DocumentHelper.parseText(xml);
			// 格式化输出格式
			OutputFormat format = OutputFormat.createPrettyPrint();
			if (encoding == null)
				encoding = "UTF-8";
			format.setEncoding(encoding);
			format.setSuppressDeclaration(true);
			StringWriter sw = new StringWriter();
			// 格式化输出流
			xw = new XMLWriter(sw, format);
			// 将document写入到输出流
			xw.write(document);
			return sw.toString();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (xw != null)
					xw.close();
			} catch (IOException e) {
			}
		}
		return null;
	}

	/**
	 * 生成指定位数的由字母或数字组成的字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getCharAndNum(int length) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				str += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				str += String.valueOf(random.nextInt(10));
			}
		}
		return str;
	}		
	
}
