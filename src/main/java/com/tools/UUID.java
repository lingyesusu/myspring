package com.tools;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Hibernate UUID生成算法
 */
public class UUID {

	/**
	 * 生成16位唯一数字
	 * 
	 * @return
	 */
	public String generate16() {
		String timeZoneId = "Asia/Shanghai";
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
		int year = cal.get(Calendar.YEAR);
		int num = year + cal.get(Calendar.DAY_OF_YEAR);
		long current = cal.getTimeInMillis();
		cal.set(year - 1, 0, 1, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long source = cal.getTimeInMillis();
		int val = (int) (Math.random() * (10 - 1) + 1);
		return num + String.valueOf(current - source) + val;
	}

	/**
	 * 生成Hibernate格式的UUID
	 * 
	 * @return
	 */
	public String generate() {
		return new StringBuffer(36).append(format(getIP())).append(sep).append(format(getJVM())).append(sep)
				.append(format(getHiTime())).append(sep).append(format(getLoTime())).append(sep)
				.append(format(getCount())).toString();
	}

	private static final int IP;

	private static int IptoInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}

	static {
		int ipadd;
		try {
			ipadd = IptoInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
	private static short counter = (short) 0;
	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	/**
	 * Unique across JVMs on this machine (unless they load this class in the
	 * same quater second - very unlikely)
	 */
	protected int getJVM() {
		return JVM;
	}

	/**
	 * Unique in a millisecond for this JVM instance (unless there are >
	 * Short.MAX_VALUE instances created in a millisecond)
	 */
	protected short getCount() {
		synchronized (UUID.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	/**
	 * Unique in a local network
	 */
	protected int getIP() {
		return IP;
	}

	/**
	 * Unique down to millisecond
	 */
	protected short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	private final static String sep = "";

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

}
