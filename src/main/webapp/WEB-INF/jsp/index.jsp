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
    Hello,${user}<br>
    <ul>
	    <shiro:hasRole name="user">
    		<li>user</li>
		</shiro:hasRole>
		<shiro:hasRole name="admin">
    		<li>admin</li>
		</shiro:hasRole>
		<shiro:hasRole name="by">
    		<li>by</li>
		</shiro:hasRole>
    	<shiro:hasPermission name="indexpermission">
	    	<li>indexpermission</li>
		</shiro:hasPermission>
    	<shiro:hasPermission name="userpermission">
	    	<li>userpermission</li>
		</shiro:hasPermission>
    	<shiro:hasPermission name="adminpermission">
	    	<li>adminpermission</li>
		</shiro:hasPermission>
    </ul>
  </body>
</html>
