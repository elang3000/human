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
					<smart:fieldSet title="查询条件" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="登录名：" autocomplete="off" placeholder="请输入登录名" name="loginName"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:linkSelect labelName="应用系统：" id="appNodeId"  display="block" />
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
						<smart:table id="loginHistoryList" url="system/login/history/page" height="full-150" text="未找到用户登录历史数据！" page="true">
							<tr>
								<smart:tableItem field="userName" width=".1" sort="true">用户名</smart:tableItem>
								<smart:tableItem field="loginName" width=".1" sort="true">登录名</smart:tableItem>
								<smart:tableItem field="appName" width=".2" sort="true">应用系统</smart:tableItem>
								<smart:tableItem field="loginTimeStr" width=".1">登录时间</smart:tableItem>
								<smart:tableItem field="ipAddress" width=".1">IP地址</smart:tableItem>
								<smart:tableItem field="browser" width=".2">浏览器信息</smart:tableItem>
								<smart:tableItem field="errorInfo" width=".2">错误信息</smart:tableItem>
							</tr>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="form,layer,element,table,linkSelect">
		<smart:initLinkSelect id="appNodeId" name="appId" tips="请选择应用系统" url="app/query/all"  />
		<smart:buttonScriptAction>
			search : function() {
				var params = smart.json($('#searchForm'));
				table.reload('loginHistoryList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>