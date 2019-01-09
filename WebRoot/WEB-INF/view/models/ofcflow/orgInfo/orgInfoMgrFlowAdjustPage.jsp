<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="smart" uri="http://smart.wondersgroup.com/page/component"%>
<!DOCTYPE html>
<html>
<head>
<smart:initHead title="申请添加机构"/>
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
				<smart:form id="editForm" action="orgInfoflow/adjustSave">
					<smart:fromTokenTag/>
					<smart:textInput type="hidden" name="id" value="${orgInfoMgrFlow.id }"></smart:textInput>
					<smart:textInput type="hidden" name="organ.id" value="${orgInfoMgrFlow.organ.id}"></smart:textInput>
					<smart:textInput type="hidden" name="parentOrgan.id" value="${orgInfoMgrFlow.parentOrgan.id}"></smart:textInput>
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
							    	<td><label>${orgInfo.unitBasicName == '' ? '无原数据':orgInfo.unitBasicName}</label></td>
							    	<td><smart:textInput id="unitBasicName" display="inline" name="unitBasicName" value="${orgInfoMgrFlow.unitBasicName }" verify="required"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>单位简称：</td>
							    	<td><label>${empty orgInfo.unitBasicSimpleName ? '无原数据':orgInfo.unitBasicSimpleName}</label></td>
							    	<td><smart:textInput id="unitBasicSimpleName" display="inline" name="unitBasicSimpleName" value="${orgInfoMgrFlow.unitBasicSimpleName }" verify="required"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>单位缩写：</td>
							    	<td><label>${empty orgInfo.unitBasicShortName ? '无原数据':orgInfo.unitBasicShortName}</label></td>
							    	<td><smart:textInput id="unitBasicShortName" display="inline" name="unitBasicShortName" value="${orgInfoMgrFlow.unitBasicShortName }" verify="required"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>社会信用代码：</td>
							    	<td><label>${empty orgInfo.xydm ? '无原数据':orgInfo.xydm}</label></td>
							    	<td><smart:textInput id="xydm" display="inline" name="xydm" value="${orgInfoMgrFlow.xydm }" verify="required"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>行政区划：</td>
							    	<td><label>${empty orgInfo.unitDistrict.name ? '无原数据':orgInfo.unitDistrict.name}</label></td>
							    	<td>
							    		<label>${orgInfoMgrFlow.unitDistrict.name}</label>
							    		<smart:textInput id="unitDistrictId" name="unitDistrict.id" value="${orgInfoMgrFlow.unitDistrict.id}" type="hidden"></smart:textInput>
							    	</td>
							    </tr>
							    <tr>
							    	<td>单位级别：</td>
							    	<td><label>${empty orgInfo.unitLevel? '无原数据':orgInfo.unitLevel.name}</label></td>
							    	<td><smart:singleSelect name="unitLevel.id" isAddDefaltOption="true" initSelectedKey="${orgInfoMgrFlow.unitLevel.id}"
											display="inline" id="unitLevel" url="dictquery/sub/code/DM141"></smart:singleSelect></td>
							    </tr>
							    <tr>
							    	<td>隶属关系层次：</td>
							    	<td><label>${empty orgInfo.unitSubjectionLevel ? '无原数据':orgInfo.unitSubjectionLevel.name}</label></td>
							    	<td><smart:singleSelect name="unitSubjectionLevel.id" id="unitSubjectionLevel" isAddDefaltOption="true" initSelectedKey="${orgInfoMgrFlow.unitSubjectionLevel.id}"
											display="inline" url="dictquery/sub/code/GBT_12404_1997"></smart:singleSelect></td>
							    </tr>
							    <tr>
							    	<td>附属关系类型：</td>
							    	<td><label>${empty orgInfo.unitSubsidiary ? '无原数据':orgInfo.unitSubsidiary.name}</label></td>
							    	<td><smart:singleSelect name="unitSubsidiary.id" initSelectedKey="${orgInfoMgrFlow.unitSubsidiary.id}"
									id="unitSubsidiary" display="inline" isAddDefaltOption="true"
									url="dictquery/sub/code/DM046"></smart:singleSelect></td>
							    </tr>
							    <tr>
							    	<td>性质类别：</td>
							    	<td><label>${empty orgInfo.unitPropertyLevel ? '无原数据':orgInfo.unitPropertyLevel.name}</label></td>
							    	<td>
							    		<label>${orgInfoMgrFlow.unitPropertyLevel.name}</label>
							    		<smart:textInput id="unitPropertyLevelId" name="unitPropertyLevel.id" value="${orgInfoMgrFlow.unitPropertyLevel.id}" type="hidden"></smart:textInput>
									</td>
							    </tr>
							    <tr>
							    	<td>隶属单位名称：</td>
							    	<td><label>${empty orgInfo.unitSubordinateName ? '无原数据':orgInfo.unitSubordinateName}</label></td>
							    	<td><smart:textInput name="unitSubordinateName" value="${orgInfoMgrFlow.unitSubordinateName }" display="inline"
											id="unitSubordinateName"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>隶属单位代码：</td>
							    	<td><label>${empty orgInfo.unitSubordinateCode ? '无原数据':orgInfo.unitSubordinateCode}</label></td>
							    	<td><smart:textInput display="inline" name="unitSubordinateCode" value="${orgInfoMgrFlow.unitSubordinateCode }"
											id="unitSubordinateCode"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>领导班子主管单位名称：</td>
							    	<td><label>${empty orgInfo.unitLeaderDirectorName ? '无原数据':orgInfo.unitLeaderDirectorName}</label></td>
							    	<td><smart:textInput display="inline" name="unitLeaderDirectorName" value="${orgInfoMgrFlow.unitLeaderDirectorName }"
											id="unitLeaderDirectorName"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>领导班子主管单位代码：</td>
							    	<td><label>${empty orgInfo.unitLeaderDirectorCode ? '无原数据':orgInfo.unitLeaderDirectorCode}</label></td>
							    	<td><smart:textInput display="inline" name="unitLeaderDirectorCode" value="${orgInfoMgrFlow.unitLeaderDirectorCode }"
											id="unitLeaderDirectorCode"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>领导班子协管单位名称：</td>
							    	<td><label>${empty orgInfo.unitLeaderAssistName ? '无原数据':orgInfo.unitLeaderAssistName}</label></td>
							    	<td><smart:textInput display="inline" name="unitLeaderAssistName" value="${orgInfoMgrFlow.unitLeaderAssistName }"
											id="unitLeaderAssistName"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>领导班子协管单位代码：</td>
							    	<td><label>${empty orgInfo.unitLeaderAssistCode ? '无原数据':orgInfo.unitLeaderAssistCode}</label></td>
							    	<td><smart:textInput display="inline" name="unitLeaderAssistCode" value="${orgInfoMgrFlow.unitLeaderAssistCode }"
											id="unitLeaderAssistCode"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>归口管理单位名称：</td>
							    	<td><label>${empty orgInfo.unitReturnManagementName ? '无原数据':orgInfo.unitReturnManagementName}</label></td>
							    	<td><smart:textInput display="inline" name="unitReturnManagementName" value="${orgInfoMgrFlow.unitReturnManagementName }"
											id="unitReturnManagementName"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>归口管理单位代码：</td>
							    	<td><label>${empty orgInfo.unitReturnManagementCode ? '无原数据':orgInfo.unitReturnManagementCode}</label></td>
							    	<td><smart:textInput display="inline" name="unitReturnManagementCode" value="${orgInfoMgrFlow.unitReturnManagementCode }"
											id="unitReturnManagementCode"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>单位负责人：</td>
							    	<td><label>${empty orgInfo.corporation ? '无原数据':orgInfo.corporation}</label></td>
							    	<td><smart:textInput display="inline" name="corporation" value="${orgInfoMgrFlow.corporation }" id="corporation"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>批准成立日期：</td>
							    	<td><label>${empty orgInfo.approveDate ? '无原数据':orgInfo.approveDate}</label></td>
							    	<td><smart:date name="approveDate" value="${orgInfoMgrFlow.approveDate }" id="approveDate" display="inline"></smart:date></td>
							    </tr>
							    <tr>
							    	<td>批准成立文号：</td>
							    	<td><label>${empty orgInfo.approveNo ? '无原数据': orgInfo.approveNo}</label></td>
							    	<td><smart:textInput display="inline" name="approveNo" value="${orgInfoMgrFlow.approveNo }"
										id="approveNo"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>成立日期：</td>
							    	<td><label>${empty orgInfo.buildDate ? '无原数据':orgInfo.buildDate}</label></td>
							    	<td><smart:date name="buildDate" id="buildDate" display="inline" value="${orgInfoMgrFlow.buildDate }"></smart:date></td>
							    </tr>
							    <tr>
							    	<td>批准单位名称：</td>
							    	<td><label>${empty orgInfo.approveUnitName ? '无原数据':orgInfo.approveUnitName}</label></td>
							    	<td><smart:textInput display="inline" name="approveUnitName" value="${orgInfoMgrFlow.approveUnitName }"
										id="approveUnitName"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>批准单位代码：</td>
							    	<td><label>${empty orgInfo.approveUnitCode ? '无原数据':orgInfo.approveUnitCode}</label></td>
							    	<td><smart:textInput display="inline" name="approveUnitCode" value="${orgInfoMgrFlow.approveUnitCode }"
										id="approveUnitCode"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>单位说明 ：</td>
							    	<td><label>${empty orgInfo.unitInstruction ? '无原数据':orgInfo.unitInstruction}</label></td>
							    	<td><smart:textInput display="inline" name="unitInstruction" value="${orgInfoMgrFlow.unitInstruction }"
										id="unitInstruction"></smart:textInput></td>
							    </tr>
							    <tr>
							    	<td>拨款形式：</td>
							    	<td><label>${empty orgInfo.appropriation ? '无原数据':orgInfo.appropriation.name}</label></td>
							    	<td><smart:singleSelect name="appropriation.id" id="appropriation" display="inline" isAddDefaltOption="true" initSelectedKey="${orgInfoMgrFlow.appropriation.id }"
										url="dictquery/sub/code/DM047"></smart:singleSelect></td>
							    </tr>
							</tbody>
						</table>
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
			var url="orgInfoflow/adjustSubmit";
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