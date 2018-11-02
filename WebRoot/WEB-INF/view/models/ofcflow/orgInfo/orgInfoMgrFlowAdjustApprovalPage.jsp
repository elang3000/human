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
					<smart:textInput type="hidden" name="id" value="${orgInfoMgrFlow.id }"></smart:textInput>
					<smart:textInput type="hidden" name="organ.id" value="${orgInfoMgrFlow.organ.id}"></smart:textInput>
					<smart:textInput type="hidden" name="parentOrgan.id" value="${orgInfoMgrFlow.parentOrgan.id}"></smart:textInput>
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
							    	<td>单位名称：</td>
							    	<td><label>${empty orgInfo.unitBasicName ? '—':orgInfo.unitBasicName}</label></td>
							    	<td>${orgInfoMgrFlow.unitBasicName }</td>
							    </tr>
							    <tr>
							    	<td>单位简称：</td>
							    	<td><label>${empty orgInfo.unitBasicSimpleName ? '—':orgInfo.unitBasicSimpleName}</label></td>
							    	<td>${orgInfoMgrFlow.unitBasicSimpleName }</td>
							    </tr>
							    <tr>
							    	<td>单位缩写：</td>
							    	<td><label>${empty orgInfo.unitBasicShortName ? '—':orgInfo.unitBasicShortName}</label></td>
							    	<td>${orgInfoMgrFlow.unitBasicShortName }</td>
							    </tr>
							    <tr>
							    	<td>社会信用代码：</td>
							    	<td><label>${empty orgInfo.xydm ? '—':orgInfo.xydm}</label></td>
							    	<td>${orgInfoMgrFlow.xydm }</td>
							    </tr>
							    <tr>
							    	<td>行政区划：</td>
							    	<td><label>${empty orgInfo.unitDistrict.name ? '—':orgInfo.unitDistrict.name}</label></td>
							    	<td>
							    		<label>${orgInfoMgrFlow.unitDistrict.name}</label>
							    	</td>
							    </tr>
							    <tr>
							    	<td>单位级别：</td>
							    	<td><label>${empty orgInfo.unitLevel? '—':orgInfo.unitLevel.name}</label></td>
							    	<td>${orgInfoMgrFlow.unitLevel.name}</td>
							    </tr>
							    <tr>
							    	<td>隶属关系层次：</td>
							    	<td><label>${empty orgInfo.unitSubjectionLevel ? '—':orgInfo.unitSubjectionLevel.name}</label></td>
							    	<td>${orgInfoMgrFlow.unitSubjectionLevel.name}</td>
							    </tr>
							    <tr>
							    	<td>附属关系类型：</td>
							    	<td><label>${empty orgInfo.unitSubsidiary ? '—':orgInfo.unitSubsidiary.name}</label></td>
							    	<td>${orgInfoMgrFlow.unitSubsidiary.name}</td>
							    </tr>
							    <tr>
							    	<td>性质级别：</td>
							    	<td><label>${empty orgInfo.unitPropertyLevel ? '—':orgInfo.unitPropertyLevel.name}</label></td>
							    	<td>${orgInfoMgrFlow.unitPropertyLevel.name}</td>
							    </tr>
							    <tr>
							    	<td>隶属单位名称：</td>
							    	<td><label>${empty orgInfo.unitSubordinateName ? '—':orgInfo.unitSubordinateName}</label></td>
							    	<td>${orgInfoMgrFlow.unitSubordinateName }</td>
							    </tr>
							    <tr>
							    	<td>隶属单位代码：</td>
							    	<td><label>${empty orgInfo.unitSubordinateCode ? '—':orgInfo.unitSubordinateCode}</label></td>
							    	<td>${orgInfoMgrFlow.unitSubordinateCode }</td>
							    </tr>
							    <tr>
							    	<td>领导班子主管单位名称：</td>
							    	<td><label>${empty orgInfo.unitLeaderDirectorName ? '—':orgInfo.unitLeaderDirectorName}</label></td>
							    	<td>${orgInfoMgrFlow.unitLeaderDirectorName }</td>
							    </tr>
							    <tr>
							    	<td>领导班子主管单位代码：</td>
							    	<td><label>${empty orgInfo.unitLeaderDirectorCode ? '—':orgInfo.unitLeaderDirectorCode}</label></td>
							    	<td>${orgInfoMgrFlow.unitLeaderDirectorCode }</td>
							    </tr>
							    <tr>
							    	<td>领导班子协管单位名称：</td>
							    	<td><label>${empty orgInfo.unitLeaderAssistName ? '—':orgInfo.unitLeaderAssistName}</label></td>
							    	<td>${orgInfoMgrFlow.unitLeaderAssistName }</td>
							    </tr>
							    <tr>
							    	<td>领导班子协管单位代码：</td>
							    	<td><label>${empty orgInfo.unitLeaderAssistCode ? '—':orgInfo.unitLeaderAssistCode}</label></td>
							    	<td>${orgInfoMgrFlow.unitLeaderAssistCode }</td>
							    </tr>
							    <tr>
							    	<td>归口管理单位名称：</td>
							    	<td><label>${empty orgInfo.unitReturnManagementName ? '—':orgInfo.unitReturnManagementName}</label></td>
							    	<td>${orgInfoMgrFlow.unitReturnManagementName }</td>
							    </tr>
							    <tr>
							    	<td>归口管理单位代码：</td>
							    	<td><label>${empty orgInfo.unitReturnManagementCode ? '—':orgInfo.unitReturnManagementCode}</label></td>
							    	<td>${orgInfoMgrFlow.unitReturnManagementCode }</td>
							    </tr>
							    <tr>
							    	<td>单位负责人：</td>
							    	<td><label>${empty orgInfo.corporation ? '—':orgInfo.corporation}</label></td>
							    	<td>${orgInfoMgrFlow.corporation }</td>
							    </tr>
							    <tr>
							    	<td>批准成立日期：</td>
							    	<td><label>${empty orgInfo.approveDate ? '—':orgInfo.approveDate}</label></td>
							    	<td>${orgInfoMgrFlow.approveDate }</td>
							    </tr>
							    <tr>
							    	<td>批准成立文号：</td>
							    	<td><label>${empty orgInfo.approveNo ? '—': orgInfo.approveNo}</label></td>
							    	<td>${orgInfoMgrFlow.approveNo }</td>
							    </tr>
							    <tr>
							    	<td>成立日期：</td>
							    	<td><label>${empty orgInfo.buildDate ? '—':orgInfo.buildDate}</label></td>
							    	<td>${orgInfoMgrFlow.buildDate }</td>
							    </tr>
							    <tr>
							    	<td>批准单位名称：</td>
							    	<td><label>${empty orgInfo.approveUnitName ? '—':orgInfo.approveUnitName}</label></td>
							    	<td>${orgInfoMgrFlow.approveUnitName }</td>
							    </tr>
							    <tr>
							    	<td>批准单位代码：</td>
							    	<td><label>${empty orgInfo.approveUnitCode ? '—':orgInfo.approveUnitCode}</label></td>
							    	<td>${orgInfoMgrFlow.approveUnitCode }</td>
							    </tr>
							    <tr>
							    	<td>单位说明 ：</td>
							    	<td><label>${empty orgInfo.unitInstruction ? '—':orgInfo.unitInstruction}</label></td>
							    	<td>${orgInfoMgrFlow.unitInstruction }</td>
							    </tr>
							    <tr>
							    	<td>拨款形式：</td>
							    	<td><label>${empty orgInfo.appropriation ? '—':orgInfo.appropriation.name}</label></td>
							    	<td>${orgInfoMgrFlow.appropriation.name }</td>
							    </tr>
							    <tr>
							    	<td>机构类别：</td>
							    	<td><label>${empty orgInfo.orgCategory ? '—':orgInfo.orgCategory.name}</label></td>
							    	<td>${orgInfoMgrFlow.orgCategory.name}</td>
							    </tr>
							</tbody>
						</table>
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
		<smart:buttonScriptAction>
			pass : function() {
				$("#result").val("1");//审批通过
				smart.confirm({
					title:'提示',
					message:'确认审批通过吗？',
					url:'orgInfoflow/auditAdjustFlow',
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
					url:'orgInfoflow/auditAdjustFlow',
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