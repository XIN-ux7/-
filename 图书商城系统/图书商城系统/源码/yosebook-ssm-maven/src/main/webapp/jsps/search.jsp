<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>按书名查询</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
    body {
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    form {
        text-align: center;
    }

    input {
        width: 300px;
        height: 30px;
        border-style: solid;
        border-color: #a2d7dd;
        border-radius: 3px;
        margin-top: 10px;
    }

    a {
        text-transform: none;
        text-decoration: none;
        color: #a2d7dd;
    }

    a:hover {
        color: #8AD700;
    }

</style>
</head>

<body>
    <form action="<c:url value='/book/findByBname.do'/>" method="get"
        target="body" id="form1">
        <input type="text" name="bname" />
        <span>
            <a href="javascript:document.getElementById('form1').submit();">
                搜  索
            </a>
            <a href="<c:url value='/jsps/gj.jsp'/>" style="font-size: 11pt;font-family: 'microsoft yahei';"
            target="body">高级搜索</a>
        </span>
    </form>
</body>
</html>
