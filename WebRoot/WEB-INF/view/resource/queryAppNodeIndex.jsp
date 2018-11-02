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
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="应用系统管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="应用节点管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="系统节点管理" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="应用系统查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="6">
									<smart:textInput labelName="应用系统名称：" autocomplete="off" placeholder="应用系统名称" name="name"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="6">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询" theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
						  				 </smart:button>
										<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
						   				</smart:button>
						   				<smart:button size="sm" method="new" title="新增应用" theme="normal">
											<smart:icon icon="plus"></smart:icon>&nbsp;新增应用
									   	</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
					<smart:gridColumn>
						<smart:table id="appNodeList" url="app/query/page" height="full-200" sortField="name" sortType="asc" text="未找到应用系统数据！" page="true">
							<tr>
								<smart:tableItem field="code" width=".1" sort="true">应用系统代码</smart:tableItem>
								<smart:tableItem field="name" width=".2" sort="true">应用系统名称</smart:tableItem>
								<smart:tableItem field="nodeType" width=".1" templet="nodeTypeTpl">节点类型</smart:tableItem>
								<smart:tableItem field="indexURL" width=".1">首页链接</smart:tableItem>
								<smart:tableItem field="appServer" width=".1">应用服务器</smart:tableItem>
								<smart:tableItem field="dbServer" width=".1">数据库</smart:tableItem>
								<smart:tableItem field="description" width=".15">描述信息</smart:tableItem>
								<smart:tableItem align="center" width=".15" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="normal" event="addUser" title="添加人员">
									<smart:icon icon="user"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="normal" event="menu" title="菜单维护">
									<smart:icon icon="book"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="normal" event="func" title="功能维护">
									<smart:icon icon="anchor"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="danger" event="remove" title="删除">
									<smart:icon icon="remove"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<script type="text/html" id="nodeTypeTpl">
  		{{#  if(d.nodeType === 0){ }}
			中心节点
  		{{#  } else { }}
			普通节点
  		{{#  } }}
	</script>
	<smart:scriptHead models="form,layer,element,table">
		<smart:tableScriptAction tableId="appNodeList">
			edit : function(data) {
				smart.show({
					title : '修改应用系统信息',
					url : 'app/edit',
					params : {
						id : data.data.id
					},
					scrollbar : false
				});
			},
			menu:function(data) {
				smart.show({
					title : '维护应用系统菜单',
					url : 'resource/menu/index',
					params : {
						id : data.data.id
					},
					size:'full',
					scrollbar : false
				});
			},
			func :function(data) {
				smart.show({
					title : '维护应用功能',
					url : 'resource/console/index',
					params : {
						id : data.data.id
					},
					size:'full',
					scrollbar : false
				});
			},
			addUser :function(data) {
				smart.show({
					title : '添加应用系统用户',
					url : 'app/user/index',
					params : {
						id : data.data.id
					},
					size:'full',
					scrollbar : false
				});
			},
			remove : function(data) {
				smart.confirm({
					title:'删除应用系统',
					message:'确认删除应用系统？',
					type:'POST',
					url:'app/remove/save',
					params : {id : data.data.id},
					callback : function() {
						var params = smart.json($('#searchForm'));
						table.reload('appNodeList', {
							where : params
						});
					}
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			new : function() {
				smart.show({
					title : '新增应用系统',
					url : 'app/create',
					scrollbar : false
				});
			},
			search : function() {
				var params = smart.json($('#searchForm'));
				table.reload('appNodeList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
	<script type="text/javascript" src="ztree/js/jquery-1.4.4.min.js" charset="UTF-8"></script>
	<script type="text/javascript">
		function refresh() {
			$('#searchForm button[data-method="search"]').click();
		}
	</script>
</smart:body>