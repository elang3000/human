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
					<smart:breadcrumbNavMenuItem iname="统一权限管理"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="按用户授权" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="用户查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:linkSelect labelName="所属区域：" id="organTreeIdTag" display="block" />
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="登录名：" autocomplete="off" placeholder="输入系统登录名" verify="required" name="loginName" value="${user.loginName}"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="姓名：" autocomplete="off" placeholder="输入姓名" verify="required" name="name" value="${user.name}"></smart:textInput>
								</smart:gridColumn>
							</smart:gridRow>
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:linkSelect labelName="所属单位：" id="organNodeIdTag" display="block" />
								</smart:gridColumn>
								<smart:gridColumn colPart="4" colOffset="4">
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
						<smart:table id="navigationList" url="security/user/query" height="full-300" sortField="loginName" sortType="asc" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem field="loginName" width=".1" sort="true">登录账号</smart:tableItem>
								<smart:tableItem field="name" width=".1" sort="true">姓名</smart:tableItem>
								<smart:tableItem field="sexStr" width=".05" sort="true">性别</smart:tableItem>
								<smart:tableItem field="statusName" width=".075" sort="false">账户状态</smart:tableItem>
								<smart:tableItem field="accountTypeName" width=".075" sort="false">账户类型</smart:tableItem>
								<smart:tableItem field="departmentName" width=".15" sort="false">所属单位</smart:tableItem>
								<smart:tableItem field="email" width=".1" sort="false">电子邮件</smart:tableItem>
								<smart:tableItem field="address" width=".1" sort="false">办公地址</smart:tableItem>
								<smart:tableItem field="officePhone" width=".1" sort="false">办公电话</smart:tableItem>
								<smart:tableItem align="center" width=".15" fixed="right" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="accredit" title="用户权限管理">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>

	<smart:scriptHead models="form,layer,element,table,linkSelect">
		var linkOrganNodeSelect = function(value) {
			var params = {};
			params.organTreeId = value;
			organNodeIdTag.refresh(params);
		}
		<smart:initLinkSelect id="organTreeIdTag" name="organTreeId" tips="请选择所属区域" selected="${organTreeId}" url="system/organ/tree/query" linkFunction="linkOrganNodeSelect" />
		<smart:initLinkSelect id="organNodeIdTag" name="organNodeId" tips="请选择所属单位" selected="${organNodeId}" url="system/organ/node/query" params="{organTreeId:'${organTreeId}'}" />
		<smart:tableScriptAction tableId="navigationList">
			accredit : function(data) {
				smart.show({
					title : '用户授权管理',
					size : 'full',
					url : 'security/authc/user/accredit/index',
					params :{
						id : data.data.id
					},
					scrollbar : false
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
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
</smart:body>