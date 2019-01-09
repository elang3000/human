<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="申请调整机构信息查看"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:textInput type="hidden" name="id" value="${orgFormationMgrFlow.id }"></smart:textInput>
					<smart:textInput name="result" id="result" type="hidden"></smart:textInput>
					
					<smart:gridRow>
						<smart:title title="调整信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					
					<smart:gridRow>
						<table class="layui-table" lay-size="sm">
							<tbody>
								<tr>
									<td style="width:40%"><b>名称</b></td>
									<td style="width:30%"><b>调整前</b></td>
									<td style="width:30%"><b>调整后</b></td>
							    </tr>
							    <tr>
							    	<td>批准编制日期：</td>
							    	<td><label>${empty orgFormation.approveDate ? '—':orgFormation.approveDate}</label></td>
							    	<td>${orgFormationMgrFlow.approveDate }</td>
							    </tr>
							    <tr>
							    	<td>批准编制文号：</td>
							    	<td><label>${empty orgFormation.approveNo ? '—':orgFormation.approveNo}</label></td>
							    	<td>${orgFormationMgrFlow.approveNo }</td>
							    </tr>
							    <tr>
							    	<td>编制批准单位名称：</td>
							    	<td><label>${empty orgFormation.approveUnitName ? '—':orgFormation.approveUnitName}</label></td>
							    	<td>${orgFormationMgrFlow.approveUnitName }</td>
							    </tr>
							    <tr>
							    	<td>编制批准单位代码：</td>
							    	<td><label>${empty orgFormation.approveUnitCode ? '—':orgFormation.approveUnitCode}</label></td>
							    	<td>${orgFormationMgrFlow.approveUnitCode }</td>
							    </tr>
							    <tr>
							    	<td>批准编制总数：</td>
							    	<td><label>${empty orgFormation.unitPlanningTotal ? '—':orgFormation.unitPlanningTotal}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.unitPlanningTotal}
							    	</td>
							    </tr>
							    <tr>
							    	<td>实有人数：</td>
							    	<td><label>${empty orgFormation.actualNumber ? '—':orgFormation.actualNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.actualNumber}
							    	</td>
							    </tr>
							    <tr>
							    	<td>空编人数：</td>
							    	<td><label>${empty orgFormation.vacancyExcessNumber ? '—':orgFormation.vacancyExcessNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.vacancyExcessNumber}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（定编）处级职数：</td>
							    	<td><label>${orgFormation.approveChuNum}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.approveChuNum}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（实有）处级职数：</td>
							    	<td><label>${orgFormation.realChuNum}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.realChuNum}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（定编）乡科级正职（领导）职数：</td>
							    	<td><label>${orgFormation.approvePlusKeLeaderNum}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.approvePlusKeLeaderNum}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（实有）乡科级正职（领导）职数：</td>
							    	<td><label>${orgFormation.realPlusKeLeaderNum}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.realPlusKeLeaderNum}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（定编）乡科级正职（非领导）职数：</td>
							    	<td><label>${orgFormation.approvePlusKeNoLeaderNum}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.approvePlusKeNoLeaderNum}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（实有）乡科级正职（非领导）职数：</td>
							    	<td><label>${orgFormation.realPlusKeNoLeaderNum}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.realPlusKeNoLeaderNum}
							    	</td>
							    </tr>
							    
							    <tr>
							    	<td>（定编）乡科级副职（领导）职数：</td>
							    	<td><label>${orgFormation.approveMinusKeLeaderNum}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.approveMinusKeLeaderNum}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（实有）乡科级副职（领导）职数：</td>
							    	<td><label>${orgFormation.realMinusKeLeaderNum}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.realMinusKeLeaderNum}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（定编）乡科级副职（非领导）职数：</td>
							    	<td><label>${orgFormation.approveMinusKeNoLeaderNum}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.approveMinusKeNoLeaderNum}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（实有）乡科级副职（非领导）职数：</td>
							    	<td><label>${orgFormation.realMinusKeNoLeaderNum}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.realMinusKeNoLeaderNum}
							    	</td>
							    </tr>
							</tbody>
						</table>
					</smart:gridRow>
					
					<smart:gridRow>
							<smart:title title="三定方案附件" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					<smart:gridRow>
						<smart:gridColumn colPart="1">
							<smart:fileUpload accept="file" isView="true" buttonName="三定方案" dataName="planPath" auto="false" name="plan" size="20000" data="${orgFormationMgrFlow.planPath}"/>
						</smart:gridColumn>
					</smart:gridRow>

					<smart:gridRow>
						<smart:title title="审批信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="12">
							<smart:textarea labelName="审批意见:" id="opinion" name="opinion" display="block"></smart:textarea>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
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
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils/>
		<smart:fileUploadUtils/>
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'提示',
					message:'确认审批通过吗？',
					url:'orgFormationFlow/auditAdjustFlow',
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
					url:'orgFormationFlow/auditAdjustFlow',
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
					url:'orgFormationFlow/auditAdjustFlow',
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