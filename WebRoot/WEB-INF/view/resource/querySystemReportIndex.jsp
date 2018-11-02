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
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="系统报表查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="系统报表名称：" autocomplete="off" placeholder="请输入系统报表名称" name="name"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="系统报表编码：" autocomplete="off" placeholder="请输入系统报表编码" name="code"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询" theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
						  				 </smart:button>
										<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
						   				</smart:button>
						   				<smart:button size="sm" method="create" title="新建报表" theme="normal">
											<smart:icon icon="plus"></smart:icon>&nbsp;新建报表
									   	</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
					<smart:gridColumn>
						<smart:table id="systemReportList" url="system/report/page" height="full-150" sortField="name" sortType="asc" text="未找到系统报表数据！" page="true">
							<tr>
								<smart:tableItem field="code" width=".2" sort="true">报表编码</smart:tableItem>
								<smart:tableItem field="name" width=".2" sort="true">报表名称</smart:tableItem>
								<smart:tableItem field="template" width=".2" sort="false">模板地址</smart:tableItem>
								<smart:tableItem field="description" width=".2">描述</smart:tableItem>
								<smart:tableItem align="center" width=".2" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="warm" event="edit" title="编辑">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								<smart:tableToolBtn event="param" title="维护报表参数">
									<smart:icon icon="book"></smart:icon>
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
		<smart:tableScriptAction tableId="systemReportList">
			edit:function(res) {
				smart.show({
					title : '维护系统报表信息',
					url : 'system/report/edit/index',
					params : {
						id : res.data.id
					},
					size : 'sm',
					scrollbar : false
				});
			},
			param(res) {
				smart.show({
					title : '维护系统报表参数',
					url : 'system/report/param/index',
					params : {
						id : res.data.id
					},
					size : 'sm',
					scrollbar : false
				});
			},
			remove:function(res) {
				smart.confirm({
					title:'删除系统报表',
					message:'确认删除系统报表信息？',
					type:'POST',
					url:'system/report/remove/save',
					params : {
						id : res.data.id
					},
					callback : function() {
						var params = smart.json($('#searchForm'));
						table.reload('systemReportList', {
							where : params
						});
					}
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			create:function() {
				smart.show({
					title : '新增系统报表信息',
					url : 'system/report/create/index',
					size : 'sm',
					scrollbar : false
				});
			},
			search : function() {
				var params = smart.json($('#searchForm'));
				table.reload('systemReportList', {
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