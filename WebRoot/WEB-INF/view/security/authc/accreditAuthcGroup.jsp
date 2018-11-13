<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统" />
</head>
<smart:body>
	<smart:grid>
		<smart:gridRow>
			<blockquote class="layui-elem-quote">
				<div>群组代码： ${group.code}&nbsp;&nbsp;&nbsp;群组名称：${group.name}</div>
			</blockquote>
		</smart:gridRow>
		<smart:fieldSet title = "群组权限查询" color="blue">
			<smart:gridRow>
				<smart:form id="searchForm">
					<input type="hidden" name="groupId" value="${group.id}"/>
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:linkSelect labelName="资源类型：" id="resourceTypeId"  display="block" />
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:linkSelect labelName="操作类型：" id="operationId"  display="block" />
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:linkSelect labelName="应用系统：" id="appNodeId"  display="block" />
						</smart:gridColumn>
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:buttonGroup container="true">
							   	<smart:button size="sm" method="accreditInBatch" title="批量授予权限">
									<smart:icon icon="check"></smart:icon>&nbsp;授权
							   	</smart:button>
							   	<smart:button size="sm" method="antiAccreditInBatch" title="批量取消授予权限">
									<smart:icon icon="check"></smart:icon>&nbsp;取消授权
							   	</smart:button>
							   	<smart:button size="sm" method="revokeInBatch" title="批量禁用权限" theme="danger">
									<smart:icon icon="flash"></smart:icon>&nbsp;禁止
							   	</smart:button>
							   	<smart:button size="sm" method="antiRevokeInBatch" title="批量取消禁用权限" theme="danger">
									<smart:icon icon="flash"></smart:icon>&nbsp;取消禁止
							   	</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:buttonGroup container="true">
								<smart:button size="sm" method="search" title="查询" theme="primary">
									<smart:icon icon="search"></smart:icon>&nbsp;查询
							  	</smart:button>
								<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
									<smart:icon icon="undo"></smart:icon>&nbsp;重置
							   	</smart:button>
							</smart:buttonGroup>
						</smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:gridRow>
		</smart:fieldSet>
		<smart:gridRow>
			<table class="layui-hidden" id="treeTable" lay-filter="treeTable"></table>
		</smart:gridRow>
	</smart:grid>
	<script type="text/html" id="tableAction">
		{{#  if(d.authc === 0){ }}
			<a class="layui-btn layui-btn-xs" lay-event="accredit">授权</a>
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="revoke">禁止</a>
		{{#  } }} 
		{{#  if(d.authc === 1){ }}
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="antiAccredit">取消授权</a>
		{{#  } }} 
		{{#  if(d.authc === -1){ }}
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="antiRevoke">取消禁止</a>
		{{#  } }} 
	</script>
	<smart:scriptHead models="form,layer,element,table,linkSelect,treeGrid">
		var linkOperationSelect = function(value) {
			var params = {};
			params.resourceTypeId = value;
			operationId.refresh(params);
		}
		<smart:initLinkSelect verify="required" id="appNodeId" name="appNodeId" tips="请选择应用系统" url="app/query/all"  />
		<smart:initLinkSelect verify="required" id="resourceTypeId" name="resourceTypeId" tips="请选择资源类型" url="security/authc/resource/type"  linkFunction="linkOperationSelect"/>
		<smart:initLinkSelect verify="required" id="operationId" name="operationId" tips="请选择资源操作类型" url="security/authc/resource/operation" />
		
		 var treeTable =treeGrid.render({
            elem: '#treeTable'
            ,url:'security/authc/group/info'
            ,cellMinWidth: 100
            ,size: 'sm'
            ,treeId:'id'
            ,treeUpId:'parentId'
            ,treeShowName:'name'
            ,response:{
	            statusName:'status',
	            statusCode:200,
	            countName:'totalSize',
	            dataName:'result'
            }
            ,cols: [[
                {type:'checkbox'}
                ,{field:'name',align:'left',title: '资源名称'}
                ,{field:'resourceTypeName',align:'center',title: '资源类型'}
                ,{field:'operationName',align:'center',title: '操作类型'}
                ,{field:'authcName',align:'center',title:'权限'}
                ,{align:'center',title:'操作',toolbar:'#tableAction'}
            ]]
            ,page:false
        });
  		//监听工具条
 		treeGrid.on('tool(treeTable)', function(obj){
    		var data = obj.data;
    		data.children = null;
    		var pArray = [];
    		pArray.push(data);
    		if(obj.event === 'accredit'){
      			smart.confirm({
					title : '授权',
					message : '确认授予相关权限？',
					url:'security/authc/group/accredit/do',
					headers : {
				        'Accept': 'application/json',
				        'Content-Type': 'application/json'
				    },
					params : JSON.stringify(pArray),
					callback : function() {
						var params = smart.json($('#searchForm'));
						treeGrid.reload('treeTable', {
							where : params
						});
					}
				});
   			} 
   			
   			if(obj.event === 'antiAccredit'){
      			smart.confirm({
					title : '取消授权',
					message : '取消授予相关权限？',
					url:'security/authc/group/accredit/undo',
					headers : {
				        'Accept': 'application/json',
				        'Content-Type': 'application/json'
				    },
					params : JSON.stringify(pArray),
					callback : function() {
						var params = smart.json($('#searchForm'));
						treeGrid.reload('treeTable', {
							where : params
						});
					}
				
				});
   			} 
   			if(obj.event === 'antiRevoke'){
      			smart.confirm({
					title : '取消禁止',
					message:'取消禁止相关权限？',
					url:'security/authc/group/accredit/unrevoke',
					headers : {
				        'Accept': 'application/json',
				        'Content-Type': 'application/json'
				    },
					params:JSON.stringify(pArray),
					callback : function() {
						var params = smart.json($('#searchForm'));
						treeGrid.reload('treeTable', {
							where : params
						});
					}
				});
		    }
   			if(obj.event === 'revoke'){
      			smart.confirm({
					title : '禁止',
					message:'确认禁止相关权限？',
					url:'security/authc/group/accredit/revoke',
					params:JSON.stringify(pArray),
					headers : {
				        'Accept': 'application/json',
				        'Content-Type': 'application/json'
				    },
					callback : function() {
						var params = smart.json($('#searchForm'));
						treeGrid.reload('treeTable', {
							where : params
						});
					}
				});
		    }
  		});
  		$('.layui-btn').on('click', function() {
			var othis = $(this), method = othis.data('method');
			buttonInvokeMethod[method] ? buttonInvokeMethod[method].call(this, othis) : '';
		});
		var buttonInvokeMethod = {
			search : function() {
				var params = smart.json($('#searchForm'));
				treeGrid.reload('treeTable', {
					where : params
				});
			},
			accreditInBatch : function() {
				var checkStatus = treeGrid.checkStatus('treeTable'),data = checkStatus.data,length = data.length;
				var pArray = [];
				data.forEach(function (d) {
					if (d.authc === 0) {
						d.children = null;
						pArray.push(d);
					}
  				});
				if (length > 0) {
					smart.confirm({
						title : '授权',
						message : '确认授予相关权限？',
						url:'security/authc/group/accredit/do',
						headers : {
				        	'Accept': 'application/json',
				        	'Content-Type': 'application/json'
				   		 },
						params : JSON.stringify(pArray),
						callback : function() {
							var params = smart.json($('#searchForm'));
							treeGrid.reload('treeTable', {
								where : params
							});
						}
					});
				} else {
					smart.message({
						type : 'W',
	      				message : '请选择至少一项事项授予相关权限。'
	      			});
				}
			},
			antiAccreditInBatch : function() {
				var checkStatus = treeGrid.checkStatus('treeTable'),data = checkStatus.data,length = data.length;
				var pArray = [];
				data.forEach(function (d) {
					if (d.authc === 1) {
						d.children = null;
						pArray.push(d);
					}
  				});
  
				if (length > 0) {
					smart.confirm({
						title : '取消授权',
						message : '取消授予相关权限？',
						url:'security/authc/group/accredit/undo',
						headers : {
				        	'Accept': 'application/json',
				        	'Content-Type': 'application/json'
				   		},
						params : JSON.stringify(pArray),
						callback : function() {
							var params = smart.json($('#searchForm'));
							treeGrid.reload('treeTable', {
								where : params
							});
						}
					});
				} else {
					smart.message({
						type : 'W',
	      				message : '请选择至少一项事项授予相关权限。'
	      			});
				}
			},
			revokeInBatch : function() {
				var checkStatus = treeGrid.checkStatus('treeTable'),data = checkStatus.data,length = data.length;
				var pArray = [];
				data.forEach(function (d) {
					if (d.authc === 0) {
						d.children = null;
						pArray.push(d);
					}
  				});
				if (length > 0) {
					smart.confirm({
						title : '禁止',
						message:'确认禁止相关权限？',
						url:'security/authc/group/accredit/revoke',
						headers : {
				        	'Accept': 'application/json',
				        	'Content-Type': 'application/json'
				   		},
						params : JSON.stringify(pArray),
						callback : function() {
							var params = smart.json($('#searchForm'));
							treeGrid.reload('treeTable', {
								where : params
							});
						}
					});
				} else {
					smart.message({
						type : 'W',
	      				message :  '请选择至少一项事项禁止相关权限。'
	      			});
				}
			},
			antiRevokeInBatch : function() {
				var checkStatus = treeGrid.checkStatus('treeTable'),data = checkStatus.data,length = data.length;
				var pArray = [];
				data.forEach(function (d) {
					if (d.authc === -1) {
						d.children = null;
						pArray.push(d);
					}
  				});
				if (length > 0) {
					smart.confirm({
						title : '取消禁止',
						message:'取消禁止相关权限？',
						url:'security/authc/group/accredit/unrevoke',
						headers : {
				        	'Accept': 'application/json',
				        	'Content-Type': 'application/json'
				   		},
						params : JSON.stringify(pArray),
						callback : function() {
							var params = smart.json($('#searchForm'));
							treeGrid.reload('treeTable', {
								where : params
							});
						}
					});
				} else {
					smart.message({
						type : 'W',
	      				message :  '请选择至少一项事项禁止相关权限。'
	      			});
				}
			}
		};
	</smart:scriptHead>
</smart:body>