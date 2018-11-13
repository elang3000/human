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
					<smart:breadcrumbNavMenuItem iname="按群组授权" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="普通群组查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="普通群组代码：" autocomplete="off" placeholder="普通用户群组代码" name="code"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="普通群组名称：" autocomplete="off" placeholder="普通用户群组名称" name="name"></smart:textInput>
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
						<smart:table id="groupList" url="group/common/page" height="full-200" sortField="name" sortType="asc" text="未找到系统群组数据！" page="true">
							<tr>
								<smart:tableItem field="code" width=".2" sort="true">普通群组代码</smart:tableItem>
								<smart:tableItem field="name" width=".3" sort="true">普通群组名称</smart:tableItem>
								<smart:tableItem field="description" width=".3">描述信息</smart:tableItem>
								<smart:tableItem align="center" width=".2" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="accredit" title="群组权限管理">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="form,layer,element,table">
		<smart:tableScriptAction tableId="groupList">
			accredit : function(data) {
				smart.show({
					title : '群组权限管理',
					url : 'security/authc/group/accredit/index',
					scrollbar : false,
					params :{
						id : data.data.id
					},
					size : 'full'
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = smart.json($('#searchForm'));
				table.reload('groupList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>