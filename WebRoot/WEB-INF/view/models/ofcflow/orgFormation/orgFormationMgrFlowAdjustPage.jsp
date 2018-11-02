<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="申请编制调整"/>
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
				<smart:form id="editForm" action="orgFormationFlow/adjustSave">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${orgFormationMgrFlow.id }"></smart:textInput>
					<smart:textInput type="hidden" name="orgInfo.id" value="${orgFormationMgrFlow.orgInfo.id}"></smart:textInput>
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
							    	<td><smart:date name="approveDate" id="approveDate" display="inline" value="${orgFormationMgrFlow.approveDate }"></smart:date></td>
							    </tr>
							    <tr>
							    	<td>批准编制文号：</td>
							    	<td><label>${empty orgFormation.approveNo ? '—':orgFormation.approveNo}</label></td>
							    	<td><smart:textInput id="approveNo" display="inline" name="approveNo" value="${orgFormationMgrFlow.approveNo }"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>编制批准单位名称：</td>
							    	<td><label>${empty orgFormation.approveUnitName ? '—':orgFormation.approveUnitName}</label></td>
							    	<td><smart:textInput id="approveUnitName" display="inline" name="approveUnitName" value="${orgFormationMgrFlow.approveUnitName }" ></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>编制批准单位代码：</td>
							    	<td><label>${empty orgFormation.approveUnitCode ? '—':orgFormation.approveUnitCode}</label></td>
							    	<td><smart:textInput id="approveUnitCode" display="inline" name="approveUnitCode" value="${orgFormationMgrFlow.approveUnitCode }"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>批准编制总数：</td>
							    	<td><label>${empty orgFormation.unitPlanningTotal ? '—':orgFormation.unitPlanningTotal}</label></td>
							    	<td>
							    		<smart:numberInput id="unitPlanningTotal" name="unitPlanningTotal" value="${orgFormationMgrFlow.unitPlanningTotal}" display="inline" min="0" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>行政编制数：</td>
							    	<td><label>${empty orgFormation.adminWeaveNumber ? '—':orgFormation.adminWeaveNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="adminWeaveNumber" name="adminWeaveNumber" value="${orgFormationMgrFlow.adminWeaveNumber}" display="inline" min="0" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>事业编制数：</td>
							    	<td><label>${empty orgFormation.institutionWeaveNumber ? '—':orgFormation.institutionWeaveNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="institutionWeaveNumber" name="institutionWeaveNumber" value="${orgFormationMgrFlow.institutionWeaveNumber}" display="inline" min="0" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>参照公务员法管理事业编制数：</td>
							    	<td><label>${empty orgFormation.causeWeaveNumber ? '—':orgFormation.causeWeaveNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="causeWeaveNumber" name="causeWeaveNumber" value="${orgFormationMgrFlow.causeWeaveNumber}" display="inline" min="0" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>实有人数：</td>
							    	<td><label>${empty orgFormation.actualNumber ? '—':orgFormation.actualNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="actualNumber" name="actualNumber" value="${orgFormationMgrFlow.actualNumber}" display="inline" min="0" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>空编人数：</td>
							    	<td><label>${empty orgFormation.vacancyExcessNumber ? '—':orgFormation.vacancyExcessNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="vacancyExcessNumber" name="vacancyExcessNumber" value="${orgFormationMgrFlow.vacancyExcessNumber}" display="inline" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>（定编）处级职数：</td>
							    	<td><label>${empty orgFormation.approveDivisionChiefLevelNumber ? '—':orgFormation.approveDivisionChiefLevelNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="approveDivisionChiefLevelNumber" name="approveDivisionChiefLevelNumber" value="${orgFormationMgrFlow.approveDivisionChiefLevelNumber}" display="inline" min="0" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>（实有）处级职数：</td>
							    	<td><label>${empty orgFormation.divisionChiefLevelNumber ? '—':orgFormation.divisionChiefLevelNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="divisionChiefLevelNumber" name="divisionChiefLevelNumber" value="${orgFormationMgrFlow.divisionChiefLevelNumber}" display="inline" min="0" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>（空缺）处级职数：</td>
							    	<td><label>${empty orgFormation.vacancyDivisionChiefLevelNumber ? '—':orgFormation.vacancyDivisionChiefLevelNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="vacancyDivisionChiefLevelNumber" name="vacancyDivisionChiefLevelNumber" value="${orgFormationMgrFlow.vacancyDivisionChiefLevelNumber}" display="inline" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>（定编）科级（领导）职数：</td>
							    	<td><label>${empty orgFormation.approveSectionChiefLevelNumber ? '—':orgFormation.approveSectionChiefLevelNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="approveSectionChiefLevelNumber" name="approveSectionChiefLevelNumber" value="${orgFormationMgrFlow.approveSectionChiefLevelNumber}" display="inline" min="0" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>（实有）科级（领导）职数：</td>
							    	<td><label>${empty orgFormation.sectionChiefLevelNumber ? '—':orgFormation.sectionChiefLevelNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="sectionChiefLevelNumber" name="sectionChiefLevelNumber" value="${orgFormationMgrFlow.sectionChiefLevelNumber}" display="inline" min="0" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>（空缺）科级（领导）职数：</td>
							    	<td><label>${empty orgFormation.vacancySectionChiefLevelNumber ? '—':orgFormation.vacancySectionChiefLevelNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="vacancySectionChiefLevelNumber" name="vacancySectionChiefLevelNumber" value="${orgFormationMgrFlow.vacancySectionChiefLevelNumber}" display="inline" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>（定编）科级（非领导）职数：</td>
							    	<td><label>${empty orgFormation.approveNonLeaderSectionChiefLevelNumber ? '—':orgFormation.approveNonLeaderSectionChiefLevelNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="approveNonLeaderSectionChiefLevelNumber" name="approveNonLeaderSectionChiefLevelNumber" value="${orgFormationMgrFlow.approveNonLeaderSectionChiefLevelNumber}" display="inline" min="0" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>（实有）科级（非领导）职数：</td>
							    	<td><label>${empty orgFormation.nonLeaderSectionChiefLevelNumber ? '—':orgFormation.nonLeaderSectionChiefLevelNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="nonLeaderSectionChiefLevelNumber" name="nonLeaderSectionChiefLevelNumber" value="${orgFormationMgrFlow.nonLeaderSectionChiefLevelNumber}" display="inline" min="0" type="text"></smart:numberInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>（空缺）科级（非领导）职数：</td>
							    	<td><label>${empty orgFormation.vacancyNonLeaderSectionChiefLevelNumber ? '—':orgFormation.vacancyNonLeaderSectionChiefLevelNumber}</label></td>
							    	<td>
							    		<smart:numberInput id="vacancyNonLeaderSectionChiefLevelNumber" name="vacancyNonLeaderSectionChiefLevelNumber" value="${orgFormationMgrFlow.vacancyNonLeaderSectionChiefLevelNumber}" display="inline" type="text"></smart:numberInput>
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
								<smart:fileUpload accept="file" buttonName="三定方案" dataName="planPath" auto="false" name="plan" size="20000" data="${orgFormationMgrFlow.planPath}"/>
							</smart:gridColumn>
						</smart:gridRow>
					
					<smart:gridRow>
					   <smart:gridColumn>
					     <smart:buttonGroup container="true">
					     	<smart:button id="save" other="lay-submit" size="sm" title="暂存" theme="default">
								<smart:icon icon="plus">&nbsp;暂存</smart:icon>
							</smart:button>
							
					     	<smart:button id="submit" other="lay-submit" size="sm" title="提交" theme="normal">
								<smart:icon icon="check">&nbsp;提交</smart:icon>
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
		
		form.on('submit(save)', function (data) {//表单保存
				var params=data.field;
				var url=data.form.action;
				$.post(url,params,function(result){
					if(result.success){//保存成功
						layer.alert(
		                result.message, 
		                {icon: 1,closeBtn: 1 },
		                function () {
							parent.layui.table.reload('orgForamtionMgrFlowList');
		                	var index=parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
		                });
					}else{
						layer.alert(
		                result.message, 
		                {icon: 0,closeBtn:0 });
					}
				});
				return false;
		});
		
		form.on('submit(submit)', function(data) {
			var params=data.field;
			var url="orgFormationFlow/adjustSubmit";
			smart.confirm({
				title:'提示',
				message:'确认提交申请吗？',
				url:url,
				params : params,
				callback : function(){
					parent.layui.table.reload('orgForamtionMgrFlowList');
                	var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
	</smart:scriptHead>
</smart:body>
</html>