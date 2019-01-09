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
			<c:if test="${not empty busId}">  
				<smart:cardHead>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="公务员管理"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="已办事项" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:cardHead>
			</c:if>
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="已办事项查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:singleSelect labelName="业务类型" isAddDefaltOption="true" name="busType" 
									display="block" data="${busTypeList}" isSearch="true"></smart:singleSelect>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:singleSelect labelName="来文单位：" name="sourceOrganNodeId"
									isSearch="true" display="block"
									url="system/organ/node/query?organTreeId=394e21fa-1eb6-42ee-ba32-50655fa16517"
									isAddDefaltOption="true"></smart:singleSelect>
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
						<smart:table id="navigationList" url="workflow/done/page?busId=${busId}&category=${category}" height="full-150" text="未找到已办事项数据！" page="true">
							<tr>
								<smart:tableItem field="targetOrganNode" width=".1" templet="topTpl">办理单位</smart:tableItem>
								<smart:tableItem field="targetSecurityUser" width=".1" templet="downTpl">办理人员</smart:tableItem>
								<smart:tableItem field="sourceOrganNode" width=".1" sort="true">来文单位</smart:tableItem>
								<smart:tableItem field="createTime" width=".1" sort="true">来文时间</smart:tableItem>
								<smart:tableItem field="busName" width=".1">业务类型</smart:tableItem>
								<smart:tableItem field="ofcFlowDescription" width=".2">业务描述</smart:tableItem>
								<smart:tableItem field="result" width=".1">审批结果</smart:tableItem>
								<smart:tableItem field="opinion" width=".1">审批意见</smart:tableItem>
								<smart:tableItem align="center" width=".1" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								<smart:tableToolBtn theme="normal" event="viewFlowRecord" title="查看流程记录">
									<smart:icon icon="eye"></smart:icon>
								</smart:tableToolBtn>
							</smart:tableToolBar>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="form,layer,element,table">
		<smart:utils/>
		<smart:tableScriptAction tableId="navigationList">
			viewFlowRecord : function(record) {
				smart.show({
					title : '查看流程记录',
					url : 'workflow/detail/index',
					params : {
						id : record.data.id
					},
					size:'full',
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