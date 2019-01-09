<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="申请添加机构"/>
</head>
<smart:body>
	<smart:grid>
		<smart:card>
			<smart:cardBody>
				<smart:form id="editForm" action="orgInfoflow/applySave">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${orgInfoMgrFlow.id }"></smart:textInput>
					<smart:textInput type="hidden" name="parentOrgan.id" value="${orgInfoMgrFlow.parentOrgan.id}"></smart:textInput>
					<smart:gridRow>
						<smart:title title="机构信息" style="margin-top: 5px;" color="blue" />
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:infoShowerLabel infoname="上级组织架构" infovalue="${orgInfoMgrFlow.parentOrgan.name}"></smart:infoShowerLabel>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="单位名称：" id="unitBasicName" name="unitBasicName" value="${orgInfoMgrFlow.unitBasicName }" isNotNull="true" verify="required"></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="单位简称：" id="unitBasicSimpleName" name="unitBasicSimpleName" value="${orgInfoMgrFlow.unitBasicSimpleName }" isNotNull="true" verify="required"></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="单位缩写：" id="unitBasicShortName" name="unitBasicShortName" value="${orgInfoMgrFlow.unitBasicShortName }" isNotNull="true" verify="required"></smart:textInput>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="社会信用代码：" name="xydm" id="xydm" value="${orgInfoMgrFlow.xydm }" isNotNull="true" verify="required"></smart:textInput>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="4">
							<smart:infoShowerLabel infoname="行政区划" infovalue="${orgInfoMgrFlow.unitDistrict.name}"></smart:infoShowerLabel>
							<smart:textInput id="unitDistrictId" name="unitDistrict.id" value="${orgInfoMgrFlow.unitDistrict.id}" type="hidden"></smart:textInput>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="4">
							<smart:singleSelect labelName="单位级别：" name="unitLevel.id" isAddDefaltOption="true" initSelectedKey="${orgInfoMgrFlow.unitLevel.id}"
											display="block" id="unitLevel" url="dictquery/sub/code/DM141"></smart:singleSelect>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:singleSelect labelName="隶属关系层次："
											name="unitSubjectionLevel.id" id="unitSubjectionLevel" isAddDefaltOption="true" initSelectedKey="${orgInfoMgrFlow.unitSubjectionLevel.id}"
											display="block" url="dictquery/sub/code/GBT_12404_1997"></smart:singleSelect>
							</smart:gridColumn>
									
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="附属关系类型：" name="unitSubsidiary.id" initSelectedKey="${orgInfoMgrFlow.unitSubsidiary.id}"
									id="unitSubsidiary" display="block" isAddDefaltOption="true"
									url="dictquery/sub/code/DM046"></smart:singleSelect>
							</smart:gridColumn>
							
							<smart:gridColumn colPart="4">
								<smart:singleSelect labelName="性质类别："
									name="unitPropertyLevel.id" id="unitPropertyLevel" isAddDefaltOption="true" initSelectedKey="${orgInfoMgrFlow.unitPropertyLevel.id}"
									display="block" url="dictquery/sub/code/DM142" initIncludeKey="1,4,5" isNotNull="true" verify="required"></smart:singleSelect>
							</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:textInput labelName="隶属单位名称：" name="unitSubordinateName" value="${orgInfoMgrFlow.unitSubordinateName }"
											id="unitSubordinateName"></smart:textInput>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="6">
							<smart:textInput labelName="隶属单位代码：" name="unitSubordinateCode" value="${orgInfoMgrFlow.unitSubordinateCode }"
											id="unitSubordinateCode"></smart:textInput>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:textInput labelName="领导班子主管单位名称：" name="unitLeaderDirectorName" value="${orgInfoMgrFlow.unitLeaderDirectorName }"
											id="unitLeaderDirectorName"></smart:textInput>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="6">
							<smart:textInput labelName="领导班子主管单位代码：" name="unitLeaderDirectorCode" value="${orgInfoMgrFlow.unitLeaderDirectorCode }"
											id="unitLeaderDirectorCode"></smart:textInput>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:textInput labelName="领导班子协管单位名称：" name="unitLeaderAssistName" value="${orgInfoMgrFlow.unitLeaderAssistName }"
											id="unitLeaderAssistName"></smart:textInput>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="6">
							<smart:textInput labelName="领导班子协管单位代码：" name="unitLeaderAssistCode" value="${orgInfoMgrFlow.unitLeaderAssistCode }"
											id="unitLeaderAssistCode"></smart:textInput>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="6">
							<smart:textInput labelName="归口管理单位名称：" name="unitReturnManagementName" value="${orgInfoMgrFlow.unitReturnManagementName }"
											id="unitReturnManagementName"></smart:textInput>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="6">
							<smart:textInput labelName="归口管理单位代码：" name="unitReturnManagementCode" value="${orgInfoMgrFlow.unitReturnManagementCode }"
											id="unitReturnManagementCode"></smart:textInput>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="单位负责人：" name="corporation" value="${orgInfoMgrFlow.corporation }"
								id="corporation"></smart:textInput>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="4">
							<smart:date labelName="批准成立日期：" name="approveDate" value="${orgInfoMgrFlow.approveDate }"
								id="approveDate" display="block"></smart:date>
						</smart:gridColumn>
						
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="批准成立文号：" name="approveNo" value="${orgInfoMgrFlow.approveNo }"
								id="approveNo"></smart:textInput>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:date labelName="成立日期：" name="buildDate" id="buildDate" display="block" value="${orgInfoMgrFlow.buildDate }"></smart:date>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="批准单位名称：" name="approveUnitName" value="${orgInfoMgrFlow.approveUnitName }"
								id="approveUnitName"></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="批准单位代码：" name="approveUnitCode" value="${orgInfoMgrFlow.approveUnitCode }"
								id="approveUnitCode"></smart:textInput>
						</smart:gridColumn>
					</smart:gridRow>
					
					<smart:gridRow>
						<smart:gridColumn colPart="4">
							<smart:textInput labelName="单位说明 ：" name="unitInstruction" value="${orgInfoMgrFlow.unitInstruction }"
								id="unitInstruction"></smart:textInput>
						</smart:gridColumn>
						<smart:gridColumn colPart="4">
							<smart:singleSelect labelName="拨款形式：" name="appropriation.id"
								id="appropriation" display="block" isAddDefaltOption="true" initSelectedKey="${orgInfoMgrFlow.appropriation.id }"
								url="dictquery/sub/code/DM047"></smart:singleSelect>
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
	<smart:scriptHead models="table,form,layer,element,laydate">
		<smart:utils/>
		<smart:buttonScriptAction>
			goBack : function(data) {
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		</smart:buttonScriptAction>
		<smart:continuousSelectAction/>
		<smart:dateRender id="approveDate"/>
		<smart:dateRender id="buildDate"/>
		
		form.on('submit(save)', function (data) {//表单保存
				var params=data.field;
				var url=data.form.action;
				$.post(url,params,function(result){
					if(result.success){//保存成功
						layer.alert(
		                result.message, 
		                {icon: 1,closeBtn: 1 },
		                function () {
		                	parent.layui.table.reload('navigationList');
							parent.layui.table.reload('orgInfoMgrFlowList');
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
			var url="orgInfoflow/applySubmit";
			smart.confirm({
				title:'提示',
				message:'确认提交申请吗？',
				url:url,
				params : params,
				callback : function(){
					parent.layui.table.reload('navigationList');
					parent.layui.table.reload('orgInfoMgrFlowList');
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
			});
		});
	</smart:scriptHead>
</smart:body>
</html>