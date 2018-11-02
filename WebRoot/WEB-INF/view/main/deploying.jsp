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
<base href="http://localhost:80/human/"></base>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<link href="fontAwesome/css/font-awesome.min.css" rel="stylesheet" />
<link rel="stylesheet" href="layui/css/ace.min.css" media="all">
<link rel="stylesheet" href="layui/css/bootstrap.min.css" media="all">
<link href="common/css/common.css" rel="stylesheet" />
<style>
.formLabel {
	width: 120px;
	text-align: right;
	/*         padding-top: 13px !important; */
}

.formInfo {
	text-align: center;
	width: 150px;
	padding: 9px 0px;
}

.redFont {
	color: red;
}

img {
	width: 200px;
	height: auto;
	max-width: 200px;
	max-height: 100%;
	min-height: 250px;
	min-width: 200px;
}

.tddiv {
	width: 100%;
	height: 100%;
	margin: 0 auto;
	padding: 0 pt;
	display: table-cell;
	vertical-align: middle;
	text-align: center;
	min-height: 250px;
	min-width: 200px;
}

.divtable {
	position: absolute;
	left: 0px;
	right: 0;
	top: 0;
	bottom: 0;
	z-index: 998;
	width: auto;
	overflow: hidden;
	overflow-y: auto;
	box-sizing: border-box
}
</style>
</head>
<body class="layui-layout-body">
	<div class="divtable">
		<div class="layui-fluid">
			<div class="layui-card">
				<div class="layui-card-body">
					<div class="row">
						<div class="col-xs-12">
							<div class="error-container">
								<div class="well">
									<h1 class="grey lighter smaller">
										<span class="blue bigger-125"> <i class="ace-icon fa fa-random"></i> 500
										</span> 此功能正在开发。。。
									</h1>

									<hr />
									<h3 class="lighter smaller">
										正在努力开发 <i class="ace-icon fa fa-wrench icon-animated-wrench bigger-125"></i> 力求尽快完成
									</h3>

									<div class="space"></div>

									<div>
										<h4 class="lighter smaller">对于此功能有任何意见或建议，请联系如下部门:</h4>

										<ul class="list-unstyled spaced inline bigger-110 margin-15">
											<li><i class="ace-icon fa fa-hand-o-right blue"></i> 联系区人事主管部门。</li>

											<li><i class="ace-icon fa fa-hand-o-right blue"></i> 联系万达信息开发团队。</li>
										</ul>
									</div>

									<hr />
									<div class="space"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="layui/layui.js" charset="UTF-8"></script>
		<script src="common/common.js" charset="UTF-8"></script>
		<!--[if lt IE 9]>
	<script src="common/html5.min.js"></script>
	<script src="common/respond.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			layui
					.extend({
						smart : 'layui/modules/common/smart'
					})
					.use(
							['smart', 'form', 'layer', 'element'],
							function() {
								var $ = layui.jquery, smart = layui.smart, layer = layui.layer, form = layui.form, layer = layui.layer, element = layui.element;
								smart = smart();

							});
		</script>
	</div>
</body>
</html>