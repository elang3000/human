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
<style type="text/css">
.bg {
	background-image:url(static/image/login-bg.png);
	background-repeat:no-repeat;
	background-size:100% 100%;
	height:100%;
	text-align: center;
}
.layui-form-item {
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<div class="bg">
		<div class="layadmin-user-login layadmin-user-display-show" id="smart-user-login" style="display: none;">
			<div class="layadmin-user-login-main">
				<div class="layadmin-user-login-box layadmin-user-login-header">
					<h2>${appNode.name}</h2>
				</div>
				<div class="layadmin-user-login-box layadmin-user-login-body layui-form">
					<div class="layui-form-item">
						<input type="hidden" name="certSn" value="${certSn}">
						<input type="hidden" name="expiresIn" value="${expiresIn}">
						<input type="hidden" name="accessToken" value="${accessToken}">
						<label class="layadmin-user-login-icon layui-icon layui-icon-username" for="smart-user-login-username"></label> 
						<input type="text" name="loginName" id="smart-user-login-username" lay-verify="required" placeholder="用户名" class="layui-input" 
						<c:if test="${enabledCA}">
							value="${user.loginName}"
							readonly="readonly"
						</c:if>>
					</div>
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="smart-user-login-password"></label> <input type="password" name="password" id="smart-user-login-password"
							lay-verify="required" placeholder="密码" class="layui-input">
					</div>
					<div class="layui-form-item">
						<button id="denglu" class="layui-btn layui-btn-fluid" lay-submit lay-filter="smart-user-login-submit">登 &nbsp;&nbsp;&nbsp;入</button>
					</div>
				</div>
			</div>
	
			<div class="layui-trans layadmin-user-login-footer">
				<p>
					© 2018 <a href="http://www.wondersgroup.com/">万达信息</a> 版权所有
				</p>
			</div>
		</div>
	</div>
	<script src="layui/layui.js"></script>
	<script>
		document.onkeydown=function(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
			if (e && e.keyCode==13) { 
			     document.getElementById('denglu').click();
			}
	    }; 
		var isKickout = false;
		function kickout(){  
	       var href = location.href;  
	       if(self != top){  
	    	   window.top.location.href = location.href;
	    	   return ;
	       }   
	       if (href.indexOf("kickout") > 0) {
	    	   isKickout = true;
	    	   return;
	       }
	    } 
		kickout.call(); 
	 	layui.config({
			base : 'layadmin/'
		}).extend({
			setter : 'config'
		}).use(['setter', 'form','layer'],function() {
			var $ = layui.$,form=layui.form,layer=layui.layer,setter=layui.setter;
			form.verify({
				nickname : function(e,s){
					return new RegExp("^[a-zA-Z0-9_]+$").test(e) ?  /(^\_)|(\__)|(\_+$)/.test(e) ? "用户名首尾不能出现下划线'_'": /^\d+\d+\d$/.test(e) ? "用户名不能全为数字" : void 0 : "用户名不能有特殊字符"
				},
				pass: [/^[\S]{6,12}$/,"密码必须6到12位，且不能出现空格"]
			}),
			form.render();
			if (isKickout) {
				layer.msg('您在账号已在其他地方登录成功，请重新登入。', {
					time: 0
					,shade :0.3
					,btn: ['确认']
					,yes: function(index){
						layer.close(index);
						location.href = 'login';
					}
				});
			}
			form.on('submit(smart-user-login-submit)', function(obj) {
				$.post(
					'login?appCode=' + '${appNode.code}',
					obj.field,
					function(result) {
						if (result.success) {
							layer.msg('登入成功', {
								time: 0
								,icon : 1
								,shade :0.3
								,anim: 5
							});
							location.href = 'main/index';
						} else {							
							layer.msg(result.message, {
								time: 0
								,icon : 2
								,shade :0.3
								,btn: ['确认']
								,anim: 6
								,yes: function(index){
									layer.close(index);
								}
							});
						}
					},
					'json');
			});
		});
	</script>
</body>
</html>