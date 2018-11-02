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
					<smart:fieldSet title="用户查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="3">
									<smart:textInput labelName="用户姓名：" autocomplete="off" placeholder="用户姓名" name="name"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:textInput labelName="用户登录名：" autocomplete="off" placeholder="用户登录名" name="loginName"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:linkSelect labelName="已在系统中：" id="inAppNodeTag" display="block"/>
								</smart:gridColumn>
								<smart:gridColumn colPart="3">
									<smart:buttonGroup container="true">
										<smart:button size="sm" method="search" title="查询" theme="primary">
											<smart:icon icon="search"></smart:icon>&nbsp;查询
						  				 </smart:button>
										<smart:button size="sm" method="history" title="重置" theme="primary" type="reset">
											<smart:icon icon="history"></smart:icon>&nbsp;重置
						   				</smart:button>
						   				<smart:button size="sm" method="addUser" title="添加用户" theme="normal">
											<smart:icon icon="plus"></smart:icon>&nbsp;添加用户
									   	</smart:button>
									</smart:buttonGroup>
								</smart:gridColumn>
							</smart:gridRow>
						</smart:form>
					</smart:fieldSet>
					<smart:gridColumn>
						<smart:table id="appNodeList" url="app/user/page?id=${id}" height="full-150" sortField="name" sortType="asc" text="未找到应用系统数据！" page="true">
							<tr>
								<smart:tableItem isCheckbox="true">全选</smart:tableItem>
								<smart:tableItem field="name" width=".2" sort="true">用户姓名</smart:tableItem>
								<smart:tableItem field="loginName" width=".2" sort="true">用户登录名</smart:tableItem>
								<smart:tableItem field="sexStr" width=".1" sort="true">性别</smart:tableItem>
								<smart:tableItem field="officePhone" width=".1">办公电话</smart:tableItem>
								<smart:tableItem field="honePhone" width=".1">移动电话</smart:tableItem>
								<smart:tableItem field="email" width=".15">电子邮件</smart:tableItem>
								<smart:tableItem align="center" width=".1" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								{{#  if(d.appNodeId != null){ }}
									<smart:tableToolBtn theme="danger" event="removeUser" title="删除">
										<smart:icon icon="remove"></smart:icon>
									</smart:tableToolBtn>
	  							{{#  }}}
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="form,layer,element,table,linkSelect">
		<smart:initLinkSelect id="inAppNodeTag" name="inAppNode" tips="是否已在系统当中" data="[{id:0,name:'未在系统'},{id:1,name:'已在系统'}]"/>
		<smart:tableScriptAction tableId="appNodeList">
			removeUser:function(data) {
				smart.confirm({
					title : '移出用户',
					message : '确从当前系统当中移出用户？',
					url:'app/user/remove',
					params : {
						appNodeId:'${id}',
						userId:data.data.id
					},
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
			addUser : function() {
				var checkStatus = table.checkStatus('appNodeList');
				if (checkStatus.data.length == 0) {
					smart.message({
						type : 'W',
						message : '请选择需要添加的用户！'
					});
					return;
				} else {
					var userIds = [];
					for (var i=0 ; i < checkStatus.data.length; i++) {
						var user = checkStatus.data[i];
						if (user.appNodeId == null) {
							userIds.push(user.id);
						}
					}
					if (userIds.length > 0) {
						smart.confirm({
							title : '添加用户',
							message : '确认添加用户至系统？',
							headers : {
						        'Accept': 'application/json',
						        'Content-Type': 'application/json'
						    },
							url:'app/user/add/${id}',
							params : JSON.stringify(userIds),
							callback : function() {
								var params = smart.json($('#searchForm'));
								table.reload('appNodeList', {
									where : params
								});
							}
						});
					} else {
						smart.message({
							type : 'W',
							message : '请选择未在当前系统当中的用户！'
						});
						return;
					}
				}
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
</smart:body>