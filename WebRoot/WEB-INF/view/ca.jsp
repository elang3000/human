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
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
</head>
<body class="no-skin">
	<div class="main-container ace-save-state" id="main-container">
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="error-container">
								<div class="well">
									<h1 class="grey lighter smaller">
										<span class="blue bigger-125"> <i class="ace-icon fa fa-random"></i>505
										</span>
										数字证书授权问题
									</h1>
									<hr />
									<h3 class="lighter smaller">
										<i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>${message}
									</h3>
									<div class="space"></div>
									<div>
										<h4 class="lighter smaller">您可以通过如下方式解决问题:</h4>

										<ul class="list-unstyled spaced inline bigger-110 margin-15">
											<li><i class="ace-icon fa fa-hand-o-right blue"></i>联系上海市数字证书管理中心，获取证书的数字证书。</li>
											<li><i class="ace-icon fa fa-hand-o-right blue"></i>联系长宁区人事管理信息通管理员，获取相关证书的系统权限。</li>
											<li><i class="ace-icon fa fa-hand-o-right blue"></i>联系长宁区人事管理信息通管理员，反馈相关的系统问题。</li>
										</ul>
									</div>
									<hr />
									<div class="space"></div>
									<div class="center">
										<a href="javascript:location.reload()" class="btn btn-grey"> <i class="ace-icon fa fa-arrow-left"></i>刷新
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
