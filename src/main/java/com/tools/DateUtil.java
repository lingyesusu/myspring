package com.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static final long DAY_MILLIS = 86400000; // 一天的毫秒数
	public static final long HOUR_MILLIS = 3600000; // 一小时的毫秒数
	public static final long MINUTE_MILLIS = 60000;

	/**
	 * 获得当前系统时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		return new Date();
	}
	
	/**
	 * 获得给定时间的00:00:00时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeginOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获得给定时间的23:59:59时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 获得某月的第一天的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return getBeginOfDay(cal.getTime());
	}

	/**
	 * 获得某月的最后一天的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return getEndOfDay(cal.getTime());
	}

	/**
	 * 格式化:时间转化成时间戳
	 * @param date
	 * @return
	 */
	public static Timestamp formatToTimestamp(String date) {
		DateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp time = null;
		try {
			time = new Timestamp(sim.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;		
	}
	
	/**
	 * 格式化:时间戳转化成字符串
	 * @param
	 * @return
	 */
	public static String timeStampToString(long l, String fmt) {
		Date date=new Date(l);
		DateFormat formatter = new SimpleDateFormat(fmt);
		return formatter.format(date);
	}
	
	/**
	 * 格式化:时间转化成
	 * 
	 * @param date
	 * @param fmt
	 * @return
	 */
	public static String formatToString(Date date, String fmt) {
		DateFormat formatter = new SimpleDateFormat(fmt);
		return formatter.format(date);
	}

	/**
	 * 格式化:字符串转化成时间
	 * 
	 * @param date
	 * @param fmt
	 * @return
	 * @throws ParseException
	 */
	public static Date format(String date, String fmt) throws ParseException {
		DateFormat formatter = new SimpleDateFormat(fmt);
		return formatter.parse(date);
	}
	
	/**
	 * n天前或后 + -
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return new Date(cal.getTime().getTime());
	}
	
	
	/**
	 * 格式化:时间转化成时间戳
	 * @param date
	 * @return
	 */
	public static Timestamp StringToTimestamp(String date) {
		DateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp time = null;
		try {
			time = new Timestamp(sim.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;		
	}
	
}
