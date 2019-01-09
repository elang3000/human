<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="编制调整申请查看"/>
<style>
	.layui-form-item{
		margin-bottom:0px;
	}
	.layui-input-inline{
		width:100% !important;
	}
</style>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm">
					<smart:gridRow>
						<smart:title title="调整信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					
					<smart:gridRow>
						<table class="layui-table" lay-size="sm">
							<tbody>
								<tr>
									<td style="width:40%"><b>名称</b></td>
									<td style="width:35%"><b>调整前</b></td>
									<td style="width:35%"><b>调整后</b></td>
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
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
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
	<smart:scriptHead models="table,form,layer,element,laydate,upload">
		<smart:utils/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:fileUploadUtils/>
		<smart:dateRender id="approveDate"/>
	</smart:scriptHead>
</smart:body>
</html>