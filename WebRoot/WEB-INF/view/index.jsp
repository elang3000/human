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
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="shortcut icon" type="image/x-icon" href="static/image/favicon.ico"  media="screen"/>
	<link rel="stylesheet" href="layui/css/layui.css" media="all">
	<link href="fontAwesome/css/font-awesome.min.css" rel="stylesheet" />
	<link href="common/css/common.css" rel="stylesheet" />
	<link href="layadmin/style/admin.css" rel="stylesheet" />
	<style type="text/css">
		.layui-layout-admin .layui-logo {
		    height: 90px;
		    vertical-align: middle;
		    line-height: 30px;
		    font-size:25px;
		    padding:0px 0px;
		}
		.layui-layout-admin .layui-logo .smart-logo{
			height: 90px;
			width: 50px;
			display:inline;
			vertical-align: middle;
		}
		.layui-layout-admin .layui-logo .smart-title{
			height: 90px;
			width: 170px;
			display:inline;
			vertical-align: middle;
			text-align:center;
		    font-size:25px;
		}
		.layui-layout-admin .layui-logo .smart-logo img{
		    max-width: 50px;
			max-height: 50px;
			min-width: 50px;
			min-height: 50px;
		}
		.layui-side-menu .layui-nav {
		    margin-top: 90px;
		}
		.shakespan {
		    border: 0px solid black;
		    margin-left: 10px;
		    border-radius: 11px;
		    min-width: 8px;
		    line-height: 15px;
		    font-size: 13px;
		    position: absolute;
		    top: 12px;
		    background: #ff0000;
		    color: #fff;
		}
	</style>
</head>
<body class="layui-layout-body">
	<div id="smart_app">
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header">
				<ul class="layui-nav layui-layout-left">
					<li class="layui-nav-item layadmin-flexible" lay-unselect>
						<a href="javascript:;" layadmin-event="flexible" title="侧边伸缩"> 
							<i class="layui-icon layui-icon-shrink-right" id="smart_app_flexible"></i>
						</a>
					</li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect>
						<a href="http://www.changning.sh.cn" target="_blank" title="前台">
							<i class="layui-icon layui-icon-website"></i>
						</a>
					</li>
					<li class="layui-nav-item" lay-unselect>
						<a href="javascript:;" layadmin-event="refresh" title="刷新"> 
							<i class="layui-icon layui-icon-refresh-3"></i>
						</a>
					</li>
				</ul>
				<ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
					<li class="layui-nav-item" lay-unselect>
						<a lay-href="announcement/index" layadmin-event="message" lay-text="消息中心">
							<i class="layui-icon layui-icon-notice"></i>
							<c:if test="${isUnreadAnnouncement}">
								<span class="shakespan">${unreadAnnouncement}</span>
							</c:if>
						</a>
					</li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect>
            			<a href="javascript:;" layadmin-event="theme">
              				<i class="layui-icon layui-icon-theme"></i>
            			</a>
          			</li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect>
						<a href="javascript:;" layadmin-event="note">
							<i class="layui-icon layui-icon-note"></i>
						</a>
					</li>
					<li class="layui-nav-item" lay-unselect>
						<a href="javascript:;">
							<cite>
								${principal.name}
								<c:if test="${not empty organNode}">
									(${organNode.name})
								</c:if>
							</cite>
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a lay-href="security/user/info/edit?id=${principal.id}">基本资料</a>
							</dd>
							<dd>
								<a lay-href="security/user/password/reset?id=${principal.id}">修改密码</a>
							</dd>
							<hr>
							<dd layadmin-event="logout" style="text-align: center;">
								<a>退出</a>
							</dd>
						</dl>
					</li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect>
						<a href="javascript:;" layadmin-event="about">
							<i class="layui-icon layui-icon-more-vertical"></i>
						</a>
					</li>
				</ul>
			</div>
			<div class="layui-side layui-side-menu">
				<div class="layui-side-scroll">
					<div class="layui-logo">
						<div style="height: 90px;clear:both;vertical-align: middle;display: table-cell;">
							<div style="width:220px;height:90px;float:left;background-image:url(static/image/logo03.png);background-repeat:no-repeat; background-size:100% 100%;-moz-background-size:100% 100%;">
							</div>
							
						</div>
					</div>	
					<ul class="layui-nav layui-nav-tree" lay-shrink="all" id="smart-system-side-menu" lay-filter="layadmin-system-side-menu">
						<c:forEach items="${menus}" var="menu">
							<li data-name="${menu.code}" class="layui-nav-item">
								<a href="javascript:;">
									<c:if test="${empty menu.icon}">
										<i class="fa fa-angle-right" style="font-size:18px;color:white;padding-right:1em"></i>
									</c:if>
									<c:if test="${not empty menu.icon}">
										<i class="fa ${menu.icon}" style="font-size:18px;color:white;padding-right:1em"></i>
									</c:if>
									<cite>${menu.resourceName}</cite>
								</a>
								<c:if test="${!menu.isLeaf}">
									<dl class="layui-nav-child">
										<c:forEach items="${menu.children}" var="menu1">
											<dd data-name="${menu1.code}">
												<c:if test="${!menu1.isLeaf}">
													<a href="javascript:;">
														<c:if test="${empty menu1.icon}">
															<i class="fa fa-angle-right" style="font-size:18px;color:white;padding-right:1em"></i>
														</c:if>
														<c:if test="${not empty menu1.icon}">
															<i class="fa ${menu1.icon}" style="font-size:18px;color:white;padding-right:1em"></i>
														</c:if>
														${menu1.resourceName}
													</a>
													<dl class="layui-nav-child">
														<c:forEach items="${menu1.children}" var="menu2">
															<dd data-name="${menu2.code}">
																<c:if test="${!menu2.isLeaf}">
																	<a href="javascript:;">
																		<c:if test="${empty menu2.icon}">
																			<i class="fa fa-angle-right" style="font-size:18px;color:white;padding-right:1em"></i>
																		</c:if>
																		<c:if test="${not empty menu2.icon}">
																			<i class="fa ${menu2.icon}" style="font-size:18px;color:white;padding-right:1em"></i>
																		</c:if>
																		${menu2.resourceName}
																	</a>
																	<dl class="layui-nav-child">
																		<c:forEach items="${menu2.children}" var="menu3">
																			<dd data-name="${menu3.code}">
																				<a lay-href="${menu3.linkPath}">
																					<c:if test="${empty menu3.icon}">
																						<i class="fa fa-angle-right" style="font-size:18px;color:white;padding-right:1em"></i>
																					</c:if>
																					<c:if test="${not empty menu3.icon}">
																						<i class="fa ${menu3.icon}" style="font-size:18px;color:white;padding-right:1em"></i>
																					</c:if>
																					${menu3.resourceName}
																				</a>
																			</dd>
																		</c:forEach>
																	</dl>
																</c:if>
																<c:if test="${menu2.isLeaf}">
																	<a lay-href="${menu2.linkPath}">
																		<c:if test="${empty menu2.icon}">
																			<i class="fa fa-angle-right" style="font-size:18px;color:white;padding-right:1em"></i>
																		</c:if>
																		<c:if test="${not empty menu2.icon}">
																			<i class="fa ${menu2.icon}" style="font-size:18px;color:white;padding-right:1em"></i>
																		</c:if>
																		${menu2.resourceName}
																	</a>
																</c:if>
															</dd>
														</c:forEach>
													</dl>
												</c:if>
												<c:if test="${menu1.isLeaf}">
													<a lay-href="${menu1.linkPath}">
														<c:if test="${empty menu1.icon}">
															<i class="fa fa-angle-right" style="font-size:18px;color:white;padding-right:1em"></i>
														</c:if>
														<c:if test="${not empty menu1.icon}">
															<i class="fa ${menu1.icon}" style="font-size:18px;color:white;padding-right:1em"></i>
														</c:if>
														${menu1.resourceName}
													</a>
												</c:if>
											</dd>
										</c:forEach>
									</dl>
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="layadmin-pagetabs" id="smart_app_tabs">
				<div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
				<div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
				<div class="layui-icon layadmin-tabs-control layui-icon-down">
					<ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
						<li class="layui-nav-item" lay-unselect><a href="javascript:;"></a>
							<dl class="layui-nav-child layui-anim-fadein">
								<dd layadmin-event="closeThisTabs">
									<a href="javascript:;">关闭当前标签页</a>
								</dd>
								<dd layadmin-event="closeOtherTabs">
									<a href="javascript:;">关闭其它标签页</a>
								</dd>
								<dd layadmin-event="closeAllTabs">
									<a href="javascript:;">关闭全部标签页</a>
								</dd>
							</dl>
						</li>
					</ul>
				</div>
				<div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
					<ul class="layui-tab-title" id="smart_app_tabsheader">
						<li lay-id="main/list" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
					</ul>
				</div>
			</div>

			<div class="layui-body" id="smart_app_body">
				<div class="layadmin-tabsbody-item layui-show">
					<iframe src="main/content" frameborder="0" class="layadmin-iframe"></iframe>
				</div>
			</div>

			<div class="layadmin-body-shade" layadmin-event="shade"></div>
		</div>
	</div>
	<script src="layui/layui.js"></script>
	<script>
		layui.config({
			base: 'layadmin/'
		}).extend({
			index: 'lib/index'
		}).use('index');
	</script>
</body>
</html>
