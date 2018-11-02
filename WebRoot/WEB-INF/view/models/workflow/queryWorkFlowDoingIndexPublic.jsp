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
		<!-- 判断busId不为空 -->
			<c:if test="${not empty busId}">  
				<smart:cardHead>
					<smart:breadcrumbNavMenu separator=">">
						<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="事业单位管理"></smart:breadcrumbNavMenuItem>
						<smart:breadcrumbNavMenuItem iname="待办事项" cite="true"></smart:breadcrumbNavMenuItem>
					</smart:breadcrumbNavMenu>
				</smart:cardHead>
			</c:if>  
			<smart:cardBody>
				<smart:gridRow>
					<smart:fieldSet title="待办事项查询" color="blue" style="padding-top:1em;">
						<smart:form id="searchForm">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="事项类型：" autocomplete="off" placeholder="事项类型" name="busType"></smart:textInput>
								</smart:gridColumn>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="发文单位：" autocomplete="off" placeholder="发文单位" name="sourceOrganNodeId"></smart:textInput>
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
						<smart:table id="navigationList" url="workflow/doing/page?busId=${busId}&category=I" height="full-150" sortField="createTime" sortType="desc" text="未找到已办事项数据！" page="true">
							<tr>
								<smart:tableItem field="sourceOrganNode" width=".1" sort="true">来文单位</smart:tableItem>
								<smart:tableItem field="createTime" width=".1" sort="true">来文时间</smart:tableItem>
								<smart:tableItem field="targetOrganNode" width=".1" templet="topTpl">办理单位</smart:tableItem>
								<smart:tableItem field="targetSecurityUser" width=".1" templet="downTpl">办理人员</smart:tableItem>
								<smart:tableItem field="busName" width=".2">业务类型</smart:tableItem>
								<smart:tableItem field="ofcFlowDescription" width=".2">业务描述</smart:tableItem>
								<smart:tableItem align="center" width=".2" toolbar="navListToolBar">操作</smart:tableItem>
							</tr>
							<smart:tableToolBar id="navListToolBar">
								{{# if (d.targetSecurityUser == '${userId}') { }}
								<smart:tableToolBtn theme="warm" event="complete" title="处理相关事项">
									<smart:icon icon="edit"></smart:icon>
								</smart:tableToolBtn>
								{{# } }}
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
			},
			complete : function(record) {
				smart.show({
					title : record.data.busName,
					url : 'workflow/complete/task',
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