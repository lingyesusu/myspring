package com.tools;

/**
 * <p>描述：oms_server工程中的Java类[GlobalConstants]</p>
 * <p>创建日期：2016-2-25上午10:31:51</p>
 * <p>版权：Copyright (c) 2012</p>
 * <p>公司：东莞市邮政局</p>
 *
 * 类描述：系统常量设置
 *
 * @author 王维
 * @version 1.0
 **/
public class GlobalConstants {
	
	// 默认头选项
	public static final String HEAD_DATA_FILTER_NAME = "全部";// 头选项名
	public static final String HEAD_DATA_FILTER_VAL = "";// 头选项值
	
	// 默认头选项
	public static final String HEAD_OPTION_NAME = "--选择--";// 头选项名
	public static final String HEAD_OPTION_VAL = "";// 头选项值
	
	// 默认头选项
	public static final String HEAD_LINK_TITLE = "全部";		// 头列表名
	public static final String HEAD_LINK_HREF = "javaScript: void(0)";			// 头列表a描点超链接
	public static final String HEAD_LINK_IDPREFIX = "li";	// 列表ID默认前缀
	
	//--权限类型--
	public static final String PERM_METHOD = "METHOD";// 权限类型--方法
	
	public static final String PERM_TAP = "TAP";// 权限类型--TAP
	
	public static final String PERM_MENU = "MENU";// 权限类型--菜单
	
	public static final Long TOP_TREE_ID = -1L;//顶级树ID
	
	public static final String SESSION_USER = "OmsUser";//session中的ueser对象
	
	public static final String SUPER_USER = "admin";//系统管理员(历史为:admin)
	
	public static final String COMMON_PASSWORD = "12345678";//重置密码
	
	public static final Long SUPER_USER_ID = -100L;//系统管理员id
	
	public static final Long SUPER_DEPT_ID = -100L;//系统管理员机构ID
	
	public static final Integer THUMBS_WIDTH = 200; //缩略图宽
	
	public static final Integer THUMBS_HEIGHT = 150;//缩略图高
	
	public static final Integer THUMBS_FLAG =1;//缩略图文件末尾名标志
	
	public static final String DATA_TEMPLATE = "DATA_TEMPLATE";// 数据模板存储目录	
}