package com.tools.number;

import java.math.BigDecimal;

/**
 * <p>描述 :【OSP】工程中的Java类【Arith.java】</p>

 * <p>功能概述 :  常用算数运算      </p>
 * @version V1.0
 */
public class Arith {

	private static final int DEF_DIV_SCALE = 2;

	/**
	 * 相加
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 相减
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 相乘
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 除法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 除法
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * round
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static void main(String[]args){
		//double st=Arith.div(1.2, 2.36,3);
		 
		//字符串转为浮点数，结果保留4位
		String str="2.125819191";
		float f=Float.parseFloat(str);
		  
		BigDecimal b = new BigDecimal(Float.toString(f));
		BigDecimal b2 = new BigDecimal(Float.toString(100));
		//乘以100
		BigDecimal c=b.multiply(b2);
		 
		//取4位精度
		BigDecimal d=c.setScale(4,BigDecimal.ROUND_HALF_UP);
		 
	    System.out.println("  safsdf  "+d.toString()+"-----");

	}
}