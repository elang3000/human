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
				<div>登录名： ${user.loginName}&nbsp;&nbsp;&nbsp;姓名：${user.name}</div>
			</blockquote>
		</smart:gridRow>
		<smart:fieldSet title = "用户权限查询" color="blue">
			<smart:gridRow>
				<smart:form id="searchForm">
					<input type="hidden" name="securityUserId" value="${user.id}"/>
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
						<smart:gridColumn colPart="4">
							<smart:linkSelect labelName="所用组织树：" id="organTreeId"  display="block" />
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:linkSelect labelName="所用组织节点：" id="organNodeId"  display="block" />
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
	<smart:scriptHead models="form,layer,element,table,linkSelect,treeGrid">
		var linkOperationSelect = function(value) {
			var params = {};
			params.resourceTypeId = value;
			operationId.refresh(params);
		}
		<smart:initLinkSelect verify="required" id="appNodeId" name="appNodeId" tips="请选择应用系统" url="app/query/all"  />
		<smart:initLinkSelect verify="required" id="resourceTypeId" name="resourceTypeId" tips="请选择资源类型" url="security/authc/resource/type"  linkFunction="linkOperationSelect"/>
		<smart:initLinkSelect verify="required" id="operationId" name="operationId" tips="请选择资源操作类型" url="security/authc/resource/operation" />
		var linkOrganNodeSelect = function(value) {
			var params = {};
			params.organTreeId = value;
			organNodeId.refresh(params);
		}
		<smart:initLinkSelect verify="required" id="organTreeId" name="organTreeId" tips="请选择所用所在组织数" url="system/organ/tree/query"  linkFunction="linkOrganNodeSelect"/>
		<smart:initLinkSelect verify="required" id="organNodeId" name="organNodeId" tips="请选择所用所在组织节点" url="system/organ/node/user/query/${user.id}"/>
		
		 var treeTable =treeGrid.render({
            elem: '#treeTable'
            ,url:'security/authc/user/info'
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
            ]]
            ,page:false
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
			}	
		};
	</smart:scriptHead>
</smart:body>