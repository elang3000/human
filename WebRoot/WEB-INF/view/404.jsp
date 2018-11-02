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
<link rel="stylesheet" href="static/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="static/assets/font-awesome/4.5.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="static/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
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
										<span class="blue bigger-125"> <i class="ace-icon fa fa-random"></i> 500
										</span> 404
									</h1>

									<hr />
									<h3 class="lighter smaller">
										But we are working <i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i> on it!
									</h3>

									<div class="space"></div>

									<div>
										<h4 class="lighter smaller">Meanwhile, try one of the following:</h4>

										<ul class="list-unstyled spaced inline bigger-110 margin-15">
											<li><i class="ace-icon fa fa-hand-o-right blue"></i> Read the faq</li>

											<li><i class="ace-icon fa fa-hand-o-right blue"></i> Give us more info on how this specific error occurred!</li>
										</ul>
									</div>

									<hr />
									<div class="space"></div>

									<div class="center">
										<a href="javascript:history.back()" class="btn btn-grey"> <i class="ace-icon fa fa-arrow-left"></i> Go Back
										</a> <a href="#" class="btn btn-primary"> <i class="ace-icon fa fa-tachometer"></i> Dashboard
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
