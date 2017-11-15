<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common/taglibs.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <a href="${contextPath}/logout">退出</a><br>
    Hello,${user}<br>
    <ul>
	    <shiro:hasRole name="user">
    		<li>userRole</li>
		</shiro:hasRole>
		<shiro:hasRole name="admin">
    		<li>adminRole</li>
		</shiro:hasRole>
		<shiro:hasRole name="throw">
    		<li>throwRole</li>
		</shiro:hasRole>
    	<shiro:hasPermission name="throwpermission">
	    	<li>throwPermission</li>
		</shiro:hasPermission>
    	<shiro:hasPermission name="userpermission">
	    	<li>userPermission</li>
		</shiro:hasPermission>
    	<shiro:hasPermission name="adminpermission">
	    	<li>adminPermission</li>
		</shiro:hasPermission>
    </ul>
  </body>
</html>
