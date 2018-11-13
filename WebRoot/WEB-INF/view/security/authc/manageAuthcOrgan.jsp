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
					<smart:breadcrumbNavMenuItem iname="按组织授权" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="组织查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:linkSelect labelName="组织区域：" id="organTreeId"  display="block" />
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="组织名称：" autocomplete="off" placeholder="请输入组织名称" name="name"></smart:textInput>
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
						<smart:table id="organList" url="org/info/page" height="full-200" sortField="name" sortType="asc" text="未找到系统群组数据！" page="true">
							<tr>
								<smart:tableItem field="treeTypeName" width=".1">所在区域</smart:tableItem>
								<smart:tableItem field="code" width=".1" sort="true">组织编码</smart:tableItem>
								<smart:tableItem field="name" width=".1" sort="true">组织名称</smart:tableItem>
								<smart:tableItem field="allName" width=".2" sort="true">组织全名</smart:tableItem>
								<smart:tableItem field="organNodeTypeName" width=".1">组织类型</smart:tableItem>
								<smart:tableItem field="deptAddress" width=".1">组织地址</smart:tableItem>
								<smart:tableItem field="principal" width=".1">法人代表</smart:tableItem>
								<smart:tableItem field="description" width=".1">描述信息</smart:tableItem>
								<smart:tableItem align="center" width=".1" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="accredit" title="组织权限管理">
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
		<smart:initLinkSelect verify="required" id="organTreeId" name="organTreeId" tips="请选择所用所在组织数" url="system/organ/tree/query"/>
		<smart:tableScriptAction tableId="organList">
			accredit : function(data) {
				smart.show({
					title : '组织权限管理',
					url : 'security/authc/organ/accredit/index',
					scrollbar : false,
					params :{
						organNodeId : data.data.id,
						organTreeId : data.data.treeTypeId
					},
					size : 'full'
				});
			}
		</smart:tableScriptAction>
		<smart:buttonScriptAction>
			search : function() {
				var params = smart.json($('#searchForm'));
				table.reload('organList', {
					where : params,
					page : {
						curr : 1
					}
				});
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>