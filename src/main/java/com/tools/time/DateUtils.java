package com.tools.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * <p>描述: 日期相关工具类</p>
 * @version 1.0
 **/
public class DateUtils {
	
	// 日志处理
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
		
	/**
	 * 时区名称
	 */
	public static final String zoneName = "GMT+8";
	
	/**
	 * 时间格式
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期格式
	 */
	public static final String YYYYMMDD = "yyyyMMdd";
	
	/**
	 * 字符串转换为日期
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static Date parseDate(String dateStr, String formatStr) {
		if (StringUtils.isBlank(formatStr)) {
			formatStr = DEFAULT_DATE_FORMAT;
		}
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
			//simpleDateFormat.setTimeZone(TimeZone.getTimeZone(zoneName));
			return simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.info("字符串转换为日期异常");
		}
		return null;
	}

	/**
	 * 获取（中国）当前时间并转换格式。用于DHL物流接口  格式：2016-11-25T10:40:11+08:00
	 */
	public static String getDateToCN(){
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss+z");
		return sdf.format( new Date()).replaceFirst(" ", "T").replace("CST", "08:00") ;
	}

	/**
	 * 默认格式的转换字符串为日期
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 日期格式化为字符串
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String formatDate(Date date, String dateFormat) {
		if (date == null) {
			return null;
		}
		if(StringUtils.isBlank(dateFormat)) {
			dateFormat = DEFAULT_DATE_FORMAT;
		}
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			return simpleDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("日期格式化为字符串异常");
		}
		return null;
	}
	
	/**
	 * 默认格式的日期格式化为字符串
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 默认格式的日期格式化为字符串
	 * @param date
	 * @return
	 */
	public static String formatDate(String date, String sourceFormat, String targetFormat) {
		return formatDate(parseDate(date, sourceFormat), targetFormat);
	}
	
	/**
	 * 日期相加
	 * @param date
	 * @param day
	 * @return
	 */
	public static String addDate(String date, String formatStr, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(parseDate(date, formatStr)) + ((long) day) * 24 * 3600 * 1000);
		return formatDate(c.getTime(), formatStr);
	}
	
	/**
	 * 返回毫秒
	 * @param date
	 * @return
	 */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}
	
	/**
	 * 返回字符型日期
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		return formatDate(date, "yyyyMMdd");
	}

	/**
	 * 返回字符型时间
	 * @param date
	 * @return
	 */
	public static String getTime(Date date) {
		return formatDate(date, "HHmmss");
	}

	/**
	 * 返回字符型日期时间
	 * @param date
	 * @return
	 */
	public static String getDateTime(Date date) {
		return formatDate(date, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 获取一个月有多少天
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		int maxDate = cal.getActualMaximum(Calendar.DATE);
		return maxDate;
	}
	
	/**
	 * 获取指定周的开始日期
	 * @param year
	 * @param week
	 * @return
	 */
	public static String getWeekStartDate(int year, int week) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return formatDate(cal.getTime(), "yyyy/MM/dd");
	}
	
	/**
	 * 获取当天的结束时间
	 * @param date
	 * @return
	 */
	public static Date getEndTimeOfDay(Date date){
		return DateUtils.addDay(DateUtils.getStartTimeOfDay(date), 1);
	}
	
	/**
	 * 返回年份
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * 返回月份
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回月份
	 * @param date
	 * @param add
	 * @return
	 */
	public static int getMonth(Date date, int add) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, add);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回周
	 * @param date  日期
	 * @return 返回周
	 */
	public static int getWeek(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.get(java.util.Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 返回日份
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回分钟
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * @param date
	 * @return
	 */
	public static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}
	
	/**
	  * 判断两参数是否为同一天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
	    if (date1==null || date2==null) {
	       return false;
	    }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
	
	/**
	 * 判断两参数是否为同一天，参数用毫秒表示时间
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean isSameDay(Long time1, Long time2){
		 if(time1==null || time2==null){
			 return false;
		 }else {
			 return isSameDay(new Date(time1), new Date(time2));
		}
	 }
	
	/**
	 * 添加分钟数,如果date为null取当时时间为date
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMinute(Date date,int amount){
		Calendar c = Calendar.getInstance();
		if(date != null){
			c.setTime(date);
		}
        c.add(Calendar.MINUTE, amount);
        return c.getTime();
	}
	
	/**
	 * 添加天数,如果date为null取当时时间为date
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDay(Date date,int amount){
        Calendar c = Calendar.getInstance();
        if(date != null){
			 c.setTime(date);
		 }
        c.add(Calendar.DAY_OF_MONTH, amount);
        return c.getTime();
	}
	
	/**
	 * 添加月份,如果date为null取当时时间为date
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMonth(Date date,int amount){
        Calendar c = Calendar.getInstance();
        if(date != null){
			 c.setTime(date);
		 }
        c.add(Calendar.MONTH, amount);
        return c.getTime();
    }
	
	/**
	 * 返回某天的分钟数
	 * @param date
	 * @return
	 */
	public static int getMinuteOfDay(Date date){
		 Calendar calendar = Calendar.getInstance();
		 if(date != null){
			 calendar.setTime(date);
		 }
		int nowMinuteOfDay = calendar.get(Calendar.HOUR_OF_DAY)*60+calendar.get(Calendar.MINUTE);
		return nowMinuteOfDay;
	}
	
	/**
	 * 获取当天的开始时间
	 * @param date
	 * @return
	 */
	public static Date getStartTimeOfDay(Date date){
		Calendar calendar = Calendar.getInstance();
		if(date != null){
			calendar.setTime(date);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取一天前的时间
	 * @param dateFormat
	 * @return
	 */
	public static String getYesterDay(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();
		if (dateFormat != null){
			return formatDate(date, dateFormat);
		}
		return formatDate(date, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 获取一周前的时间
	 * @param dateFormat
	 * @return
	 */
	public static String getBeforeWeek(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		Date date = cal.getTime();
		if (dateFormat != null){
			return formatDate(date, dateFormat);
		}
		return formatDate(date, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 获取一月前的时间
	 * @param dateFormat
	 * @return
	 */
	public static String getBeforeMonth(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		Date date = cal.getTime();
		if (dateFormat != null){
			return formatDate(date, dateFormat);
		}
		return formatDate(date, DEFAULT_DATE_FORMAT);
	}
	
	/**
	 * 获取指定周的开始日期
	 * @param year
	 * @param week
	 * @param formatStr
	 * @return
	 */
	public static String getWeekStartDate(int year, int week, String formatStr) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return formatDate(cal.getTime(), formatStr);
	}

	/**
	 * 获取指定周的结束日期
	 * @param year
	 * @param week
	 * @return
	 */
	public static String getWeekEndDate(int year, int week) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		return formatDate(cal.getTime(), "yyyy/MM/dd");
	}

	/**
	 * 获取指定周的结束日期
	 * @param year
	 * @param week
	 * @param formatStr
	 * @return
	 */
	public static String getWeekEndDate(int year, int week, String formatStr) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		return formatDate(cal.getTime(), formatStr);
	}
	
	/**
	 * 获取当前年的最大周数
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setMinimalDaysInFirstWeek(7);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 日期相减
	 * @param date
	 * @param date1
	 * @return
	 */
	public static int diffDateForDay(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 日期相减
	 * @param date
	 * @param date1
	 * @return
	 */
	public static int diffDateForDay(String date, String date1, String format) {
		return (int) ((getMillis(parseDate(date, format)) - getMillis(parseDate(date1, format))) / (24 * 3600 * 1000));
	}

	/**
	 * 日期相减
	 * @param date
	 * @param date1
	 * @return
	 */
	public static int diffDateForHour(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (3600 * 1000));
	}

	/**
	 * 日期相减
	 * @param date
	 * @param date1
	 * @return
	 */
	public static int diffDateForMinute(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (60 * 1000));
	}

	/**
	 * 日期相减
	 * @param date
	 * @param date1
	 * @return
	 */
	public static int diffDateForSecond(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / 1000);
	}
	
	/**
	 * 截取时间尾数，精确到秒
	 * @param date
	 * @return
	 */
	public static Date correctToSecond(Date date) {
		Long time = date.getTime();
		Long remainder = time % 1000;
		time = time - remainder;
		return new Date(time);
	}
	
	/**
	 * Date类型转换成XMLGregorianCalendar
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
		if (date == null) {
			return null;
		}
		GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
             e.printStackTrace();
        }
        return gc;
    }
 
	/**
	 * XMLGregorianCalendar类型转换成Date
	 * @param cal
	 * @return
	 */
	public static Date convertToDate(XMLGregorianCalendar cal) {
		if(cal == null){
			return null;
		}
		GregorianCalendar ca = cal.toGregorianCalendar();
		return ca.getTime();
     }
	
	/**
	 * String转换成XMLGregorianCalendar类型
	 * @param cal
	 * @return
	 */
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(String date) {
		DatatypeFactory df=null;
		 try {
			df=DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		 XMLGregorianCalendar xmlGC=df.newXMLGregorianCalendar(date);
		 
		 return xmlGC;
     }
	
	/**
	 * 时间-时区转换
	 * @param sourceDate
	 * @param formatter
	 * @param sourceTimeZone
	 * @param targetTimeZone
	 * @return
	 * @throws ParseException 
	 */
	public static String dateTransforTimeZone(String sourceDate, String formatter, TimeZone sourceTimeZone, TimeZone targetTimeZone) throws ParseException{
		// 原时间 - 原时间所在时区相对于GMT的偏移 = 原时间相对于GMT的值
		// 原时间相对于GMT的值 +上目标时区相对GMT的偏移
		if(sourceTimeZone ==null){
			sourceTimeZone = TimeZone.getTimeZone("PST+8");
		}
		if(targetTimeZone ==null){
			targetTimeZone = TimeZone.getTimeZone("GＭT+8");
		}
		// -------------------
		SimpleDateFormat sdf= new SimpleDateFormat(DateUtils.DEFAULT_DATE_FORMAT);
		Date date =sdf.parse(sourceDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		Long targetTime = date.getTime() - sourceTimeZone.getRawOffset() + targetTimeZone.getRawOffset() ;
		return DateUtils.formatDate(new Date(targetTime), formatter);
	}
	
	/**
	 * 获取yyyyMMddHHmmss
	 * @return
	 */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date);
	}
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return String.valueOf(rad.nextInt(1000));
	}
	
	/**		
	 * 产生随机的四位数
	 * @return
	 */
	public static String getFour(){
		Random rad=new Random();
		return String.valueOf(rad.nextInt(10000));
	}
	
	/**
	 * 五位随机数
	 * @return
	 */
	public static String getFive(){
		Random rad=new Random();
		return String.valueOf(rad.nextInt(100000));
	}
	
	public static void main(String[] args) {
		System.out.println(getFour());
	}
}
