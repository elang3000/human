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
					<smart:breadcrumbNavMenuItem iname="统一用户管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="组织机构管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="组织树管理" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="组织树查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="组织树编码：" autocomplete="off" placeholder="组织树编码" name="code"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="组织树名称：" autocomplete="off" placeholder="组织树名称" name="name"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询" theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
						  				 </smart:button>
										<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
						   				</smart:button>
						   				<smart:button size="sm" method="new" title="新增组树" theme="normal">
											<smart:icon icon="plus"></smart:icon>&nbsp;新增组织树
									   	</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
					<smart:gridColumn>
						<smart:table id="navigationList" url="system/organ/tree/query/page" height="full-200" sortField="name" sortType="asc" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem field="code" width=".2" sort="true">组织树编码</smart:tableItem>
								<smart:tableItem field="name" width=".2" sort="true">组织树名称</smart:tableItem>
								<smart:tableItem field="treeTypaName" width=".15">组织树类型</smart:tableItem>
								<smart:tableItem field="rootNodeName" width=".15">根节点名称</smart:tableItem>
								<smart:tableItem field="description" width=".2">描述信息</smart:tableItem>
								<smart:tableItem align="center" width=".1" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn theme="warn" event="maintain" title="维护组织树">
									<smart:icon icon="wrench"></smart:icon>
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
	<smart:scriptHead models="form,layer,element,table">
		<smart:tableScriptAction tableId="navigationList">
			edit : function(data) {
				smart.show({
					title : '修改组织树信息',
					url : 'system/organ/tree/edit',
					params : {
						id : data.data.id
					},
					scrollbar : false
				});
			},
			maintain:function(data) {
				location.href = 'system/organ/tree/node/maintain?id=' + data.data.id;
			},
			remove : function(data) {
				smart.confirm({
					title:'删除组织树',
					message:'确认删除组织树？',
					type:'POST',
					url:'system/organ/tree/remove/save',
					params : {id : data.data.id},
					callback : function() {
						var params = smart.json($('#searchForm'));
						table.reload('navigationList', {
							where : params
						});
					}
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			new : function() {
				smart.show({
					title : '新增组织树',
					url : 'system/organ/tree/create',
					scrollbar : false
				});
			},
			search : function() {
				var params = smart.json($('#searchForm'));
				table.reload('navigationList', {
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