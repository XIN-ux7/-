<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">

<link rel="stylesheet" type="text/css"
    href="<c:url value='/css/css.css'/>">
<link rel="stylesheet" type="text/css"
    href="<c:url value='/jsps/css/user/regist.css'/>">
<script type="text/javascript"
    src="<c:url value='/jquery/jquery-2.1.1.js'/>"></script>
<%--    删除！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！11--%>
<%--<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/jsps/js/user/regist.js'/>"></script>
</head>

<body>

<div class="background-blur"></div>
<div class="container">
    <h1>欢迎注册</h1>
    <form action="<c:url value='/user/regist.do'/>" method="post" id="registForm">
        <table>
            <tr>
<%--                <td class="tdLabel">用户名：</td>--%>
                <td class="tdInput">
                    <input type="text" name="loginname" placeholder="用户名" id="loginname" class="input" value="${form.loginname }" />
                </td>
            </tr>
            <tr>
                <td class="tdError" style="height: 20px">
                    <label class="labelError" id="loginnameError">${errors.loginname }</label>
                </td>
            </tr>
            <tr>
<%--                <td class="tdLabel">登录密码：</td>--%>
                <td class="tdInput">
                    <input type="password" name="loginpass" placeholder="登录密码" id="loginpass" class="input" value="${form.loginpass }" /></td>
            </tr>
            <tr>
                <td class="tdError" style="height: 20px">
                    <label class="labelError" id="loginpassError">${errors.loginpass }</label>
                </td>
            </tr>

            <tr>
<%--                <td class="tdLabel">确认密码：</td>--%>
                <td class="tdInput">
                    <input type="password" name="reloginpass" placeholder="确认密码" id="reloginpass" class="input" value="${form.reloginpass }" /></td>
            </tr>
            <tr>
                <td class="tdError" style="height: 20px">
                    <label class="labelError" id="reloginpassError">${errors.reloginpass }</label>
                </td>
            </tr>

            <tr>
<%--                <td class="tdLabel">Email：</td>--%>
                <td class="tdInput">
                    <input type="text" name="email" placeholder="Email" id="email" class="input" value="${form.email }" /></td>
            </tr>
            <tr>
                <td class="tdError" style="height: 20px">
                    <label class="labelError" id="emailError">${errors.email }</label>
                </td>
            </tr>

            <tr>
                <td class="tdInput"><input type="text"
                                           name="verifyCode" id="verifyCode"
                                           placeholder="请输入验证码"
                                           class="input" value="${form.verifyCode }" />
                    <span class="verifyCodeImg">
                    <img id="vCode" width="90" title="点击更换"
                         src="/yosebook-ssm-maven/verifyCode"
                         onclick="this.setAttribute('src','/yosebook-ssm-maven/verifyCode?'+Math.random());"></span>
                </td>

            </tr>
            <tr>

                <td class="tdError"><label
                        class="labelError" id="verifyCodeError">${errors.verifyCode }</label>
                </td>
            </tr>

            <tr>
                <td>
                    <input type="submit" id="submitBtn" value="注册" class="btnRegist">
                </td>
                <td>
            </tr>
        </table>
    </form>
    <p>已有账号？
        <a class="alreadyHave" href="<c:url value='/jsps/user/login.jsp'/>">登录</a></td>
    </p>
</div>
</body>
</html>
