<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="培训信息"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
					<smart:gridRow colSpace="5">
						<smart:gridColumn>
							<smart:grid>
								<smart:title title="培训信息" style="margin-top: 5px;" color="blue" />
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训名称" infovalue="${trainPlan.trainName}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="开始日期" infovalue="${trainPlan.startDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="结束日期" infovalue="${trainPlan.endDate}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
								<smart:gridRow>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="培训学时" infovalue="${trainPlan.hours}"></smart:infoShowerLabel>
									</smart:gridColumn>
									<smart:gridColumn colPart="4">
										<smart:infoShowerLabel infoname="描述" infovalue="${trainPlan.description}"></smart:infoShowerLabel>
									</smart:gridColumn>
								</smart:gridRow>
							</smart:grid>
						</smart:gridColumn>
					</smart:gridRow>
				
				
				<smart:gridRow>
					<smart:gridColumn>
						<smart:table id="navigationList" url="ofcflow/train/trainPersonList?planId=${trainPlan.id}"
							height="full-270" text="未找到用户数据！" page="true">
							<tr>
								<smart:tableItem field="name" width=".1" sort="false">姓名</smart:tableItem>
								<smart:tableItem field="hours" width=".1" sort="false">培训学时</smart:tableItem>
								<smart:tableItem field="startDate" width=".1" sort="false">开始时间</smart:tableItem>
								<smart:tableItem field="endDate" width=".1" sort="false">结束时间</smart:tableItem>
								<smart:tableItem field="nature" width=".1" sort="false">培训性质</smart:tableItem>
								<smart:tableItem field="level" width=".2" sort="false">培训组织单位级别</smart:tableItem>
								<smart:tableItem field="funds" width=".2" sort="false">培训经费（万元）</smart:tableItem>
								<smart:tableItem field="isAbroad" width=".1" sort="false">是否境外培训</smart:tableItem>
							</tr>
						</smart:table>
					</smart:gridColumn>
				</smart:gridRow>
				
				<smart:form id="editForm">
				<smart:fromTokenTag/>
				
					<smart:gridRow>
						<smart:textInput type="hidden" name="id"  value="${trainPlan.id}" ></smart:textInput>
						<smart:textInput type="hidden" id="result" name="result" ></smart:textInput>
						<c:if test="${trainPlan.status<=7}">
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="审批意见：" name="opinion" placeholder="审批意见"></smart:textInput>
						</smart:gridColumn>
						</c:if>
					</smart:gridRow>
						
					<smart:gridRow>
					<smart:line color="blue" />
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
						   <c:if test="${trainPlan.status<=7}">
									<smart:button method="pass" size="sm" title="审批通过"
										theme="normal">
										<smart:icon icon="check">&nbsp;审批通过</smart:icon>
									</smart:button>
									<smart:button method="noPass" size="sm" title="审批不通过"
										theme="danger">
										<smart:icon icon="refresh">&nbsp;审批不通过</smart:icon>
									</smart:button>
								</c:if>
								<c:if test="${trainPlan.status==8}">
									<smart:button method="pass" size="sm" title="确认"
										theme="normal">
										<smart:icon icon="check">&nbsp;确认</smart:icon>
									</smart:button>
							</c:if>
						     <smart:button size="sm" method="goBack" title="返回" theme="warm">
								<smart:icon icon="reply"></smart:icon>&nbsp;返回
							</smart:button>
						 </smart:buttonGroup>
					   </smart:gridColumn>
					</smart:gridRow>
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element">
		<smart:utils/>
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/train/operationPlan',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			noPass : function() {
				$("#result").val("0");//审批不通过
				smart.confirm({
					title:'确认审批不通过',
					message:'确认审批不通过吗？',
					url:'ofcflow/train/operationPlan',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
	</smart:scriptHead>
</smart:body>
</html>