<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>top</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body {
	background: #a2d7dd;
	margin: 0px;
	color: black;
	font-family: "microsoft yahei";
    opacity: 50%;
}
.logo{
    height:75px;
    margin-left:12px;
    margin-bottom: 10px;
    font-size: 50px;
    font-style: italic;
    text-shadow: 3px 3px #a79f9f;
    color: #2e4276;
}
a {
	text-transform: none;
	text-decoration: none;
	color: black;
	font-weight: normal;
}

a:hover {
	color:#fe0;
}
</style>
</head>

<body>
    <div>
        <div class="logo">YW书城</div>
        <div style="font-size: 10pt; line-height: 10px;">
    <c:choose>
        <c:when test="${empty sessionScope.sessionUser }">
            <a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;会员登录</a>
            |&nbsp; <a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册会员</a>
        </c:when>
        <c:otherwise>
        &nbsp;&nbsp;&nbsp;&nbsp;
             YW会员：${sessionUser.loginname }&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value='/cartItem/myCart.do'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value='/order/myOrders.do'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value='/jsps/user/pwd.jsp'/>" target="body">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value='/user/quit.do'/>" target="_parent">退出</a>
        </c:otherwise>
    </c:choose>
        </div>
    </div>
</body>
</html>
