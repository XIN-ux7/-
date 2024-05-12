<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<title>高级查询</title>

<style type="text/css">
table {
	color: #404040;
	font-size: 12pt;
    font-family: "microsoft yahei";
}
input[type="text"]{
    height:30px;
    border:1px solid #aaa;
    border-radius: 5px;
    padding:1px 10px;
    margin-top: 3px;
    margin-bottom: 3px;
}
input[type="submit"],input[type="reset"]{
    margin-top:5px;
    width:89px;
    height:30px;
    border:1px solid #a2d7dd;
    border-radius: 5px;
    background: #a2d7dd;
    font-family: "microsoft yahei";
    font-size:15px;
    color:white;
}
input[type="submit"]:hover,input[type="reset"]:hover{
    background: #bce2e8;
    cursor: pointer;
}
</style>
</head>

<body>
    <form action="<c:url value='/book/findByConbination.do'/>" method="get">
        <table align="center">
            <tr>
                <td>书名：</td>
                <td><input type="text" name="bname" /></td>
            </tr>
            <tr>
                <td>作者：</td>
                <td><input type="text" name="author" /></td>
            </tr>
            <tr>
                <td>出版社：</td>
                <td><input type="text" name="press" /></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>
                    <input type="submit" value="搜　索" />
                    &nbsp;
                    <input type="reset" value="重新填写" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
