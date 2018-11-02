<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>长宁区人事管理信息系统（后台）-统一用户管理</title>
<base href="<%=basePath%>"></base>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<link href="fontAwesome/css/font-awesome.min.css" rel="stylesheet" />
<link href="common/css/common.css" rel="stylesheet" />
<style>
.formLabel {
	width: 30%;
	text-align: right;
}
.formInfo {
	text-align: center;
	width: 50%;
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
				<div class="layui-card-header">
					<div style="margin-top: 5px;" class="layui-row">
						<i class="layui-icon"> &#xe715; </i> 
						<span class="layui-breadcrumb" lay-separator=">"> 
							<a> 您现在的所在位置 </a> 
							<a> 统一用户管理 </a> 
							<cite> <a> 用户账号管理 </a> </cite>
						</span>
					</div>
				</div>
				<div class="layui-card-body">
					<div style="margin-top: 5px;" class="layui-row">

						<fieldset class="layui-elem-field" style="border-color: rgb(30, 159, 255); margin-top: 1em; padding-left: 2em; padding-right: 2em;">
							<legend>查询条件</legend>
							<form class="layui-form" id="searchForm" method="post">
								<div style="margin-top: 5px;" class="layui-row">

									<div class="layui-col-md4">
										<div class='layui-form-item'>
											<label class='layui-form-label'>所属区域：</label>
											<div class='layui-input-block' id="organTreeIdTag"></div>
										</div>
									</div>
									<div class="layui-col-md4">
										<div class="layui-form-item">
											<label class="layui-form-label"> 登录账号： </label>
											<div class="layui-input-block">
												<input type="text" class="layui-input" name="loginName" autocomplete="off" placeholder="请输入登录账号" />
											</div>
										</div>
									</div>
									<div class="layui-col-md4">
										<div class="layui-form-item">
											<label class="layui-form-label"> 姓名： </label>
											<div class="layui-input-block">
												<input type="text" class="layui-input" name="name" autocomplete="off" placeholder="请输入用户姓名" />
											</div>
										</div>
									</div>
								</div>
								<div style="margin-top: 5px;" class="layui-row">
									<div class="layui-col-md4">
										<div class='layui-form-item'>
											<label class='layui-form-label'>所属单位：</label>
											<div class='layui-input-block' id="organNodeIdTag">
											</div>
										</div>
									</div>
									<div class="layui-col-md4">
										<div class="layui-btn-container" style="text-align: center;">
											<div class="layui-inline">
												<button class="layui-btn layui-btn-primary layui-btn-sm" type="button" data-method="search" title="查询">
													<i class="fa fa-search"></i>&nbsp;查询
												</button>
											</div>
											<div class="layui-inline">
												<button class="layui-btn layui-btn-primary layui-btn-sm" type="reset" data-method="history" title="重置">
													<i class="fa fa-history"></i>&nbsp;重置
												</button>
											</div>
										</div>
									</div>
									<div class="layui-col-md4">
										<div class="layui-btn-container" style="text-align: center;">
											<div class="layui-inline">
												<shiro:hasPermission name="ADD_SECURITY_USER">
													<button class="layui-btn layui-btn-normal layui-btn-sm" type="button" data-method="add" title="新增用户">
														<i class="fa fa-plus"></i>&nbsp;新增用户
													</button>
												</shiro:hasPermission>
											</div>
										</div>
									</div>
								</div>
							</form>
						</fieldset>
					</div>
					<div style="margin-top: 5px;" class="layui-row layui-col-space5">
						<div class="layui-col-md12">
							<table class="layui-table" lay-data="{id:'navigationList',url:'security/user/query',response:{statusName:'status',statusCode:200,countName:'totalSize',dataName:'result'}, cellMinWidth:60, height:'full-280', page:true,limit:10,limits:[10,50,100],loading:true, text:{none:'未找到用户数据！'}, initSort:{field:'loginName',type:'asc'}, skin:'row',even:true,size:'sm'}" lay-filter="navigationList">
								<thead>
									<tr>
										<th lay-data="{field:'loginName', width:'10.0%', minWidth:60, type:'normal', sort:true, unresize:false,   align:'center',  LAY_CHECKED:false,colspan:1, rowspan:1}">登录账号</th>
										<th lay-data="{field:'name', width:'10.0%', minWidth:60, type:'normal', sort:true, unresize:false,   align:'center',  LAY_CHECKED:false,colspan:1, rowspan:1}">姓名</th>
										<th lay-data="{field:'sexStr', width:'5.0%', minWidth:60, type:'normal', sort:true, unresize:false,   align:'center',  LAY_CHECKED:false,colspan:1, rowspan:1}">性别</th>
										<th lay-data="{field:'statusName', width:'7.5%', minWidth:60, type:'normal', sort:true, unresize:false,   align:'center',  LAY_CHECKED:false,colspan:1, rowspan:1}">账户状态</th>
										<th lay-data="{field:'accountTypeName', width:'7.5%', minWidth:60, type:'normal', sort:true, unresize:false,   align:'center',  LAY_CHECKED:false,colspan:1, rowspan:1}">账户类型</th>
										<th lay-data="{field:'departmentName', width:'15.0%', minWidth:60, type:'normal', sort:true, unresize:false,   align:'center',  LAY_CHECKED:false,colspan:1, rowspan:1}">所属单位</th>
										<th lay-data="{field:'email', width:'10.0%', minWidth:60, type:'normal', sort:false, unresize:false,   align:'center',  LAY_CHECKED:false,colspan:1, rowspan:1}">电子邮箱</th>
										<th lay-data="{field:'address', width:'10.0%', minWidth:60, type:'normal', sort:false, unresize:false,   align:'center',  LAY_CHECKED:false,colspan:1, rowspan:1}">联系地址</th>
										<th lay-data="{field:'mobile1', width:'10.0%', minWidth:60, type:'normal', sort:false, unresize:false,   align:'center',  LAY_CHECKED:false,colspan:1, rowspan:1}">移动电话</th>
										<th lay-data="{ width:'15.0%', minWidth:60, type:'normal', sort:false, unresize:false,   align:'center', toolbar:'#navListToolBar', LAY_CHECKED:false,colspan:1, rowspan:1}">操作</th>
									</tr>
									<script type="text/html" id="navListToolBar">
										<shiro:hasPermission name="EDIT_SECURITY_USER">
											<a class="layui-btn layui-btn-xs layui-btn-normal" style="" lay-event="edit"  title="编辑">
												<i class="fa fa-edit"></i>
											</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="ENABLE_SECURITY_USER">
											{{#  if(d.status !== 1){ }}
												<a class="layui-btn layui-btn-xs layui-btn-warm" style="" lay-event="start"  title="启用">
													<i class="fa fa-play-circle-o"></i>
												</a>
 											{{#  } }}
										</shiro:hasPermission>
										<shiro:hasPermission name="STOP_SECURITY_USER">
											{{#  if(d.status === 1){ }}
												<a class="layui-btn layui-btn-xs layui-btn-danger" style="" lay-event="stop"  title="停用">
													<i class="fa fa-stop-circle-o"></i>
												</a>
											{{#  } }}
										</shiro:hasPermission>
										<shiro:hasPermission name="RESET_SECURITY_USER_PASSWORD">
											<a class="layui-btn layui-btn-xs layui-btn-warm" style="" lay-event="reset"  title="重置密码">
												<i class="fa fa-rotate-left"></i>
											</a>
										</shiro:hasPermission>
										<shiro:hasPermission name="REMOVE_SECURITY_USER">
											<a class="layui-btn layui-btn-xs layui-btn-danger" style="" lay-event="remove"  title="删除用户">
												<i class="fa fa-remove"></i>
											</a>
										</shiro:hasPermission>
									</script>
								</thead>
							</table>
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
			layui.extend({
				linkSelect: 'layui/modules/combox/linkSelect',
				multiSelect: 'layui/modules/combox/multiSelect',
				smart: 'layui/modules/common/smart'
			}).use(['table', 'form', 'layer', 'element','linkSelect','multiSelect','smart'],function() {
				var $ = layui.jquery, 
				layer = layui.layer, 
				table = layui.table, 
				form = layui.form, 
				layer = layui.layer, 
				smart = layui.smart,
				element = layui.element,
				linkSelect = layui.linkSelect,
				multiSelect = layui.multiSelect;
				smart = smart();
			    var organTreeIdTag = linkSelect({
			      	elem: '#organTreeIdTag'
			      	,filter:'organTreeIdTag'
			      	,url:'system/organ/tree/query'	
				    ,name: 'organTreeId'
				    ,tips:'请选择所属区域'
				    ,width:'100%'
					,delimiter: ','
					,field: {idName:'id',titleName:'name'}
					,link :function (value) {
						var params = {};
						params.organTreeId = value;
						organNodeIdTag.refresh(params);
					}
			    });
			    var organNodeIdTag = linkSelect({
			      	elem: '#organNodeIdTag'
			      	,filter:'organNodeIdTag'
			      	,url: 'system/organ/node/query'	
					,max : 1
				    ,name: 'organNodeId'
				    ,tips:'请选择所属单位'
				    ,width:'100%'
					,delimiter: ','
					,search:true
					,field: {idName:'id',titleName:'name'}
			    });

				table.on('tool(navigationList)', function(obj) {
					tableInvokeMethod[obj.event] ? tableInvokeMethod[obj.event].call(this, obj) : '';
				});

				table.on('sort(navigationList)', function(obj) {
					tableInvokeMethod['sort'] ? tableInvokeMethod['sort'].call(this, obj) : '';
				});
				var tableInvokeMethod = {
					edit : function(obj) {
						smart.show({
							title : '编辑用户',
							size : 'full',
							url : 'security/user/info/edit',
							params :{
								id : obj.data.id
							},
							scrollbar : false
						});
					},
					start : function(result) {
						smart.confirm({
							title:'启用用户',
							message:'确认激活或启动用户【'+ result.data.name +'】？',
							url:'security/user/status/start',
							params : {id : result.data.id},
							callback : buttonInvokeMethod.search
						});
					},
					stop : function(result) {
						smart.confirm({
							title:'锁定账户',
							message:'确认锁定或停用用户【'+ result.data.name +'】？',
							url:'security/user/status/stop',
							params : {id : result.data.id},
							callback : buttonInvokeMethod.search
						});
					},
					reset : function(result) {
						smart.confirm({
							title:'重置密码',
							message:'确认重置用户【'+ result.data.name +'】的密码？',
							url:'security/user/password/reset',
							params : {id : result.data.id}
						});
					},
					remove : function(result) {
						smart.confirm({
							title:'删除用户',
							message:'确认删除用户【'+ result.data.name +'】？',
							url:'security/user/status/remove',
							params : {id : result.data.id},
							callback : buttonInvokeMethod.search
						});
					}
				};
				var buttonInvokeMethod = {
					search : function() {
						var params = smart.json($('#searchForm'));
						table.reload('navigationList', {
							where : params,
							page : {
								curr : 1
							}
						});
					},
					add : function() {
						smart.show({
							title : '新增用户',
							size : 'full',
							url : 'security/user/info/edit',
							scrollbar : false
						});
					}
				};
				$('#searchForm button').on('click', function() {
					var othis = $(this), method = othis.data('method');
					buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this,othis) : '';
				});
			});
		</script>
	</div>
</body>
</html>