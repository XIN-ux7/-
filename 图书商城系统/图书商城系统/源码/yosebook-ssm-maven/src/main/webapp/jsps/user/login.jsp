<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
  <title> 登录 </title>
  <meta charset="utf-8"/>
  	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/login.css'/>">
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>

<%--	 删掉common.js  删除！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！--%>
<%--	<script src="<c:url value='/js/common.js'/>"></script>--%>

    <script src="<c:url value='/jsps/js/user/login.js'/>"></script>
    <script type="text/javascript">
    	$(function(){
			//从cookie中获取loginname
    		var loginname = window.decodeURI("${cookie.loginname.value}");
			//从服务器端获取loginname
    		if("${requestScope.user.loginname}"){
    			loginname="${requestScope.user.loginname}";
    		}
			//更新表单中的loginname值
    		$("#loginname").val(loginname);
    	});
    </script>
 </head>
 <body>

<!-- 页面内容 -->
<div class="background-blur"></div>
<div class="container">
	<h1>欢迎登录</h1>
	<!-- 登录表单 -->
	<form target="_top" action="<c:url value='/user/login.do'/>" method="post" id="loginForm">
		<table>
			<tr>
				<td>
					<input type="text" class="txtUsername input" placeholder="邮箱/用户名/手机号" name="loginname" id="loginname" value="${user.loginname }">
				</td>
			</tr>
			<tr style="height: 20px">
				<td><label class="error" id="loginnameError">${msg } ${errors.loginname}</label></td>
			</tr>
			<tr>
				<td>
					<input type="password" class="txtUserpwd input" placeholder="密码" name="loginpass" id="loginpass" value="${user.loginpass }">
				</td>
			</tr>
			<tr style="height: 20px">
				<td><label class="error" id="loginpassError">${errors.loginpass}</label></td>
			</tr>
					<tr>
						<td>
							<input type="submit" id="btnLogin" value="登    录" class="login-btn">
						</td>
					</tr>
		</table>
	</form>
	<p>没有账号？
		<a href="<c:url value='/jsps/user/regist.jsp'/>">
			<input type="button" id="btnRegist" value="免费注册&gt;&gt;" class="register-btn">
		</a>
	</p>

</div>

 </body>
</html>
