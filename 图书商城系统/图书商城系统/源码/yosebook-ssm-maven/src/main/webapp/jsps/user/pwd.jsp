<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<title>修改密码</title>、
<link rel="stylesheet" type="text/css"
    href="<c:url value='/css/css.css'/>">
<link rel="stylesheet" type="text/css"
    href="<c:url value='/jsps/css/user/pwd.css'/>">
<script type="text/javascript"
    src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
    <%--    删除！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！--%>
<%--<script src="<c:url value='/js/common.js'/>"></script>--%>
<script src="<c:url value='/jsps/js/user/pwd.js'/>"></script>
</head>

<body>
<%--    删除！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！--%>
<%--    <header>--%>
<%--     <img src="<c:url value='/images/ysmall_logo.png'/>">--%>
<%--     <img src="<c:url value='/images/welcome_mdfPwd.png'/>" class="welcome_mdfPwd">--%>
<%--    </header>--%>

    <section>
        <div class="div">
            <form action="<c:url value='/user/updatePassword.do'/>" method="post"
                target="_top">
                <table>
                    <tr>
                        <td class="textTd">&nbsp;</td>
                        <td class="inputTd"><label class="error"></label></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="right" class="textTd">原密码：</td>
                        <td class="inputTd"><input class="input" type="password"
                            name="loginpass" id="loginpass" value="${user.loginpass }" /></td>
                        <td><label id="loginpassError" class="error">${msg }</label></td>
                    </tr>
                    <tr>
                        <td align="right" class="textTd">新密码：</td>
                        <td class="inputTd"><input class="input" type="password"
                            name="newpass" id="newpass" value="${user.newpass }" /></td>
                        <td><label id="newpassError" class="error"></label></td>
                    </tr>
                    <tr>
                        <td align="right" class="textTd">确认密码：</td>
                        <td class="inputTd"><input class="input" type="password"
                            name="reloginpass" id="reloginpass" value="${user.reloginpass }" /></td>
                        <td><label id="reloginpassError" class="error"></label></td>
                    </tr>
                    <tr>
                        <td align="right"></td>
                        <td><input id="submit" type="submit"
                            value="确认修改" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </section>
</body>
</html>
