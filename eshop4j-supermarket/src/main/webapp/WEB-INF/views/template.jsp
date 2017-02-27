<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
//String scheme = request.getScheme();//总是http
String port = request.getServerPort()==80?"":":" +String.valueOf(request.getServerPort());
String basePath = "//" + request.getServerName() +  port + path + "/";
%>
<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>

    </body>
</html>