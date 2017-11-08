package com.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wuxz
 * @explain
 */
public class SQLHelper {
	
	public static String converToCountSql(String sql) {
		StringBuilder newSql = new StringBuilder("select count(0) from ");
		Pattern p1 = Pattern.compile("(.*)(group\\s*by\\s+)(.*)",Pattern.CASE_INSENSITIVE);// 判断是否有分组
		Matcher matcher = p1.matcher(sql);
		if (matcher.find()) {
			newSql.append("(").append(sql).append(") countTmp ");
		} else {
			String regx = "(\\s*select\\s*.*from)([\\w*||\\W*||\\s*]*)";
			p1 = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
			matcher = p1.matcher(sql);
			String other = null;
			if (matcher.find()) {
				other = matcher.group(2);
			}
			newSql.append(other);
		}
		return newSql.toString();
	}
	
	public static String converToCountSql2(String sql) {
		StringBuilder newSql = new StringBuilder("select count(0) from ( ");
		newSql.append(sql.replaceFirst("(?i)^\\s*select.*?from", "select 1 from"));
		newSql.append(" ) countTmp ");
		return newSql.toString();
	}
	
}
