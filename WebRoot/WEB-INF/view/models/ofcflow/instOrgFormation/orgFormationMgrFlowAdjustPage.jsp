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
				<smart:form id="editForm" action="institutionOrgFormationFlow/adjustSave">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${orgFormationMgrFlow.id }"></smart:textInput>
					<smart:textInput type="hidden" name="orgInfo.id" value="${orgFormationMgrFlow.orgInfo.id}"></smart:textInput>
					<smart:gridRow>
						<smart:title title="调整信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					
					<smart:gridRow>
						<table class="layui-table" lay-size="sm">
							<thead>
								<tr>
									<td style="width:20%"><b>名称</b></td>
									<td style="width:40%"><b>调整前</b></td>
									<td style="width:40%"><b>调整后</b></td>
							    </tr>
							</thead>    
							<tbody>
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
							</tbody>
						</table>
						<table class="layui-table" lay-size="sm">
							<thead>
								<tr>
									<td colspan="3" style="text-align:center"><b>管理人员</b></td>
									<td colspan="3" style="text-align:center"><b>专业技术人员</b></td>
									<td colspan="3" style="text-align:center"><b>工勤人员</b></td>
							    </tr>
							    <tr>
									<td style="width:13%"><b>名称</b></td>
									<td style="width:10%"><b>调整前</b></td>
									<td style="width:10%"><b>调整后</b></td>
									<td style="width:13%"><b>名称</b></td>
									<td style="width:10%"><b>调整前</b></td>
									<td style="width:10%"><b>调整后</b></td>
									<td style="width:13%"><b>名称</b></td>
									<td style="width:10%"><b>调整前</b></td>
									<td style="width:10%"><b>调整后</b></td>
							    </tr>
							    <tbody>
							    	<tr>
								    	<td>（定编）一级：</td>
								    	<td><label>${orgFormation.approveMgrLevelINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveMgrLevelINum" name="approveMgrLevelINum" value="${orgFormationMgrFlow.approveMgrLevelINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）一级：</td>
								    	<td><label>${orgFormation.approveTechLevelINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelINum" name="approveTechLevelINum" value="${orgFormationMgrFlow.approveTechLevelINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）一级：</td>
								    	<td><label>${orgFormation.approveWorkLevelINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveWorkLevelINum" name="approveWorkLevelINum" value="${orgFormationMgrFlow.approveWorkLevelINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）一级：</td>
								    	<td><label>${orgFormation.realMgrLevelINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realMgrLevelINum" name="realMgrLevelINum" value="${orgFormationMgrFlow.realMgrLevelINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）一级：</td>
								    	<td><label>${orgFormation.realTechLevelINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelINum" name="realTechLevelINum" value="${orgFormationMgrFlow.realTechLevelINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）一级：</td>
								    	<td><label>${orgFormation.realWorkLevelINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realWorkLevelINum" name="realWorkLevelINum" value="${orgFormationMgrFlow.realWorkLevelINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）二级：</td>
								    	<td><label>${orgFormation.approveMgrLevelIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveMgrLevelIINum" name="approveMgrLevelIINum" value="${orgFormationMgrFlow.approveMgrLevelIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）二级：</td>
								    	<td><label>${orgFormation.approveTechLevelIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelIINum" name="approveTechLevelIINum" value="${orgFormationMgrFlow.approveTechLevelIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）二级：</td>
								    	<td><label>${orgFormation.approveWorkLevelIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveWorkLevelIINum" name="approveWorkLevelIINum" value="${orgFormationMgrFlow.approveWorkLevelIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）二级：</td>
								    	<td><label>${orgFormation.realMgrLevelIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realMgrLevelIINum" name="realMgrLevelIINum" value="${orgFormationMgrFlow.realMgrLevelIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）二级：</td>
								    	<td><label>${orgFormation.realTechLevelIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelIINum" name="realTechLevelIINum" value="${orgFormationMgrFlow.realTechLevelIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）二级：</td>
								    	<td><label>${orgFormation.realWorkLevelIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realWorkLevelIINum" name="realWorkLevelIINum" value="${orgFormationMgrFlow.realWorkLevelIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）三级：</td>
								    	<td><label>${orgFormation.approveMgrLevelIIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveMgrLevelIIINum" name="approveMgrLevelIIINum" value="${orgFormationMgrFlow.approveMgrLevelIIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）三级：</td>
								    	<td><label>${orgFormation.approveTechLevelIIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelIIINum" name="approveTechLevelIIINum" value="${orgFormationMgrFlow.approveTechLevelIIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）三级：</td>
								    	<td><label>${orgFormation.approveWorkLevelIIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveWorkLevelIIINum" name="approveWorkLevelIIINum" value="${orgFormationMgrFlow.approveWorkLevelIIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）三级：</td>
								    	<td><label>${orgFormation.realMgrLevelIIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realMgrLevelIIINum" name="realMgrLevelIIINum" value="${orgFormationMgrFlow.realMgrLevelIIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）三级：</td>
								    	<td><label>${orgFormation.realTechLevelIIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelIIINum" name="realTechLevelIIINum" value="${orgFormationMgrFlow.realTechLevelIIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）三级：</td>
								    	<td><label>${orgFormation.realWorkLevelIIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realWorkLevelIIINum" name="realWorkLevelIIINum" value="${orgFormationMgrFlow.realWorkLevelIIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）四级：</td>
								    	<td><label>${orgFormation.approveMgrLevelIVNum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveMgrLevelIVNum" name="approveMgrLevelIVNum" value="${orgFormationMgrFlow.approveMgrLevelIVNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）四级：</td>
								    	<td><label>${orgFormation.approveTechLevelIVNum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelIVNum" name="approveTechLevelIVNum" value="${orgFormationMgrFlow.approveTechLevelIVNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）四级：</td>
								    	<td><label>${orgFormation.approveWorkLevelIVNum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveWorkLevelIVNum" name="approveWorkLevelIVNum" value="${orgFormationMgrFlow.approveWorkLevelIVNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）四级：</td>
								    	<td><label>${orgFormation.realMgrLevelIVNum}</label></td>
								    	<td>
								    		<smart:numberInput id="realMgrLevelIVNum" name="realMgrLevelIVNum" value="${orgFormationMgrFlow.realMgrLevelIVNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）四级：</td>
								    	<td><label>${orgFormation.realTechLevelIVNum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelIVNum" name="realTechLevelIVNum" value="${orgFormationMgrFlow.realTechLevelIVNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）四级：</td>
								    	<td><label>${orgFormation.realWorkLevelIVNum}</label></td>
								    	<td>
								    		<smart:numberInput id="realWorkLevelIVNum" name="realWorkLevelIVNum" value="${orgFormationMgrFlow.realWorkLevelIVNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）五级：</td>
								    	<td><label>${orgFormation.approveMgrLevelVNum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveMgrLevelVNum" name="approveMgrLevelVNum" value="${orgFormationMgrFlow.approveMgrLevelVNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）五级：</td>
								    	<td><label>${orgFormation.approveTechLevelVNum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelVNum" name="approveTechLevelVNum" value="${orgFormationMgrFlow.approveTechLevelVNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）五级：</td>
								    	<td><label>${orgFormation.approveWorkLevelVNum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveWorkLevelVNum" name="approveWorkLevelVNum" value="${orgFormationMgrFlow.approveWorkLevelVNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）五级：</td>
								    	<td><label>${orgFormation.realMgrLevelVNum}</label></td>
								    	<td>
								    		<smart:numberInput id="realMgrLevelVNum" name="realMgrLevelVNum" value="${orgFormationMgrFlow.realMgrLevelVNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）五级：</td>
								    	<td><label>${orgFormation.realTechLevelVNum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelVNum" name="realTechLevelVNum" value="${orgFormationMgrFlow.realTechLevelVNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）五级：</td>
								    	<td><label>${orgFormation.realWorkLevelVNum}</label></td>
								    	<td>
								    		<smart:numberInput id="realWorkLevelVNum" name="realWorkLevelVNum" value="${orgFormationMgrFlow.realWorkLevelVNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）六级：</td>
								    	<td><label>${orgFormation.approveMgrLevelVINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveMgrLevelVINum" name="approveMgrLevelVINum" value="${orgFormationMgrFlow.approveMgrLevelVINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）六级：</td>
								    	<td><label>${orgFormation.approveTechLevelVINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelVINum" name="approveTechLevelVINum" value="${orgFormationMgrFlow.approveTechLevelVINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td colspan=3 rowspan=16></td>
							    	</tr>
							    	<tr>
								    	<td>（实有）六级：</td>
								    	<td><label>${orgFormation.realMgrLevelVINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realMgrLevelVINum" name="realMgrLevelVINum" value="${orgFormationMgrFlow.realMgrLevelVINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）六级：</td>
								    	<td><label>${orgFormation.realTechLevelVINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelVINum" name="realTechLevelVINum" value="${orgFormationMgrFlow.realTechLevelVINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）七级：</td>
								    	<td><label>${orgFormation.approveMgrLevelVIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveMgrLevelVIINum" name="approveMgrLevelVIINum" value="${orgFormationMgrFlow.approveMgrLevelVIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）七级：</td>
								    	<td><label>${orgFormation.approveTechLevelVIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelVIINum" name="approveTechLevelVIINum" value="${orgFormationMgrFlow.approveTechLevelVIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）七级：</td>
								    	<td><label>${orgFormation.realMgrLevelVIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realMgrLevelVIINum" name="realMgrLevelVIINum" value="${orgFormationMgrFlow.realMgrLevelVIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）七级：</td>
								    	<td><label>${orgFormation.realTechLevelVIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelVIINum" name="realTechLevelVIINum" value="${orgFormationMgrFlow.realTechLevelVIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）八级：</td>
								    	<td><label>${orgFormation.approveMgrLevelVIIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveMgrLevelVIIINum" name="approveMgrLevelVIIINum" value="${orgFormationMgrFlow.approveMgrLevelVIIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）八级：</td>
								    	<td><label>${orgFormation.approveTechLevelVIIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelVIIINum" name="approveTechLevelVIIINum" value="${orgFormationMgrFlow.approveTechLevelVIIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）八级：</td>
								    	<td><label>${orgFormation.realMgrLevelVIIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realMgrLevelVIIINum" name="realMgrLevelVIIINum" value="${orgFormationMgrFlow.realMgrLevelVIIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）八级：</td>
								    	<td><label>${orgFormation.realTechLevelVIIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelVIIINum" name="realTechLevelVIIINum" value="${orgFormationMgrFlow.realTechLevelVIIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）九级：</td>
								    	<td><label>${orgFormation.approveMgrLevelIXNum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveMgrLevelIXNum" name="approveMgrLevelIXNum" value="${orgFormationMgrFlow.approveMgrLevelIXNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）九级：</td>
								    	<td><label>${orgFormation.approveTechLevelIXNum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelIXNum" name="approveTechLevelIXNum" value="${orgFormationMgrFlow.approveTechLevelIXNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）九级：</td>
								    	<td><label>${orgFormation.realMgrLevelIXNum}</label></td>
								    	<td>
								    		<smart:numberInput id="realMgrLevelIXNum" name="realMgrLevelIXNum" value="${orgFormationMgrFlow.realMgrLevelIXNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）九级：</td>
								    	<td><label>${orgFormation.realTechLevelIXNum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelIXNum" name="realTechLevelIXNum" value="${orgFormationMgrFlow.realTechLevelIXNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）十级：</td>
								    	<td><label>${orgFormation.approveMgrLevelXNum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveMgrLevelXNum" name="approveMgrLevelXNum" value="${orgFormationMgrFlow.approveMgrLevelXNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（定编）十级：</td>
								    	<td><label>${orgFormation.approveTechLevelXNum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelXNum" name="approveTechLevelXNum" value="${orgFormationMgrFlow.approveTechLevelXNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）十级：</td>
								    	<td><label>${orgFormation.realMgrLevelXNum}</label></td>
								    	<td>
								    		<smart:numberInput id="realMgrLevelXNum" name="realMgrLevelXNum" value="${orgFormationMgrFlow.realMgrLevelXNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
								    	<td>（实有）十级：</td>
								    	<td><label>${orgFormation.realTechLevelXNum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelXNum" name="realTechLevelXNum" value="${orgFormationMgrFlow.realTechLevelXNum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td colspan=3 rowspan=6></td>
								    	<td>（定编）十一级：</td>
								    	<td><label>${orgFormation.approveTechLevelXINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelXINum" name="approveTechLevelXINum" value="${orgFormationMgrFlow.approveTechLevelXINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）十一级：</td>
								    	<td><label>${orgFormation.realTechLevelXINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelXINum" name="realTechLevelXINum" value="${orgFormationMgrFlow.realTechLevelXINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）十二级：</td>
								    	<td><label>${orgFormation.approveTechLevelXIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelXIINum" name="approveTechLevelXIINum" value="${orgFormationMgrFlow.approveTechLevelXIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）十二级：</td>
								    	<td><label>${orgFormation.realTechLevelXIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelXIINum" name="realTechLevelXIINum" value="${orgFormationMgrFlow.realTechLevelXIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）十三级：</td>
								    	<td><label>${orgFormation.approveTechLevelXIIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="approveTechLevelXIIINum" name="approveTechLevelXIIINum" value="${orgFormationMgrFlow.approveTechLevelXIIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）十三级：</td>
								    	<td><label>${orgFormation.realTechLevelXIIINum}</label></td>
								    	<td>
								    		<smart:numberInput id="realTechLevelXIIINum" name="realTechLevelXIIINum" value="${orgFormationMgrFlow.realTechLevelXIIINum}" display="inline" min="0" type="text"></smart:numberInput>
								    	</td>
							    	</tr>
							    </tbody>
							</thead>
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
							parent.layui.table.reload('navigationList');
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
			var url="institutionOrgFormationFlow/adjustSubmit";
			smart.confirm({
				title:'提示',
				message:'确认提交申请吗？',
				url:url,
				params : params,
				callback : function(){
					parent.layui.table.reload('orgForamtionMgrFlowList');
					parent.layui.table.reload('navigationList');
                	var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
	</smart:scriptHead>
</smart:body>
</html>