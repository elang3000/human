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
					<smart:fieldSet title="系统人员查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="用户姓名：" autocomplete="off" placeholder="用户姓名" name="name"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="移动电话：" autocomplete="off" placeholder="用户移动电话" name="mobile1"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询" theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
						  				 </smart:button>
										<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
						   				</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
					<smart:gridColumn>
						<smart:table id="dictTypeList" url="group/common/noneuser/page?groupId=${group.id}" height="full-150" sortField="name" sortType="asc" text="未找到应用系统数据！" page="true">
							<tr>
								<smart:tableItem field="loginName" width=".1" sort="true">登录名</smart:tableItem>
								<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sexStr" width=".05" sort="true">性别</smart:tableItem>
								<smart:tableItem field="statusName" width=".1">账户状态</smart:tableItem>
								<smart:tableItem field="accountTypeName" width=".1">账户类型</smart:tableItem>
								<smart:tableItem field="departmentName" width=".15">所属单位</smart:tableItem>
								<smart:tableItem field="email" width=".1">电子邮箱</smart:tableItem>
								<smart:tableItem field="address" width=".1">联系地址</smart:tableItem>
								<smart:tableItem field="mobile1" width=".1">移动电话</smart:tableItem>
								<smart:tableItem align="center" width=".1" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn event="addUser" title="新增人员">
									<smart:icon icon="plus"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="form,layer,element,table">
		<smart:tableScriptAction tableId="dictTypeList">
			addUser:function(data) {
				smart.confirm({
					title : '新增用户',
					url : 'group/common/user/add',
					message:'新增用户至' + '${group.name}' + '群组?',
					params : {
						userId : data.data.id,
						groupId : '${group.id}'
					},
					callback : function() {
						parent.refresh();
						var params = smart.json($('#searchForm'));
						table.reload('dictTypeList', {
							where : params,
							page : {
								curr : 1
							}
						});
					},
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = smart.json($('#searchForm'));
				table.reload('dictTypeList', {
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