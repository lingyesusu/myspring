<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>注册</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
		<link rel="stylesheet" href="${contextPath}/resources/css/login/reset.css"/>
        <link rel="stylesheet" href="${contextPath}/resources/css/login/supersized.css"/>
        <link rel="stylesheet" href="${contextPath}/resources/css/login/style.css"/>
		<style>
			#vcode >img{cursor:pointer;margin-bottom: -15px;border-radius:5px;}
		</style>
    </head>

    <body id="body">
        <div class="page-container" style="margin: 100px auto 0px;">
            <h1>注册</h1>
            <form id="_form" action="" method="post">
                <input type="text" name="nickname" id="nickname" class="username" placeholder="用户名">
                <input type="text" name="email"  id="email" placeholder="邮箱">
                <input type="password" name="pswd" id="password" class="password" placeholder="密码">
                <input type="password" id="re_password"  placeholder="再一次填密码">
                <div style="text-align: left; margin-left: 10px;" id="vcode">
	                <input type="text" name="vcode"   placeholder="验证码" style="width: 110px; margin-left: -8px; margin-right: 8px;">
                	<img src="${contextPath}/open/getGifCode.shtml" />
                </div>
                <button type="button" class="register">注册</button>
                <button type="button" id="login" >登录</button>
                <div class="error"><span>+</span></div>
            </form>
        </div>

        <!-- Javascript -->
        <script  src="${contextPath}/resources/js/common/jquery/jquery1.8.3.min.js"></script>
        <script  src="${contextPath}/resources/js/common/MD5.js"></script>
        <script  src="${contextPath}/resources/js/common/supersized.3.2.7.min.js"></script>
        <script  src="${contextPath}/resources/js/common/supersized-init.js"></script>
		<script  src="${contextPath}/resources/js/common/layer/layer.js"></script>
    </body>
<script type="text/javascript">
jQuery(document).ready(function() {
	//验证码
	$("#vcode").on("click",'img',function(){
		/**动态验证码，改变地址，多次在火狐浏览器下，不会变化的BUG，故这样解决*/
		
		$(this).attr('src','${contextPath}/open/getGifCode.shtml?' + Math.floor(Math.random() * 100));
	});
	//注册
	$("#login").click(function(){
		window.location.href="${contextPath}/login";
	});
});
</script>
</html>