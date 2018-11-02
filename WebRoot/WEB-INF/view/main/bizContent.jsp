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
<title>长宁区人事管理信息系统</title>
<base href="<%=basePath%>" />
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<link href="fontAwesome/css/font-awesome.min.css" rel="stylesheet" />
<link href="common/css/common.css" rel="stylesheet" />
<link href="layadmin/style/admin.css" rel="stylesheet" />
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<c:if test="${idLeader}">
			<div class="layui-col-md8">
			</c:if>
			<c:if test="${!idLeader}">
			<div class="layui-col-md12">
			</c:if>
				<div class="layui-row layui-col-space15">
					<div class="layui-col-md6">
						<div class="layui-card">
							<div class="layui-card-header">快捷方式</div>
							<div class="layui-card-body">

								<div class="layui-carousel layadmin-carousel layadmin-shortcut">
									<div carousel-item>
										<c:forEach var="menu" items="${shortcut}" varStatus="status">
											<c:if test="${status.index mod 8 == 0}">
												<ul class="layui-row layui-col-space10">
											</c:if>
											<li class="layui-col-xs3" style="padding-top:20px">
												<a lay-href="${menu.linkPath}">
													<i class="fa ${menu.icon}" style="font-size:40px;color:#86aae0;" title="${menu.resourceName}"></i> 
													<cite>${menu.resourceName}</cite>
												</a>
											</li>
											<c:if test="${(status.index +1) mod 8 == 0 || fn:length(shortcut) == status.index +1}">
												</ul>
											</c:if>
										</c:forEach>										
									</div>
								</div>

							</div>
						</div>
					</div>
					<div class="layui-col-md6">
						<div class="layui-card">
							<div class="layui-card-header">待办事项</div>
							<div class="layui-card-body">

								<div class="layui-carousel layadmin-carousel layadmin-backlog">
									<div carousel-item>
										<ul class="layui-row layui-col-space10">
											<li class="layui-col-xs6"><a
												lay-href="workflow/doing/index?category=G" id="servantDoing"
												class="layadmin-backlog-body">
													<h3>公务员事项待审</h3>
													<p>
														<cite>0</cite>
													</p>
											</a></li>
											<li class="layui-col-xs6"><a
												lay-href="workflow/doing/indexPublic" id="bizDoing"
												class="layadmin-backlog-body">
													<h3>事业单位事项待审</h3>
													<p>
														<cite>0</cite>
													</p>
											</a></li>
											<li class="layui-col-xs6"><a
												lay-href="workflow/doing/index?category=O" id="organDoing"
												class="layadmin-backlog-body">
													<h3>组织信息待审</h3>
													<p>
														<cite>0</cite>
													</p>
											</a></li>
											<li class="layui-col-xs6"><a href="workflow/doing/index?category=P" id="planDoing"
												class="layadmin-backlog-body">
													<h3>编制信息待审</h3>
													<p>
														<cite>0</cite>
													</p>
											</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="layui-col-md12">
						<div class="layui-card">
							<div class="layui-card-header">组织机构信息</div>
							<div class="layui-card-body">
								<div class="layui-carousel layadmin-carousel layadmin-dataview"
									data-anim="fade" lay-filter="smart-index-dataview">
									<div carousel-item id="smart-index-dataview">
										<div>
											<i class="layui-icon layui-icon-loading1 layadmin-loading"></i>
										</div>
										<div></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<c:if test="${idLeader}">
				<div class="layui-col-md4">
					<div class="layui-card">
						<div class="layui-card-header">当月事项完成情况</div>
						<div class="layui-card-body layadmin-takerates">

						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	<script src="layui/layui.js"></script>
	<script>
		layui.config({
			base: 'layadmin/'
		}).extend({
			index: 'lib/index'
		}).use(['index','bizConsole']);
	</script>
</body>
</html>
