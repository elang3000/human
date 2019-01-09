<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="审批重大事项申请"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${importantEventApply.id }"></smart:textInput>
					<smart:textInput name="result" id="result" type="hidden"></smart:textInput>
					<smart:gridRow>
						<smart:title title="事项信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:infoShowerLabel infoname="事项标题" infovalue="${importantEventApply.eventTitle}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="申请单位" infovalue="${importantEventApply.applyOrgan.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="8">
							<smart:infoShowerLabel infoname="事项说明" infovalue="${importantEventApply.description}"></smart:infoShowerLabel>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="事项说明" infovalue="${importantEventApply.eventType.name}"></smart:infoShowerLabel>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:title title="附件信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="1">
							<smart:fileUpload accept="file" buttonName="事项附件" dataName="filePath" auto="false" size="20000" data="${importantEventApply.filePath}" isView="true"/>
						</smart:gridColumn>
					</smart:gridRow>
					<c:if test="${importantEventApply.status==6 }">
						<smart:gridRow>
							<smart:title title="经办人选择" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						
						<smart:gridRow>
							<smart:gridColumn colPart="6">
								<smart:selectResource isNotNull="true" labelName="经办人姓名：" id="selectNameTag" display="block"/>
							</smart:gridColumn>
						</smart:gridRow>
					</c:if>
					<c:if test="${importantEventApply.status!=6 }">
						<smart:gridRow>
							<smart:title title="审批信息" style="margin-top: 5px;" color="blue" />
						</smart:gridRow>
						<smart:gridRow>
							<smart:gridColumn colPart="12">
								<smart:textarea labelName="审批意见:" id="opinion" name="opinion" display="block"></smart:textarea>
							</smart:gridColumn>
						</smart:gridRow>
					</c:if>
					<smart:gridRow>
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
					     	<c:if test="${importantEventApply.status!=6 }">
						     	<smart:button method="pass" size="sm" title="审批通过" theme="normal">
									<smart:icon icon="check">&nbsp;审批通过</smart:icon>
								</smart:button>
								<smart:button method="noPass" size="sm" title="审批驳回"
									theme="warm">
									<smart:icon icon="refresh">&nbsp;审批驳回</smart:icon>
								</smart:button>
								<smart:button method="stopPass" size="sm" title="审批不通过"
									theme="danger">
									<smart:icon icon="minus-circle">&nbsp;审批不通过</smart:icon>
								</smart:button>
							</c:if>
							<c:if test="${importantEventApply.status==6 }">
								<smart:button id="save" other="lay-submit" size="sm" title="确认"
										theme="normal">
									<smart:icon icon="check">&nbsp;确认</smart:icon>
								</smart:button>
							</c:if>
							<smart:button theme="primary" size="sm" method="goBack" title="返回">
								<smart:icon icon="reply">&nbsp;返回</smart:icon>
							</smart:button>
						  </smart:buttonGroup>
					   </smart:gridColumn>
					</smart:gridRow>
					
				</smart:form>
			</smart:cardBody>
		</smart:card>
	</smart:grid>
	<smart:scriptHead models="table,form,layer,element,laydate,upload,selectResource">
		<smart:utils/>
		<smart:initSelectResource verify="required" value="${importantEventApply.eventOperator.name}" hiddenValue="${importantEventApply.eventOperator.id}" id = "selectNameTag" name="eventOperator.name" hiddenName="eventOperator.id" winSize="lg" winTitle="选择人员信息" winUrl="orgInfo/selectServant" 
		linkElement=""/>
		<smart:fileUploadUtils/>
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'提示',
					message:'确认审批通过吗？',
					url:'importantEventApply/auditFlow',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			noPass : function() {
				$("#result").val("0");//审批驳回
				if(!$("#opinion").val()){
					smart.message({
						message : "请输入审批驳回意见！",
						type : 'W' //S保存  I问号  W感叹号 E错误
					});
					return;
				}
				smart.confirm({
					title:'提示',
					message:'确认审批驳回至上一办理人员？',
					url:'importantEventApply/auditFlow',
					params : smart.json("#editForm"),
					callback : function(){
						parent.layui.table.reload('navigationList');
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
				});
			},
			stopPass : function() {
				$("#result").val("-1");//审批不通过
				if(!$("#opinion").val()){
					smart.message({
						message : "请输入审批不通过意见！",
						type : 'W' //S保存  I问号  W感叹号 E错误
					});
					return;
				}
				smart.confirm({
					title:'提示',
					message:'确认审批不通过，结束业务办理？',
					url:'importantEventApply/auditFlow',
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
		form.on('submit(save)', function (data) {//表单保存
			$("#result").val("1");//审批通过
				smart.confirm({
					title:'提示',
					message:'确认经办人选择？',
					url:'importantEventApply/auditFlow',
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