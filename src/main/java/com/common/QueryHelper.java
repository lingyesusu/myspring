package com.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 单表通用查询帮助类
 * @author Caoji
 */
public class QueryHelper {
	
	private String fromClause ="";
	private String whereClause ="";
	private String orderByClause ="";
	private List<Object> parameters;
	
	public static String ORDER_BY_DESC ="DESC";
	public static String ORDER_BY_ASC ="ASC";
	/**
	 *	构造函数构造from子句 ,from只执行一次
	 * @param clazz 实体类
	 * @param alias 实体类对应的别名
	 */
	@SuppressWarnings("rawtypes")
	public QueryHelper(Class clazz,String alias) {
		fromClause = "FROM " + clazz.getSimpleName()+" "+alias;
	}
	/**
	 * 构造where子句
	 * @param condition 查询条件语句；例如i.title like ?
	 * @param params 查询条件中？的参数，例如 %标题%
	 */
	public void addCondition(String condition,Object... params){
		if(whereClause.length()>1){   
			//非第一次
			whereClause += " AND " + condition;
		}else{
			whereClause += " WHERE " + condition;
		}
		if(parameters == null){
			parameters = new ArrayList<Object>();
		}
		if(params !=null){
			for(Object param:params){
				parameters.add(param);
			}
		}
	}
	/**
	 * 构造Order by 语句
	 * @param property 根据该参数进行排序
	 * @param order    采用什么顺序排序
	 */
	public void addOrderByProperty(String property,String order){
		if(orderByClause.length()>1){
			//非第一次
			orderByClause += "," + property + " " + order;
		}else{
			orderByClause = " ORDER BY " + property +" " + order;
		}
	}
	public String getQuerySizeHql(){
		return "SELECT COUNT(*)" +fromClause+whereClause;
	}
	//查询hql语句
	public String getQueryListHql(){
		return fromClause+whereClause+orderByClause;
	}
	//hql语句中？对应的查询条件集合
	public List<Object> getPrarameters(){
		return parameters;
	}
}
