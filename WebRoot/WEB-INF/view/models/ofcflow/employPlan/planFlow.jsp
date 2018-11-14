<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="smart"
	uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<smart:initHead title="长宁区人事管理信息系统--事项申请" />
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardHead>
				<smart:breadcrumbNavMenu separator=">">
					<smart:breadcrumbNavMenuItem iname="您现在的所在位置"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="事项申请"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="职位简章"></smart:breadcrumbNavMenuItem>
					<smart:breadcrumbNavMenuItem iname="审批招录计划" cite="true"></smart:breadcrumbNavMenuItem>
				</smart:breadcrumbNavMenu>
			</smart:cardHead>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:fromTokenTag/>
					<smart:gridRow>
						<smart:gridRow>
							<smart:title title="审批招录计划" style="margin-top: 5px;"
								color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="年度计划" infovalue="${recruityearplan.name}"></smart:infoShowerLabel>
								<smart:textInput type="hidden" id="result" name="result" ></smart:textInput>
								<smart:textInput type="hidden" id="id" name="id"  value="${recruitemployplan.id}" ></smart:textInput>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="起止时间" infovalue="${recruityearplan.startDate}至${recruityearplan.endDate}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="招录机构" infovalue="${recruitemployplan.recruitOrgan.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="用人机构" infovalue="${recruitemployplan.employOrgan.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构编制数" infovalue="${recruitemployplan.allowWeaveNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构实有人数" infovalue="${recruitemployplan.realNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构缺编数" infovalue="${recruitemployplan.thisYearLackWeaveNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="处级实职缺编人数" infovalue="${recruitemployplan.chiefLackWeaveNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="编制类型" infovalue="${recruitemployplan.recuritType.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel red="true" infoname="计划招录人数" infovalue="${recruitemployplan.planEmployNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="机构计划减员人数" infovalue="${recruitemployplan.planCutNum}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="人员类别" infovalue="${recruitemployplan.personType.name}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="联系人" infovalue="${recruitemployplan.contacter}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="联系电话" infovalue="${recruitemployplan.contactPhone}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="通讯地址" infovalue="${recruitemployplan.contactAddress}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="咨询电话" infovalue="${recruitemployplan.consultPhone}"></smart:infoShowerLabel>
							</smart:gridColumn>
							<smart:gridColumn colPart="4">
								<smart:infoShowerLabel infoname="备注" infovalue="${recruitemployplan.remark}"></smart:infoShowerLabel>
							</smart:gridColumn>
						</smart:gridRow>
						<c:if test="${recruitemployplan.planState>5 }">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel red="true" infoname="初审招录人数" infovalue="${recruitemployplan.firstEmployNum }"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
						</c:if>
						<c:if test="${recruitemployplan.planState>6 }">
							<smart:gridRow>
								<smart:gridColumn colPart="4">
									<smart:infoShowerLabel infoname="终审招录人数" infovalue="${recruitemployplan.endEmployNum }"></smart:infoShowerLabel>
								</smart:gridColumn>
							</smart:gridRow>
						</c:if>
						<smart:gridRow>
							<c:if test="${recruitemployplan.planState>=2&&recruitemployplan.planState<=5}">
								<smart:gridColumn colPart="4">
									<smart:numberInput display="block" isNotNull="true" verify="required" value="${recruitemployplan.firstEmployNum }" name="employNum" max="500" min="1" type="text" labelName="初审招录人数:" placeholder="初审招录人数"></smart:numberInput>
								</smart:gridColumn>
							</c:if>
							<c:if test="${recruitemployplan.planState==6}">
								<smart:gridColumn colPart="4">
									<smart:numberInput display="block" isNotNull="true" verify="required" name="employNum" max="500" min="1" type="text" labelName="终审招录人数:" placeholder="终审招录人数"></smart:numberInput>
								</smart:gridColumn>
							</c:if>
								<smart:gridColumn colPart="4">
									<smart:textInput labelName="审批意见:" name="opinion" id="opinion" placeholder="审批意见"></smart:textInput>
								</smart:gridColumn>
						</smart:gridRow>
					</smart:gridRow>
					<smart:gridRow>
						<smart:line color="blue" />
						<smart:gridColumn colPart="4" deviceType="md" colOffset="4">
							<smart:buttonGroup container="true">
								<c:if test="${recruitemployplan.planState<=6}">
									<smart:button id="pass" other="lay-submit" size="sm" title="审批通过"
										theme="normal">
										<smart:icon icon="check">&nbsp;审批通过</smart:icon>
									</smart:button>
									<smart:button method="noPass" size="sm" title="审批不通过"
										theme="danger">
										<smart:icon icon="refresh">&nbsp;审批不通过</smart:icon>
									</smart:button>
								</c:if>
								<c:if test="${recruitemployplan.planState==7}">
									<smart:button id="confirm" other="lay-submit" size="sm" title="确认"
										theme="normal">
										<smart:icon icon="check">&nbsp;确认</smart:icon>
									</smart:button>
								</c:if>
								<smart:button theme="warm" size="sm" method="goBack" title="返回">
									<smart:icon icon="reply">&nbsp;返回</smart:icon>
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
			noPass : function() {
				$("#result").val("0");//审批不通过
				if(!$("#opinion").val()){
					$("#opinion").val("不同意");
				}
				smart.confirm({
					title:'确认审批不通过',
					message:'确认审批不通过吗？',
					url:'ofcflow/recruit/operationPlan',
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
		 	form.on('submit(pass)', function (data) {//表单保存
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/recruit/operationPlan',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			});
			form.on('submit(confirm)', function (data) {//表单保存
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'确认审批通过',
					message:'确认审批通过吗？',
					url:'ofcflow/recruit/operationPlan',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			});
	</smart:scriptHead>
</smart:body>
</html>