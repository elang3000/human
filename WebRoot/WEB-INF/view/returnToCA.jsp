<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>长宁区人事管理信息系统</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="shortcut icon" type="image/x-icon" href="static/image/favicon.ico"  media="screen"/>
<link rel="stylesheet" href="layui/css/layui.css" media="all"/>
<link href="layadmin/style/admin.css" rel="stylesheet" />
<link href="layadmin/style/login.css" rel="stylesheet" media="all"/>
</head>
<body>

	<script>
	   window.onload=function(){
			//alert('${returnUrl}');
			window.location.href = '${returnUrl}'; 
		}
	</script>
</body>
</html>