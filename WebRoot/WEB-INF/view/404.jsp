<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>长宁区人事管理信息系统</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="/human/static/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="/human/static/assets/font-awesome/4.5.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="/human/static/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
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
											<span class="blue bigger-125"> <i class="ace-icon fa fa-random"></i>${status}
											</span>
										</h1>
										<hr/>
										<h3 class="lighter smaller">
											${message}
										</h3>
										<hr/>
										<h4 class="lighter smaller">
											我们正在努力修复<i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i>此问题！
										</h4>
										<div class="space"></div>
										<div>
											<h4 class="lighter smaller">同时，可以尝试如下操作：</h4>
											<ul class="list-unstyled spaced inline bigger-110 margin-15">
												<li><i class="ace-icon fa fa-hand-o-right blue"></i>仔细阅读FAQ</li>
												<li><i class="ace-icon fa fa-hand-o-right blue"></i>提供更多关于错误发生时的系统信息！ </li>
											</ul>
										</div>
										<hr />
										<div class="space"></div>
										<div class="center">
											<a href="javascript:history.back()" class="btn btn-warning"> <i class="ace-icon fa fa-arrow-left"></i>返回
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
