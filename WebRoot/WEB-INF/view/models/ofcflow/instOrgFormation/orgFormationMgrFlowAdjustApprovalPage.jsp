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
								    		${orgFormationMgrFlow.approveMgrLevelINum}
								    	</td>
								    	<td>（定编）一级：</td>
								    	<td><label>${orgFormation.approveTechLevelINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelINum}
								    	</td>
								    	<td>（定编）一级：</td>
								    	<td><label>${orgFormation.approveWorkLevelINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveWorkLevelINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）一级：</td>
								    	<td><label>${orgFormation.realMgrLevelINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realMgrLevelINum}
								    	</td>
								    	<td>（实有）一级：</td>
								    	<td><label>${orgFormation.realTechLevelINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelINum}
								    	</td>
								    	<td>（实有）一级：</td>
								    	<td><label>${orgFormation.realWorkLevelINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realWorkLevelINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）二级：</td>
								    	<td><label>${orgFormation.approveMgrLevelIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveMgrLevelIINum}
								    	</td>
								    	<td>（定编）二级：</td>
								    	<td><label>${orgFormation.approveTechLevelIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelIINum}
								    	</td>
								    	<td>（定编）二级：</td>
								    	<td><label>${orgFormation.approveWorkLevelIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveWorkLevelIINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）二级：</td>
								    	<td><label>${orgFormation.realMgrLevelIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realMgrLevelIINum}
								    	</td>
								    	<td>（实有）二级：</td>
								    	<td><label>${orgFormation.realTechLevelIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelIINum}
								    	</td>
								    	<td>（实有）二级：</td>
								    	<td><label>${orgFormation.realWorkLevelIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realWorkLevelIINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）三级：</td>
								    	<td><label>${orgFormation.approveMgrLevelIIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveMgrLevelIIINum}
								    	</td>
								    	<td>（定编）三级：</td>
								    	<td><label>${orgFormation.approveTechLevelIIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelIIINum}
								    	</td>
								    	<td>（定编）三级：</td>
								    	<td><label>${orgFormation.approveWorkLevelIIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveWorkLevelIIINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）三级：</td>
								    	<td><label>${orgFormation.realMgrLevelIIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realMgrLevelIIINum}
								    	</td>
								    	<td>（实有）三级：</td>
								    	<td><label>${orgFormation.realTechLevelIIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelIIINum}
								    	</td>
								    	<td>（实有）三级：</td>
								    	<td><label>${orgFormation.realWorkLevelIIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realWorkLevelIIINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）四级：</td>
								    	<td><label>${orgFormation.approveMgrLevelIVNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveMgrLevelIVNum}
								    	</td>
								    	<td>（定编）四级：</td>
								    	<td><label>${orgFormation.approveTechLevelIVNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelIVNum}
								    	</td>
								    	<td>（定编）四级：</td>
								    	<td><label>${orgFormation.approveWorkLevelIVNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveWorkLevelIVNum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）四级：</td>
								    	<td><label>${orgFormation.realMgrLevelIVNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realMgrLevelIVNum}
								    	</td>
								    	<td>（实有）四级：</td>
								    	<td><label>${orgFormation.realTechLevelIVNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelIVNum}
								    	</td>
								    	<td>（实有）四级：</td>
								    	<td><label>${orgFormation.realWorkLevelIVNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realWorkLevelIVNum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）五级：</td>
								    	<td><label>${orgFormation.approveMgrLevelVNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveMgrLevelVNum}
								    	</td>
								    	<td>（定编）五级：</td>
								    	<td><label>${orgFormation.approveTechLevelVNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelVNum}
								    	</td>
								    	<td>（定编）五级：</td>
								    	<td><label>${orgFormation.approveWorkLevelVNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveWorkLevelVNum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）五级：</td>
								    	<td><label>${orgFormation.realMgrLevelVNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realMgrLevelVNum}
								    	</td>
								    	<td>（实有）五级：</td>
								    	<td><label>${orgFormation.realTechLevelVNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelVNum}
								    	</td>
								    	<td>（实有）五级：</td>
								    	<td><label>${orgFormation.realWorkLevelVNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realWorkLevelVNum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）六级：</td>
								    	<td><label>${orgFormation.approveMgrLevelVINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveMgrLevelVINum}
								    	</td>
								    	<td>（定编）六级：</td>
								    	<td><label>${orgFormation.approveTechLevelVINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelVINum}
								    	</td>
								    	<td colspan=3 rowspan=16></td>
							    	</tr>
							    	<tr>
								    	<td>（实有）六级：</td>
								    	<td><label>${orgFormation.realMgrLevelVINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realMgrLevelVINum}
								    	</td>
								    	<td>（实有）六级：</td>
								    	<td><label>${orgFormation.realTechLevelVINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelVINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）七级：</td>
								    	<td><label>${orgFormation.approveMgrLevelVIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveMgrLevelVIINum}
								    	</td>
								    	<td>（定编）七级：</td>
								    	<td><label>${orgFormation.approveTechLevelVIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelVIINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）七级：</td>
								    	<td><label>${orgFormation.realMgrLevelVIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realMgrLevelVIINum}
								    	</td>
								    	<td>（实有）七级：</td>
								    	<td><label>${orgFormation.realTechLevelVIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelVIINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）八级：</td>
								    	<td><label>${orgFormation.approveMgrLevelVIIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveMgrLevelVIIINum}
								    	</td>
								    	<td>（定编）八级：</td>
								    	<td><label>${orgFormation.approveTechLevelVIIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelVIIINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）八级：</td>
								    	<td><label>${orgFormation.realMgrLevelVIIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realMgrLevelVIIINum}
								    	</td>
								    	<td>（实有）八级：</td>
								    	<td><label>${orgFormation.realTechLevelVIIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelVIIINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）九级：</td>
								    	<td><label>${orgFormation.approveMgrLevelIXNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveMgrLevelIXNum}
								    	</td>
								    	<td>（定编）九级：</td>
								    	<td><label>${orgFormation.approveTechLevelIXNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelIXNum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）九级：</td>
								    	<td><label>${orgFormation.realMgrLevelIXNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realMgrLevelIXNum}
								    	</td>
								    	<td>（实有）九级：</td>
								    	<td><label>${orgFormation.realTechLevelIXNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelIXNum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）十级：</td>
								    	<td><label>${orgFormation.approveMgrLevelXNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveMgrLevelXNum}
								    	</td>
								    	<td>（定编）十级：</td>
								    	<td><label>${orgFormation.approveTechLevelXNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelXNum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）十级：</td>
								    	<td><label>${orgFormation.realMgrLevelXNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realMgrLevelXNum}
								    	</td>
								    	<td>（实有）十级：</td>
								    	<td><label>${orgFormation.realTechLevelXNum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelXNum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td colspan=3 rowspan=6></td>
								    	<td>（定编）十一级：</td>
								    	<td><label>${orgFormation.approveTechLevelXINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelXINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）十一级：</td>
								    	<td><label>${orgFormation.realTechLevelXINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelXINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）十二级：</td>
								    	<td><label>${orgFormation.approveTechLevelXIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelXIINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）十二级：</td>
								    	<td><label>${orgFormation.realTechLevelXIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelXIINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（定编）十三级：</td>
								    	<td><label>${orgFormation.approveTechLevelXIIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.approveTechLevelXIIINum}
								    	</td>
							    	</tr>
							    	<tr>
								    	<td>（实有）十三级：</td>
								    	<td><label>${orgFormation.realTechLevelXIIINum}</label></td>
								    	<td>
								    		${orgFormationMgrFlow.realTechLevelXIIINum}
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
					url:'institutionOrgFormationFlow/auditAdjustFlow',
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
					url:'institutionOrgFormationFlow/auditAdjustFlow',
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
					url:'institutionOrgFormationFlow/auditAdjustFlow',
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