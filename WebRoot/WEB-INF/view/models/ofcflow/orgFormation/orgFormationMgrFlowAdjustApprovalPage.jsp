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
						<table class="layui-table">
							<tbody>
								<tr>
									<td style="width:20%"><b>名称</b></td>
									<td style="width:40%"><b>调整前</b></td>
									<td style="width:40%"><b>调整后</b></td>
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
							    	<td>行政编制数：</td>
							    	<td><label>${empty orgFormation.adminWeaveNumber ? '—':orgFormation.adminWeaveNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.adminWeaveNumber}
							    	</td>
							    </tr>
							    <tr>
							    	<td>事业编制数：</td>
							    	<td><label>${empty orgFormation.institutionWeaveNumber ? '—':orgFormation.institutionWeaveNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.institutionWeaveNumber}
							    	</td>
							    </tr>
							    <tr>
							    	<td>参照公务员法管理事业编制数：</td>
							    	<td><label>${empty orgFormation.causeWeaveNumber ? '—':orgFormation.causeWeaveNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.causeWeaveNumber}
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
							    	<td><label>${empty orgFormation.approveDivisionChiefLevelNumber ? '—':orgFormation.approveDivisionChiefLevelNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.approveDivisionChiefLevelNumber}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（实有）处级职数：</td>
							    	<td><label>${empty orgFormation.divisionChiefLevelNumber ? '—':orgFormation.divisionChiefLevelNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.divisionChiefLevelNumber}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（空缺）处级职数：</td>
							    	<td><label>${empty orgFormation.vacancyDivisionChiefLevelNumber ? '—':orgFormation.vacancyDivisionChiefLevelNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.vacancyDivisionChiefLevelNumber}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（定编）科级（领导）职数：</td>
							    	<td><label>${empty orgFormation.approveSectionChiefLevelNumber ? '—':orgFormation.approveSectionChiefLevelNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.approveSectionChiefLevelNumber}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（实有）科级（领导）职数：</td>
							    	<td><label>${empty orgFormation.sectionChiefLevelNumber ? '—':orgFormation.sectionChiefLevelNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.sectionChiefLevelNumber}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（空缺）科级（领导）职数：</td>
							    	<td><label>${empty orgFormation.vacancySectionChiefLevelNumber ? '—':orgFormation.vacancySectionChiefLevelNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.vacancySectionChiefLevelNumber}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（定编）科级（非领导）职数：</td>
							    	<td><label>${empty orgFormation.approveNonLeaderSectionChiefLevelNumber ? '—':orgFormation.approveNonLeaderSectionChiefLevelNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.approveNonLeaderSectionChiefLevelNumber}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（实有）科级（非领导）职数：</td>
							    	<td><label>${empty orgFormation.nonLeaderSectionChiefLevelNumber ? '—':orgFormation.nonLeaderSectionChiefLevelNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.nonLeaderSectionChiefLevelNumber}
							    	</td>
							    </tr>
							    <tr>
							    	<td>（空缺）科级（非领导）职数：</td>
							    	<td><label>${empty orgFormation.vacancyNonLeaderSectionChiefLevelNumber ? '—':orgFormation.vacancyNonLeaderSectionChiefLevelNumber}</label></td>
							    	<td>
							    		${orgFormationMgrFlow.vacancyNonLeaderSectionChiefLevelNumber}
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
							<smart:textarea labelName="审批意见:" name="opinion" display="block"></smart:textarea>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
					     	<smart:button method="pass" size="sm" title="审批通过" theme="normal">
								<smart:icon icon="check">&nbsp;审批通过</smart:icon>
							</smart:button>
							<smart:button method="noPass" size="sm" title="审批不通过"
								theme="danger">
								<smart:icon icon="refresh">&nbsp;审批不通过</smart:icon>
							</smart:button>
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
				$("#result").val("0");//审批不通过
				smart.confirm({
					title:'提示',
					message:'确认审批不通过吗？',
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