<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</head>
<body>
<script src="layui/layui.js"></script>
layui.use(['form','layer'],function() {
	var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
});
</body>